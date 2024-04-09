package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class ReadTests extends TestClass {

    @Test
    public void testReadInt() {
        String input = """
            int a;
            read a;
        """;

        String compiled = """
            push I 0
            save a
            read I
            save a
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadFloat() {
        String input = """
            float a;
            read a;
        """;

        String compiled = """
            push F 0.0
            save a
            read F
            save a
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadString() {
        String input = """
            string a;
            read a;
        """;

        String compiled = """
            push S ""
            save a
            read S
            save a
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadBool() {
        String input = """
            bool a;
            read a;
        """;

        String compiled = """
            push B false
            save a
            read B
            save a
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadMultipleVariables() {
        String input = """
            int a;
            float b;
            string c;
            bool d;
            read a, b, c, d;
        """;

        String compiled = """
            push I 0
            save a
            push F 0.0
            save b
            push S ""
            save c
            push B false
            save d
            read I
            save a
            read F
            save b
            read S
            save c
            read B
            save d
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadNoDeclaration() {
        String input = """
            read a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
