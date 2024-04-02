package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpressionTests {

    @AfterEach
    public void reset() {
        ErrorLogger.clearErrors();
    }

    @Test
    public void testExpression() {
        String input = """
            1 + 2;
            1 - 2;
            1 * 2;
            1 / 2;
            1 % 2;
            1 == 2;
            1 != 2;
            1 < 2;
            1 > 2;
            true && true;
            true || false;
            !true;
            -1;
            +1;
            1;
            1.0;
            "string";
            true;
            false;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }
}
