package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.Program;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TernaryTests extends TestClass {

    @Test
    public void testTernary() {
        String input = """
            int a;
            int b;
            int c;
            a = 1;
            b = 2;
            c = (a > b ? a : b);
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testTernaryWithoutParentheses() {
        String input = """
            int a;
            int b;
            int c;
            a = 1;
            b = 2;
            c = a > b ? a : b;
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testTernaryIntFloat() {
        String input = """
            int a;
            float b;
            float c;
            a = 1;
            b = 2.0;
            c = (a > b ? a : b);
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testTernaryWrongCondition() {
        String input = """
            string a;
            float b;
            int c;
            a = "ahoj";
            b = 2.0;
            c = (a ? a : b);
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testTernaryWrongAssignment() {
        String input = """
            int a;
            int b;
            string c;
            a = 1;
            b = 2;
            c = (a > b ? a : b);
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testTernaryWrongType() {
        String input = """
            int a;
            string b;
            int c;
            a = 1;
            b = "ahoj";
            c = (true ? a : b);
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }
}
