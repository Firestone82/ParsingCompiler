package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class ArithmeticTests extends TestClass {

    @Test
    public void testAddition() {
        String input = """
            2 + 2;
        """;

        String compiled = """
            push I 2
            push I 2
            add
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testSubtraction() {
        String input = """
            2 - 2;
        """;

        String compiled = """
            push I 2
            push I 2
            sub
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testMultiplication() {
        String input = """
            2 * 2;
        """;

        String compiled = """
            push I 2
            push I 2
            mul
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testDivision() {
        String input = """
            2 / 2;
        """;

        String compiled = """
            push I 2
            push I 2
            div
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testInt() {
        String input = """
            int a;
            int b;
            a + b;
        """;

        String compiled = """
            push I 0
            save a
            push I 0
            save b
            load a
            load b
            add
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testFloat() {
        String input = """
            float a;
            float b;
            a + b;
        """;

        String compiled = """
            push F 0.0
            save a
            push F 0.0
            save b
            load a
            load b
            add
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testRecast() {
        String input = """
            int a;
            float b;
            a + b;
        """;

        String compiled = """
            push I 0
            save a
            push F 0.0
            save b
            load a
            itof
            load b
            add
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testString() {
        String input = """
            string a;
            string b;
            a + b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBool() {
        String input = """
            bool a;
            bool b;
            a + b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testNotDeclared() {
        String input = """
            a + b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
