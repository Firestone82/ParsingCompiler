package cz.mik0486.pjp.project.antlr.error;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
public class ErrorLogger {
    private static ErrorLogger INSTANCE;
    private final List<String> errors = new ArrayList<>();

    private static synchronized ErrorLogger getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ErrorLogger();
        }

        return INSTANCE;
    }

    public static void addError(ParserRuleContext ctx, Token token, String error, Object... args) {
        int lineNum = ctx.getStart().getLine();
        int charPos = 0;

        if (token != null) {
            charPos = token.getCharPositionInLine();
        } else {
            charPos = ctx.getStart().getCharPositionInLine();
        }

        String message = error.formatted(args);
        getInstance().errors.add("""
                (.num.). Type error at line %d:%d! %s
                |  - Statement: %s
                |               %s
                """.formatted(lineNum, charPos, message, ctx.getText(), " ".repeat(charPos) + "^"));
    }

    public static void clearErrors() {
        getInstance().errors.clear();
    }

    public static boolean hasErrors() {
        return !getInstance().errors.isEmpty();
    }

    public static List<String> getErrors() {
        return getInstance().errors;
    }

    public static void printErrors() {
        log.error("Found total %d errors: ".formatted(getInstance().errors.size()));
        int i = 0;

        for (String error : getInstance().errors) {
            error = error.replace("(.num.)", String.valueOf(++i));
            Arrays.stream(error.split("\n")).forEach(log::error);
        }
    }
}
