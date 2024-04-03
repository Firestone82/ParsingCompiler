package cz.mik0486.pjp.project.antlr;

import cz.mik0486.pjp.project.antlr.gen.LanguageBaseListener;
import cz.mik0486.pjp.project.antlr.gen.LanguageListener;
import cz.mik0486.pjp.project.antlr.gen.LanguageParser;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LanguageAAAA extends LanguageBaseListener implements LanguageListener {
    private final Map<String, Pair<Type, Object>> symbolTable = new HashMap<>();
    private final ParseTreeProperty<Pair<Type, Object>> values = new ParseTreeProperty<>();
    private final ParseTreeProperty<String> code = new ParseTreeProperty<>();

    @Override
    public void exitProgram(LanguageParser.ProgramContext ctx) {
        StringBuilder sb = new StringBuilder();

        for (LanguageParser.StatementContext statement : ctx.statement()) {
            Arrays.stream(code.get(statement).split("\n")).forEach(log::info);
            sb.append(code.get(statement));
            sb.append("\n");
        }
    }

    @Override
    public void exitVarDeclStatement(LanguageParser.VarDeclStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        String type = code.get(ctx.TYPE());

        for (TerminalNode var : ctx.VAR()) {
            sb.append(type);
            sb.append(STR . "save \{ var.getText() } \n");
        }

        code.put(ctx, sb.toString());
    }

    @Override
    public void exitWriteStatement(LanguageParser.WriteStatementContext ctx) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (LanguageParser.ExpressionContext expression : ctx.expression()) {
            Object value = values.get(expression).b;
            String exprCode = code.get(expression);
            sb.append(exprCode);
            count++;
        }

        sb.append(STR . "print \{ count }");
        code.put(ctx, sb.append("\n").toString());
    }

    @Override
    public void exitVarExpression(LanguageParser.VarExpressionContext ctx) {
        code.put(ctx, STR . "load \{ ctx.VAR().getText() } \n");
        values.put(ctx, symbolTable.get(ctx.VAR().getText()));
    }

    @Override
    public void exitLiteralExpression(LanguageParser.LiteralExpressionContext ctx) {
        if (ctx.literal().INT() != null) {
            int value = Integer.parseInt(ctx.literal().INT().getText());
            values.put(ctx, new Pair<>(Type.INT, value));
            code.put(ctx, STR . "push I \{ value } \n");
        }

        if (ctx.literal().FLOAT() != null) {
            float value = Float.parseFloat(ctx.literal().FLOAT().getText());
            values.put(ctx, new Pair<>(Type.FLOAT, value));
            code.put(ctx, STR . "push F \{ value } \n");
        }

        if (ctx.literal().BOOL() != null) {
            boolean value = Boolean.parseBoolean(ctx.literal().BOOL().getText());
            values.put(ctx, new Pair<>(Type.BOOL, value));
            code.put(ctx, STR . "push B \{ value ? "true" : "false" } \n");
        }

        if (ctx.literal().STRING() != null) {
            String value = ctx.literal().STRING().getText();
            values.put(ctx, new Pair<>(Type.STRING, value));
            code.put(ctx, STR . "push S \{ value } \n");
        }
    }

    @Override
    public void exitAssignExpression(LanguageParser.AssignExpressionContext ctx) {

    }
}
