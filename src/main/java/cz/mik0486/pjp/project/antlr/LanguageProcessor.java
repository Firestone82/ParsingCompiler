package cz.mik0486.pjp.project.antlr;

import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import cz.mik0486.pjp.project.antlr.gen.LanguageBaseListener;
import cz.mik0486.pjp.project.antlr.gen.LanguageListener;
import cz.mik0486.pjp.project.antlr.gen.LanguageParser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

@Slf4j
public class LanguageProcessor extends LanguageBaseListener implements LanguageListener {
    private final Map<String, Pair<Type, Object>> symbolTable = new HashMap<>();

    private final ParseTreeProperty<Pair<Type, Object>> values = new ParseTreeProperty<>();
    private final ParseTreeProperty<String> code = new ParseTreeProperty<>();

    @Getter
    private List<String> compiledCodeLines = null;

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
                    compiledCodeLines.addAll(Arrays.asList(code.get(statement).split("\n")));
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
            values.put(var, new Pair<>(type, defaultValue));
            symbolTable.put(var.getText(), new Pair<>(type, defaultValue));

            instructions.add(STR . "push \{ type.toString().charAt(0) } \{ defaultValue }");
            instructions.add(STR . "save \{ var.getText() }");
        }

        code.put(ctx, String.join("\n", instructions));
    }

    @Override
    public void exitModuloExpression(LanguageParser.ModuloExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        // Left
        String leftCode = code.get(ctx.expression(0));
        Pair<Type, Object> leftValue = values.get(ctx.expression(0));

        // Right
        String rightCode = code.get(ctx.expression(1));
        Pair<Type, Object> rightValue = values.get(ctx.expression(1));

        // Operator
        String operator = ctx.op.getText();
        Object value = switch (operator) {
            case "%" -> (int) leftValue.b % (int) rightValue.b;
            default -> "Unknown operator";
        };

        instructions.add(leftCode);
        instructions.add(rightCode);
        instructions.add("mod");

        values.put(ctx, new Pair<>(Type.INT, value));
        code.put(ctx, String.join("\n", instructions));
    }

    @Override
    public void exitAritmExpression(LanguageParser.AritmExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        // Left
        String leftCode = code.get(ctx.expression(0));
        Pair<Type, Object> leftValue = values.get(ctx.expression(0));

        // Right
        String rightCode = code.get(ctx.expression(1));
        Pair<Type, Object> rightValue = values.get(ctx.expression(1));

        // Operator
        String operator = ctx.op.getText();
        String operatorType = "";
        Object value = null;

        switch (operator) {
            case "+" -> {
                operatorType = "add";

                if (leftValue.a == Type.FLOAT || rightValue.a == Type.FLOAT) {
                    value = NumberUtils.parseToFloat(leftValue.b) + NumberUtils.parseToFloat(rightValue.b);
                } else {
                    value = (int) leftValue.b + (int) rightValue.b;
                }
            }

            case "-" -> {
                operatorType = "sub";

                if (leftValue.a == Type.FLOAT || rightValue.a == Type.FLOAT) {
                    value = NumberUtils.parseToFloat(leftValue.b) - NumberUtils.parseToFloat(rightValue.b);
                } else {
                    value = (int) leftValue.b - (int) rightValue.b;
                }
            }

            case "*" -> {
                operatorType = "mul";

                if (leftValue.a == Type.FLOAT || rightValue.a == Type.FLOAT) {
                    value = NumberUtils.parseToFloat(leftValue.b) * NumberUtils.parseToFloat(rightValue.b);
                } else {
                    value = (int) leftValue.b * (int) rightValue.b;
                }
            }

            case "/" -> {
                operatorType = "div";

                if (leftValue.a == Type.FLOAT || rightValue.a == Type.FLOAT) {
                    value = NumberUtils.parseToFloat(leftValue.b) / NumberUtils.parseToFloat(rightValue.b);
                } else {
                    value = (int) leftValue.b / (int) rightValue.b;
                }
            }
        };

        instructions.add(leftCode);
        if (leftValue.a == Type.INT && rightValue.a == Type.FLOAT) {
            instructions.add("itof");
        }

        instructions.add(rightCode);
        if (rightValue.a == Type.INT && leftValue.a == Type.FLOAT) {
            instructions.add("itof");
        }

        instructions.add(operatorType);

        values.put(ctx, new Pair<>(Type.resultType(leftValue.a, rightValue.a, operator), value));
        code.put(ctx, String.join("\n", instructions));
    }

    @Override
    public void exitLogicExpression(LanguageParser.LogicExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();

        // Left
        String leftCode = code.get(ctx.expression(0));
        Pair<Type, Object> leftValue = values.get(ctx.expression(0));

        // Right
        String rightCode = code.get(ctx.expression(1));
        Pair<Type, Object> rightValue = values.get(ctx.expression(1));

        // Operator
        String operator = ctx.op.getText();
        Object value = switch (operator) {
            case "&&" -> (boolean) leftValue.b && (boolean) rightValue.b;
            case "||" -> (boolean) leftValue.b || (boolean) rightValue.b;
            default -> "Unknown operator";
        };

        instructions.add(leftCode);
        instructions.add(rightCode);
        instructions.add(operator.equalsIgnoreCase("&&") ? "and" : "or");

        values.put(ctx, new Pair<>(Type.BOOL, value));
        code.put(ctx, String.join("\n", instructions));
    }

    @Override
    public void exitParenExpression(LanguageParser.ParenExpressionContext ctx) {
        values.put(ctx, values.get(ctx.expression()));
        code.put(ctx, code.get(ctx.expression()));
    }

    @Override
    public void exitBlockStatement(LanguageParser.BlockStatementContext ctx) {
        List<String> instructions = new ArrayList<>();

        for (LanguageParser.StatementContext statement : ctx.statement()) {
            instructions.add(code.get(statement));
        }

        code.put(ctx, String.join("\n", instructions));
    }

    @Override
    public void exitWriteStatement(LanguageParser.WriteStatementContext ctx) {
        List<String> instructions = new ArrayList<>();
        int count = 0;

        for (LanguageParser.ExpressionContext expression : ctx.expression()) {
            String exprCode = code.get(expression);
            instructions.add(exprCode);
            count++;
        }

        instructions.add(STR . "print \{ count }");
        code.put(ctx, String.join("\n", instructions));
    }

    @Override
    public void exitVarExpression(LanguageParser.VarExpressionContext ctx) {
        code.put(ctx, STR . "load \{ ctx.VAR().getText() }");
        values.put(ctx, symbolTable.get(ctx.VAR().getText()));
    }

    @Override
    public void exitLiteralExpression(LanguageParser.LiteralExpressionContext ctx) {
        Type type = getLiteralType(ctx.literal());

        switch (type) {
            case INT -> {
                int value = Integer.parseInt(ctx.literal().INT().getText());
                values.put(ctx, new Pair<>(Type.INT, value));
                code.put(ctx, STR . "push I \{ value }");
            }

            case FLOAT -> {
                float value = Float.parseFloat(ctx.literal().FLOAT().getText());
                values.put(ctx, new Pair<>(Type.FLOAT, value));
                code.put(ctx, STR . "push F \{ value }");
            }

            case BOOL -> {
                boolean value = Boolean.parseBoolean(ctx.literal().BOOL().getText());
                values.put(ctx, new Pair<>(Type.BOOL, value));
                code.put(ctx, STR . "push B \{ value ? "true" : "false" }");
            }

            case STRING -> {
                String value = ctx.literal().STRING().getText();
                values.put(ctx, new Pair<>(Type.STRING, value));
                code.put(ctx, STR . "push S \{ value }");
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

        values.put(ctx, values.get(ctx.expression()));
        instructions.add(code.get(ctx.expression()));
        instructions.add("pop");

        code.put(ctx, String.join("\n", instructions));
    }

    boolean first = true;
    String firstText = "";

    @Override
    public void exitEmptyStatement(LanguageParser.EmptyStatementContext ctx) {
        code.put(ctx, "");
    }

    @Override
    public void exitAssignExpression(LanguageParser.AssignExpressionContext ctx) {
        List<String> instructions = new ArrayList<>();
        instructions.add(code.get(ctx.expression()));

        String varName = ctx.VAR().getText();
        Pair<Type, Object> varValue = symbolTable.get(varName);
        Pair<Type, Object> exprValue = values.get(ctx.expression());

        if (varValue.a == Type.FLOAT && exprValue.a == Type.INT) {
            instructions.add("itof");
        }

        values.put(ctx, exprValue);
        symbolTable.put(varName, exprValue);

        instructions.add(STR . "save \{ varName }");
        instructions.add(STR . "load \{ varName }");

        // Pop the value from the stack
        if (ctx.expression().getText().equals(firstText) && !first) {
            first = true;
            instructions.add("pop");
        }

        code.put(ctx, String.join("\n", instructions));
    }

    @Override
    public void enterAssignExpression(LanguageParser.AssignExpressionContext ctx) {
        if (first) {
            first = false;
            firstText = ctx.expression().getText();
        }
    }
}
