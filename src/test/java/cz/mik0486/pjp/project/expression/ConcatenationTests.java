package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConcatenationTests {

    @AfterEach
    public void reset() {
        ErrorLogger.clearErrors();
    }

    @Test
    public void testModulo() {
        String input = """
            3 % 2;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }
    @Test
    public void testConcatenation() {
        String input = """
            "string" . "string";
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testConcatenationInt() {
        String input = """
            1 . 2;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testConcatenationFloat() {
        String input = """
            1.0 . 2.0;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testConcatenationBool() {
        String input = """
            true . false;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testConcatenationString() {
        String input = """
            "string" . "string";
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }
}
