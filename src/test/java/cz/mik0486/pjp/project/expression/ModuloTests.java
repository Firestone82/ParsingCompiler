package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class ModuloTests extends TestClass {

    @Test
    public void testModulo() {
        String input = """
            3 % 2;
        """;

        String compiled = """
            push I 3
            push I 2
            mod
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testModuloInt() {
        String input = """
            int a;
            int b;
            a % b;
        """;

        String compiled = """
            push I 0
            save a
            push I 0
            save b
            load a
            load b
            mod
            pop
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testModuloFloat() {
        String input = """
            float a;
            float b;
            a % b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testModuloString() {
        String input = """
            string a;
            string b;
            a % b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testModuloBool() {
        String input = """
            bool a;
            bool b;
            a % b;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
