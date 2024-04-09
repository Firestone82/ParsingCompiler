package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class AssignmentTests extends TestClass {

    @Test
    public void testAssignInt() {
        String input = """
            int a;
            a = 5;
        """;

        String compiled = """
            push I 0
            save a
            push I 5
            save a
            load a
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testAssignFloat() {
        String input = """
            float a;
            a = 5.0;
        """;

        String compiled = """
            push F 0.0
            save a
            push F 5.0
            save a
            load a
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testAssignString() {
        String input = """
            string a;
            a = "hello";
        """;

        String compiled = """
            push S ""
            save a
            push S "hello"
            save a
            load a
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testAssignBool() {
        String input = """
            bool a;
            a = true;
        """;

        String compiled = """
            push B false
            save a
            push B true
            save a
            load a
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testAssignMixed() {
        String input = """
            int a;
            a = 0.9;
            
            float b;
            b = 5;
        """;


        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testAssignIntToFloat() {
        String input = """
            float a;
            a = 5;
        """;

        String compiled = """
            push F 0.0
            save a
            push I 5
            itof
            save a
            load a
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
