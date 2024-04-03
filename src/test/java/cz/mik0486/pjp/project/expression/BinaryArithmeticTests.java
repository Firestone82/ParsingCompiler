package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinaryArithmeticTests extends TestClass {

    @Test
    public void testAddition() {
        String input = """
            2 + 2;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testSubtraction() {
        String input = """
            2 - 2;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testMultiplication() {
        String input = """
            2 * 2;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testDivision() {
        String input = """
            2 / 2;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testInt() {
        String input = """
            int a;
            int b;
            a + b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testFloat() {
        String input = """
            float a;
            float b;
            a + b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testRecast() {
        String input = """
            int a;
            float b;
            a + b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testString() {
        String input = """
            string a;
            string b;
            a + b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testBool() {
        String input = """
            bool a;
            bool b;
            a + b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testNotDeclared() {
        String input = """
            a + b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }
}
