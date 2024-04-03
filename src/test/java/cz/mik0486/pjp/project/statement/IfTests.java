package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IfTests extends TestClass {

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
    public void testIf() {
        String input = """
            if (true) {
                int a;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testIfElse() {
        String input = """
            if (true) {
                int a;
            } else {
                float b;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testIfElseIf() {
        String input = """
            if (true) {
                int a;
            } else if (false) {
                float b;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testIfElseIfElse() {
        String input = """
            if (true) {
                int a;
            } else if (false) {
                float b;
            } else {
                string c;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testIfCondition() {
        String input = """
            int a;
            if (a == 5) {
                int b;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }

    @Test
    public void testIfElseConditionNoDeclaration() {
        String input = """
            if (a == 5) {
                int b;
            } else {
                float c;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testIfWrongCondition() {
        String input = """
            if (a = 5) {
                int b;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testIfNotBooleanCondition() {
        String input = """
            if ("ahoj") {
                int b;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }

    @Test
    public void testComplexIf() {
        String input = """
            int x;
            x = 5;
            if (x > 0 && x < 10) {
                int a;
            } else if (x > 10 && x < 20) {
                float b;
            } else {
                string c;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertTrue(program.init());
    }
}
