package cz.mik0486.pjp.project.antlr;

import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import cz.mik0486.pjp.project.antlr.gen.LanguageBaseListener;
import cz.mik0486.pjp.project.antlr.gen.LanguageListener;
import cz.mik0486.pjp.project.antlr.gen.LanguageParser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

@Slf4j
public class LanguageCompiler extends LanguageBaseListener implements LanguageListener {
    private final Map<String, Type> symbolTable = new HashMap<>();
    private final ParseTreeProperty<Statement> tree = new ParseTreeProperty<>();

    @Getter
    private List<String> compiledCodeLines = null;

    private int labelCounter = 0;
    public String getNewLabel() {
        return STR . "L\{ labelCounter++ }";
    }

    @Override
    public void enterProgram(LanguageParser.ProgramContext ctx) {
        super.enterProgram(ctx);
        compiledCodeLines = new ArrayList<>();
    }

    @Override
    public void exitProgram(LanguageParser.ProgramContext ctx) {
        try {
            for (LanguageParser.StatementContext statement : ctx.statement()) {
                try {
                    compiledCodeLines.addAll(Arrays.asList(tree.get(statement).getCode().split("\n")));
                } catch (Exception e) {
                    compiledCodeLines.add(STR . "Error in statement: \{ statement.getText() }");
                    ErrorLogger.addError(ctx, statement.getStart(), "Error in statement: %s", statement.getText());
                }
            }

            // Remove empty lines
            compiledCodeLines.removeIf(String::isBlank);
        } catch (Exception e) {
            log.error("Error while compiling code", e);
            compiledCodeLines = null;
        }
    }

    @Override
    public void enterVarDeclStatement(LanguageParser.VarDeclStatementContext ctx) {
        List<String> instructions = new ArrayList<>();

        Type type = Type.valueOf(ctx.TYPE().getText().toUpperCase());
        Object defaultValue = switch (type) {
            case INT -> 0;
            case FLOAT -> 0.0f;
            case BOOL -> false;
            case STRING -> "\"\"";
            default -> "Unknown type declared!";
        };

        for (TerminalNode var : ctx.VAR()) {
            symbolTable.put(var.getText(), type);

            instructions.add(STR . "push \{ type.toString().charAt(0) } \{ defaultValue }");
            instructions.add(STR . "save \{ var.getText() }");
        }

        tree.put(ctx, Statement.of(String.join("\n", instructions), type));
    }

    @Override
    public void exitModuloExpression(LanguageParser.ModuloExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        instructions.add(tree.get(ctx.expression(0)).getCode());
        instructions.add(tree.get(ctx.expression(1)).getCode());
        instructions.add("mod");

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.INT));
    }

    @Override
    public void exitIfStatement(LanguageParser.IfStatementContext ctx) {
        List<String> instructions = new ArrayList<>();

        String labelStart = getNewLabel();
        String labelEnd = getNewLabel();

        String condition = tree.get(ctx.condition()).getCode();
        String positive = tree.get(ctx.statement(0)).getCode();
        String negative = ctx.statement().size() == 2
                ? tree.get(ctx.statement(1)).getCode()
                : "";

        if (negative.isBlank()) {
            instructions.add(condition);
            instructions.add(STR . "fjmp \{ labelEnd }");
            instructions.add(positive);
            instructions.add(STR . "label \{ labelEnd }");
        } else {
            instructions.add(condition);
            instructions.add(STR . "fjmp \{ labelStart }");
            instructions.add(positive);
            instructions.add(STR . "jmp \{ labelEnd }");
            instructions.add(STR . "label \{ labelStart }");
            instructions.add(negative);
            instructions.add(STR . "label \{ labelEnd }");
        }

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.VOID));
    }

    @Override
    public void exitWhileStatement(LanguageParser.WhileStatementContext ctx) {
        List<String> instructions = new ArrayList<>();

        String labelStart = getNewLabel();
        String labelEnd = getNewLabel();

        String condition = tree.get(ctx.condition()).getCode();
        String statement = tree.get(ctx.statement()).getCode();

        instructions.add(STR . "label \{ labelStart }");
        instructions.add(condition);
        instructions.add(STR . "fjmp \{ labelEnd }");
        instructions.add(statement);
        instructions.add(STR . "jmp \{ labelStart }");
        instructions.add(STR . "label \{ labelEnd }");

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.VOID));
    }

    @Override
    public void exitBoolCondition(LanguageParser.BoolConditionContext ctx) {
        tree.put(ctx, tree.get(ctx.expression()));
    }

    @Override
    public void exitAritmExpression(LanguageParser.AritmExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        Statement left = tree.get(ctx.expression(0));
        Statement right = tree.get(ctx.expression(1));

        instructions.add(left.getCode());
        if (left.getType() == Type.INT && right.getType() == Type.FLOAT) {
            instructions.add("itof");
        }

        instructions.add(right.getCode());
        if (right.getType() == Type.INT && left.getType() == Type.FLOAT) {
            instructions.add("itof");
        }

        instructions.add(switch (ctx.op.getText()) {
            case "+" -> "add";
            case "-" -> "sub";
            case "*" -> "mul";
            case "/" -> "div";
            default -> "Unknown operator";
        });

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.resultType(left.getType(), right.getType(), ctx.op.getText())));
    }

    @Override
    public void exitLogicExpression(LanguageParser.LogicExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        Statement left = tree.get(ctx.expression(0));
        instructions.add(left.getCode());

        Statement right = tree.get(ctx.expression(1));
        instructions.add(right.getCode());

        instructions.add(switch (ctx.op.getText()) {
            case "&&" -> "and";
            case "||" -> "or";
            default -> "Unknown operator";
        });

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.BOOL));
    }

    @Override
    public void exitParenExpression(LanguageParser.ParenExpressionContext ctx) {
        tree.put(ctx, tree.get(ctx.expression()));
    }

    @Override
    public void exitBlockStatement(LanguageParser.BlockStatementContext ctx) {
        List<String> instructions = new ArrayList<>();

        for (LanguageParser.StatementContext statement : ctx.statement()) {
            instructions.add(tree.get(statement).getCode());
        }

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.VOID));
    }

    @Override
    public void exitWriteStatement(LanguageParser.WriteStatementContext ctx) {
        List<String> instructions = new ArrayList<>();

        for (LanguageParser.ExpressionContext expression : ctx.expression()) {
            instructions.add(tree.get(expression).getCode());
        }

        instructions.add(STR . "print \{ ctx.expression().size() }");
        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.VOID));
    }

    @Override
    public void exitVarExpression(LanguageParser.VarExpressionContext ctx) {
        String varName = ctx.VAR().getText();
        String code = STR . "load \{ varName }";

        tree.put(ctx, Statement.of(code, symbolTable.get(varName)));
    }

    @Override
    public void exitLiteralExpression(LanguageParser.LiteralExpressionContext ctx) {
        Type type = getLiteralType(ctx.literal());

        switch (type) {
            case INT -> {
                int value = Integer.parseInt(ctx.literal().INT().getText());
                tree.put(ctx, Statement.of(STR . "push I \{ value }", Type.INT));
            }

            case FLOAT -> {
                float value = Float.parseFloat(ctx.literal().FLOAT().getText());
                tree.put(ctx, Statement.of(STR . "push F \{ value }", Type.FLOAT));
            }

            case BOOL -> {
                boolean value = Boolean.parseBoolean(ctx.literal().BOOL().getText());
                tree.put(ctx, Statement.of(STR . "push B \{ value ? "true" : "false" }", Type.BOOL));
            }

            case STRING -> {
                String value = ctx.literal().STRING().getText();
                tree.put(ctx, Statement.of(STR . "push S \{ value }", Type.STRING));
            }

            case ERROR -> {
                log.error("Unknown literal type");
            }
        }
    }

    private Type getLiteralType(LanguageParser.LiteralContext ctx) {
        if (ctx.INT() != null) {
            return Type.INT;
        } else if (ctx.FLOAT() != null) {
            return Type.FLOAT;
        } else if (ctx.BOOL() != null) {
            return Type.BOOL;
        } else if (ctx.STRING() != null) {
            return Type.STRING;
        }

        return Type.ERROR;
    }

    @Override
    public void exitExprStatement(LanguageParser.ExprStatementContext ctx) {
        List<String> instructions = new ArrayList<>();

        Type type = tree.get(ctx.expression()).getType();
        instructions.add(tree.get(ctx.expression()).getCode());
        instructions.add("pop");

        tree.put(ctx, Statement.of(String.join("\n", instructions), type));
    }

    @Override
    public void exitEmptyStatement(LanguageParser.EmptyStatementContext ctx) {
        tree.put(ctx, Statement.of("", Type.VOID));
    }

    @Override
    public void exitConcatExpression(LanguageParser.ConcatExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        instructions.add(tree.get(ctx.expression(0)).getCode());
        instructions.add(tree.get(ctx.expression(1)).getCode());
        instructions.add("concat");

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.STRING));
    }

    @Override
    public void exitNotExpression(LanguageParser.NotExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        instructions.add(tree.get(ctx.expression()).getCode());
        instructions.add("not");

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.BOOL));
    }

    @Override
    public void exitReadStatement(LanguageParser.ReadStatementContext ctx) {
        List<String> instructions = new ArrayList<>();

        for (TerminalNode var : ctx.VAR()) {
            String varName = var.getText();
            Type varType = symbolTable.getOrDefault(varName, Type.ERROR);

            instructions.add(STR . "read \{ varType.toString().charAt(0) }");
            instructions.add(STR . "save \{ varName }");
        }

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.VOID));
    }

    @Override
    public void exitNegExpression(LanguageParser.NegExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        Type type = tree.get(ctx.expression()).getType();
        Type result = Type.resultType(type, Type.INT, "-");

        instructions.add(tree.get(ctx.expression()).getCode());
        instructions.add("uminus");

        tree.put(ctx, Statement.of(String.join("\n", instructions), result));
    }

    @Override
    public void exitComparisonExpression(LanguageParser.ComparisonExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        Statement left = tree.get(ctx.expression(0));
        Statement right = tree.get(ctx.expression(1));

        instructions.add(left.getCode());
        if (left.getType() == Type.INT && right.getType() == Type.FLOAT) {
            instructions.add("itof");
        }

        instructions.add(right.getCode());
        if (right.getType() == Type.INT && left.getType() == Type.FLOAT) {
            instructions.add("itof");
        }

        switch (ctx.op.getText()) {
            case "==" -> {
                instructions.add("eq");
            }

            case "!=" -> {
                instructions.add("eq");
                instructions.add("not");
            }
        }

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.BOOL));
    }

    @Override
    public void exitRelationExpression(LanguageParser.RelationExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        Statement left = tree.get(ctx.expression(0));
        Statement right = tree.get(ctx.expression(1));

        instructions.add(left.getCode());
        if (left.getType() == Type.INT && right.getType() == Type.FLOAT) {
            instructions.add("itof");
        }

        instructions.add(right.getCode());
        if (right.getType() == Type.INT && left.getType() == Type.FLOAT) {
            instructions.add("itof");
        }

        instructions.add(switch (ctx.op.getText()) {
            case "<" -> "lt";
            case ">" -> "gt";
            case "==" -> "eq";
            case "!=" -> "ne";
            default -> "Unknown operator";
        });

        tree.put(ctx, Statement.of(String.join("\n", instructions), Type.BOOL));
    }

    @Override
    public void exitAssignExpression(LanguageParser.AssignExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        Statement var = tree.get(ctx.expression());
        String varName = ctx.VAR().getText();
        Type varType = symbolTable.getOrDefault(varName, Type.ERROR);
        Type exprType = var.getType();

        instructions.add(var.getCode());
        if (varType == Type.FLOAT && exprType == Type.INT) {
            instructions.add("itof");
        }

        instructions.add(STR . "save \{ varName }");
        instructions.add(STR . "load \{ varName }");

        tree.put(ctx, Statement.of(String.join("\n", instructions), varType));
    }
}
