package cz.mik0486.pjp.project.antlr;

import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import cz.mik0486.pjp.project.antlr.gen.LanguageBaseVisitor;
import cz.mik0486.pjp.project.antlr.gen.LanguageParser;
import cz.mik0486.pjp.project.antlr.gen.LanguageVisitor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LanguageTypeVisitor extends LanguageBaseVisitor<Type> implements LanguageVisitor<Type> {
    private final Map<String, Type> symbolTable = new HashMap<>();

    @Override
    public Type visitTernaryExpression(LanguageParser.TernaryExpressionContext ctx) {
        Type conditionType = visit(ctx.expression(0));
        Type trueType = visit(ctx.expression(1));
        Type falseType = visit(ctx.expression(2));

        if (conditionType != Type.BOOL) {
            ErrorLogger.addError(ctx, ctx.expression(0).getStart(), "Condition must be of type BOOL, got %s", conditionType);
        }

        if (trueType == Type.INT && falseType == Type.FLOAT) {
            return Type.FLOAT;
        }

        if (trueType != falseType) {
            ErrorLogger.addError(ctx, ctx.split, "Incompatible types %s and %s", trueType, falseType);
        }

        return trueType;
    }

    @Override
    public Type visitVarDeclStatement(LanguageParser.VarDeclStatementContext ctx) {
        Type type = Type.valueOf(ctx.TYPE().getText().toUpperCase());
        boolean errored = false;

        for (TerminalNode terminal : ctx.VAR()) {
            if (symbolTable.containsKey(terminal.getText())) {
                ErrorLogger.addError(ctx, terminal.getSymbol(), "Variable %s already declared", terminal.getText());
                errored = true;
            }

            symbolTable.put(terminal.getText(), type);
        }

        return errored ? Type.ERROR : type;
    }

    @Override
    public Type visitReadStatement(LanguageParser.ReadStatementContext ctx) {
        boolean errored = false;

        for (TerminalNode terminal : ctx.VAR()) {
            if (!symbolTable.containsKey(terminal.getText())) {
                ErrorLogger.addError(ctx, terminal.getSymbol(), "Variable %s not declared", terminal.getText());
                errored = true;
            }
        }

        return errored ? Type.ERROR : Type.VOID;
    }

    /**
     * ======================
     * CONDITION
     * ======================
     */

    @Override
    public Type visitBoolCondition(LanguageParser.BoolConditionContext ctx) {
        Type conditionType = visit(ctx.expression());
        if (conditionType != Type.BOOL) {
            ErrorLogger.addError(ctx, null,"Condition must be of type BOOL, got %s", conditionType);
        }

        return Type.BOOL;
    }

    /**
     * ======================
     * EXPRESSIONS
     * ======================
     */

    @Override
    public Type visitAritmExpression(LanguageParser.AritmExpressionContext ctx) {
        Type leftType = visit(ctx.expression(0));
        Type rightType = visit(ctx.expression(1));

        return checkExpression(leftType, rightType, ctx.op.getText(), ctx, ctx.op);
    }

    @Override
    public Type visitModuloExpression(LanguageParser.ModuloExpressionContext ctx) {
        Type leftType = visit(ctx.expression(0));
        Type rightType = visit(ctx.expression(1));

        return checkExpression(leftType, rightType, ctx.op.getText(), ctx, ctx.op);
    }

    @Override
    public Type visitConcatExpression(LanguageParser.ConcatExpressionContext ctx) {
        Type leftType = visit(ctx.expression(0));
        Type rightType = visit(ctx.expression(1));

        return checkExpression(leftType, rightType, ctx.op.getText(), ctx, ctx.op);
    }

    @Override
    public Type visitRelationExpression(LanguageParser.RelationExpressionContext ctx) {
        Type leftType = visit(ctx.expression(0));
        Type rightType = visit(ctx.expression(1));

        return checkExpression(leftType, rightType, ctx.op.getText(), ctx, ctx.op);
    }

    @Override
    public Type visitComparisonExpression(LanguageParser.ComparisonExpressionContext ctx) {
        Type leftType = visit(ctx.expression(0));
        Type rightType = visit(ctx.expression(1));

        return checkExpression(leftType, rightType, ctx.op.getText(), ctx, ctx.op);
    }

    @Override
    public Type visitLogicExpression(LanguageParser.LogicExpressionContext ctx) {
        Type leftType = visit(ctx.expression(0));
        Type rightType = visit(ctx.expression(1));

        return checkExpression(leftType, rightType, ctx.op.getText(), ctx, ctx.op);
    }

    @Override
    public Type visitNotExpression(LanguageParser.NotExpressionContext ctx) {
        Type exprType = visit(ctx.expression());

        Type result = Type.resultType(exprType, ctx.op.getText());
        if (result == Type.ERROR) {
            ErrorLogger.addError(ctx, ctx.op, "Incompatible type %s, must be bool!", exprType);
        }

        return result;
    }

    @Override
    public Type visitNegExpression(LanguageParser.NegExpressionContext ctx) {
        Type exprType = visit(ctx.expression());

        Type result = Type.resultType(exprType, ctx.op.getText());
        if (result == Type.ERROR) {
            ErrorLogger.addError(ctx, ctx.op, "Incompatible type %s, must be int or float!", exprType);
        }

        return result;
    }

    private Type checkExpression(Type left, Type right, String operator, LanguageParser.ExpressionContext ctx, Token op) {
        Type result = Type.resultType(left, right, operator);
        if (result == Type.ERROR) {
            ErrorLogger.addError(ctx, op, "Incompatible types %s and %s", left, right);
        }

        return result;
    }

    @Override
    public Type visitVarExpression(LanguageParser.VarExpressionContext ctx) {
        String varName = ctx.VAR().getText();

        if (symbolTable.containsKey(varName)) {
            return symbolTable.get(varName);
        }

        ErrorLogger.addError(ctx, ctx.VAR().getSymbol(),"Variable %s not declared", varName);
        return Type.ERROR;
    }

    @Override
    public Type visitLiteralExpression(LanguageParser.LiteralExpressionContext ctx) {
        if (ctx.literal().INT() != null) {
            return Type.INT;
        } else if (ctx.literal().FLOAT() != null) {
            return Type.FLOAT;
        } else if (ctx.literal().BOOL() != null) {
            return Type.BOOL;
        } else if (ctx.literal().STRING() != null) {
            return Type.STRING;
        }

        ErrorLogger.addError(ctx, ctx.literal().start,"Unknown literal type %s", ctx.literal().getText());
        return Type.ERROR;
    }

    @Override
    public Type visitAssignExpression(LanguageParser.AssignExpressionContext ctx) {
        String varName = ctx.VAR().getText();
        Type varType = symbolTable.getOrDefault(varName, Type.ERROR);
        Type exprType = visit(ctx.expression());

        if (varType == Type.FLOAT && exprType == Type.INT) {
            return Type.INT;
        }

        if (!varType.equals(exprType)) {
            ErrorLogger.addError(ctx, null, "Trying to assign %s to %s", exprType, varType);
        }

        return varType;
    }

    @Override
    public Type visitParenExpression(LanguageParser.ParenExpressionContext ctx) {
        return visit(ctx.expression());
    }
}