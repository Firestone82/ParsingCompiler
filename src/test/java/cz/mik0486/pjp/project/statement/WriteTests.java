package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriteTests {

    @AfterEach
    public void reset() {
        ErrorLogger.clearErrors();
    }

    @Test
    public void testWriteInt() {
        String input = """
            int a;
            write a;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testWriteFloat() {
        String input = """
            float a;
            write a;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testWriteString() {
        String input = """
            string a;
            write a;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testWriteBool() {
        String input = """
            bool a;
            write a;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testWriteMultipleVariables() {
        String input = """
            int a;
            float b;
            string c;
            bool d;
            write a, b, c, d;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testWriteNoDeclaration() {
        String input = """
            write a;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testWriteNonsense() {
        String input = """
            write "ahoj" > 5;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testWriteWord() {
        String input = """
            write "ahoj";
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }
}
