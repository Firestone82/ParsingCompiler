package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class ConcatTests extends TestClass {

    @Test
    public void testConcatenation() {
        String input = """
            "string" . "string";
        """;

        String compiled = """
            push S "string"
            push S "string"
            concat
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testConcatenationInt() {
        String input = """
            1 . 2;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testConcatenationFloat() {
        String input = """
            1.0 . 2.0;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testConcatenationBool() {
        String input = """
            true . false;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
