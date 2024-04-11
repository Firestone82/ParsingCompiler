package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class LogicAndOrTests extends TestClass {

    @Test
    public void testLogicAnd() {
        String input = """
            true && false;
        """;

        String compiled = """
            push B true
            push B false
            and
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicOr() {
        String input = """
            true || false;
        """;

        String compiled = """
            push B true
            push B false
            or
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicAndInt() {
        String input = """
            int a;
            int b;
            a && b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicAndFloat() {
        String input = """
            float a;
            float b;
            a && b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicAndString() {
        String input = """
            string a;
            string b;
            a && b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicAndBool() {
        String input = """
            bool a;
            bool b;
            a && b;
        """;

        String compiled = """
            push B false
            save a
            push B false
            save b
            load a
            load b
            and
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
