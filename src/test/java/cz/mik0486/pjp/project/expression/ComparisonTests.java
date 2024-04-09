package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class ComparisonTests extends TestClass {

    @Test
    public void testEqual() {
        String input = """
            2 == 2;
        """;

        String compiled = """
            push I 2
            push I 2
            eq
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testNotEqual() {
        String input = """
            2 != 2;
        """;

        String compiled = """
            push I 2
            push I 2
            eq
            not
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testInt() {
        String input = """
            int a;
            int b;
            a == b;
        """;

        String compiled = """
            push I 0
            save a
            push I 0
            save b
            load a
            load b
            eq
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testFloat() {
        String input = """
            float a;
            float b;
            a == b;
        """;

        String compiled = """
            push F 0.0
            save a
            push F 0.0
            save b
            load a
            load b
            eq
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testString() {
        String input = """
            string a;
            string b;
            a == b;
        """;

        String compiled = """
            push S ""
            save a
            push S ""
            save b
            load a
            load b
            eq
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBool() {
        String input = """
            bool a;
            bool b;
            a == b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
