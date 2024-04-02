package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModuloTests {

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
    public void testModuloInt() {
        String input = """
            int a;
            int b;
            a % b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testModuloFloat() {
        String input = """
            float a;
            float b;
            a % b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testModuloString() {
        String input = """
            string a;
            string b;
            a % b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testModuloBool() {
        String input = """
            bool a;
            bool b;
            a % b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }
}
