package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class ParenthesisTests extends TestClass {

    @Test
    public void testParenthesis() {
        String input = """
            (true);
        """;

        String compiled = """
            push B true
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testParenthesisInt() {
        String input = """
            int a;
            (a);
        """;

        String compiled = """
            push I 0
            save a
            load a
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testParenthesisFloat() {
        String input = """
            float a;
            (a);
        """;

        String compiled = """
            push F 0.0
            save a
            load a
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testParenthesisString() {
        String input = """
            string a;
            (a);
        """;

        String compiled = """
            push S ""
            save a
            load a
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testParenthesisBool() {
        String input = """
            bool a;
            (a);
        """;

        String compiled = """
            push B false
            save a
            load a
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testParenthesisOperation() {
        String input = """
            (true && false);
        """;

        String compiled = """
            push B true
            push B false
            and
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
