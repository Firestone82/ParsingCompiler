package cz.mik0486.pjp.project.antlr;

import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import cz.mik0486.pjp.project.gen.LanguageBaseVisitor;
import cz.mik0486.pjp.project.gen.LanguageParser;
import cz.mik0486.pjp.project.gen.LanguageVisitor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LanguageTypeVisitor extends LanguageBaseVisitor<Type> implements LanguageVisitor<Type> {
    private final Map<String, Type> symbolTable = new HashMap<>();

    @Override
    public Type visitVarDeclStatement(LanguageParser.VarDeclStatementContext ctx) {
        Type type = Type.valueOf(ctx.TYPE().getText().toUpperCase());
        ctx.VAR().forEach(var -> symbolTable.put(var.getText(), type));
        return null;
    }

    @Override
    public Type visitAssignExpression(LanguageParser.AssignExpressionContext ctx) {
        String varName = ctx.VAR().getText();
        Type varType = symbolTable.get(varName);
        Type exprType = visit(ctx.expression());

        if (varType == Type.INT && exprType == Type.FLOAT) {
            return Type.FLOAT;
        } else if (varType == Type.FLOAT && exprType == Type.INT) {
            return Type.INT;
        }

        if (!varType.equals(exprType)) {
            ErrorLogger.addError(ctx, "Trying to assign %s to %s", exprType, varType);
        }

        return varType;
    }

    @Override
    public Type visitBinaryExpression(LanguageParser.BinaryExpressionContext ctx) {
        Type leftType = visit(ctx.expression(0));
        Type rightType = visit(ctx.expression(1));

        if (Type.resultType(leftType, rightType, ctx.operator().getText()) == Type.ERROR) {
            ErrorLogger.addError(ctx, "Incompatible types %s and %s", leftType, rightType);
        }

        return leftType;
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

        ErrorLogger.addError(ctx, "Unknown literal type %s", ctx.literal().getText());
        return Type.ERROR;
    }

    @Override
    public Type visitVarExpression(LanguageParser.VarExpressionContext ctx) {
        String varName = ctx.VAR().getText();
        if (symbolTable.containsKey(varName)) {
            return symbolTable.get(varName);
        }

        ErrorLogger.addError(ctx, "Variable %s not declared", varName);
        return Type.ERROR;
    }
}