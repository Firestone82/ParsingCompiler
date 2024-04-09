package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class WriteTests extends TestClass {

    @Test
    public void testWriteInt() {
        String input = """
            int a;
            write a;
        """;

        String compiled = """
            push I 0
            save a
            load a
            print 1
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteFloat() {
        String input = """
            float a;
            write a;
        """;

        String compiled = """
            push F 0.0
            save a
            load a
            print 1
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteString() {
        String input = """
            string a;
            write a;
        """;

        String compiled = """
            push S ""
            save a
            load a
            print 1
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteBool() {
        String input = """
            bool a;
            write a;
        """;

        String compiled = """
            push B false
            save a
            load a
            print 1
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteMultipleVariables() {
        String input = """
            int a;
            float b;
            string c;
            bool d;
            write a, b, c, d;
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
            load a
            load b
            load c
            load d
            print 4
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteNoDeclaration() {
        String input = """
            write a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteNonsense() {
        String input = """
            write "ahoj" > 5;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteWord() {
        String input = """
            write "ahoj";
        """;

        String compiled = """
            push S "ahoj"
            print 1
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
