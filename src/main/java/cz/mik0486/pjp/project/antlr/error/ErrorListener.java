package cz.mik0486.pjp.project.antlr.error;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

@Slf4j
public class ErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        log.error("Rule Stack: " + String.join(", ", ((Parser) recognizer).getRuleInvocationStack().reversed()));
        log.error("Line " + line + ":" + charPositionInLine + " at " + offendingSymbol + ": " + msg);
    }
}
