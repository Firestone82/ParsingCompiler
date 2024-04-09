package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class NotTests extends TestClass {

    @Test
    public void testLogicNot() {
        String input = """
            !false;
        """;

        String compiled = """
            push B false
            not
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicNotInt() {
        String input = """
            int a;
            !a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicNotFloat() {
        String input = """
            float a;
            !a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicNotString() {
        String input = """
            string a;
            !a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicNotBool() {
        String input = """
            bool a;
            !a;
        """;

        String compiled = """
            push B false
            save a
            load a
            not
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
