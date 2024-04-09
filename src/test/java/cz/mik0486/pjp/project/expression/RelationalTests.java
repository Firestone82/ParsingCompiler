package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class RelationalTests extends TestClass {

    @Test
    public void testGreater() {
        String input = """
            1 > 2;
        """;

        String compiled = """
            push I 1
            push I 2
            gt
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLess() {
        String input = """
            1 < 2;
        """;

        String compiled = """
            push I 1
            push I 2
            lt
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testInt() {
        String input = """
            int a;
            int b;
            a > b;
        """;

        String compiled = """
            push I 0
            save a
            push I 0
            save b
            load a
            load b
            gt
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testFloat() {
        String input = """
            float a;
            float b;
            a > b;
        """;

        String compiled = """
            push F 0.0
            save a
            push F 0.0
            save b
            load a
            load b
            gt
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testString() {
        String input = """
            string a;
            string b;
            a > b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBool() {
        String input = """
            bool a;
            bool b;
            a > b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
