package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class NegTests extends TestClass {

    @Test
    public void testUnaryMinusInt() {
        String input = """
            -1;
        """;

        String compiled = """
            push I 1
            uminus
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusFloat() {
        String input = """
            -1.0;
        """;

        String compiled = """
            push F 1.0
            uminus
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusBool() {
        String input = """
            -true;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusString() {
        String input = """
            -"string";
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
