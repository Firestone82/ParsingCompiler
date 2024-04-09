package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class WhileTests extends TestClass {

    @Test
    public void testWriteInt() {
        String input = """
            int a;
            write a;
        """;

        String compiled = """
            TODO
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusNoDeclaration() {
        String input = """
            -a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusInt() {
        String input = """
            int a;
            -a;
        """;

        String compiled = """
            TODO
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusFloat() {
        String input = """
            float a;
            -a;
        """;

        String compiled = """
            TODO
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusString() {
        String input = """
            string a;
            -a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusBool() {
        String input = """
            bool a;
            -a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
