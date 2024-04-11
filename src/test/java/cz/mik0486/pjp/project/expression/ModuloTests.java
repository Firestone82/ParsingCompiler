package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ModuloTests extends TestClass {

    @Test
    public void testModulo() {
        String code = """
            write 3 % 2;
        """;

        String compiled = """
            push I 3
            push I 2
            mod
            print 1
        """;

        String input = """        
        """;

        String output = """
            1
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testModuloInt() {
        String code = """
            int a;
            a = 3;
            int b;
            b = 2;
            write a % b;
        """;

        String compiled = """
            push I 0
            save a
            push I 3
            save a
            load a
            pop
            push I 0
            save b
            push I 2
            save b
            load b
            pop
            load a
            load b
            mod
            print 1
        """;

        String input = """        
        """;

        String output = """
            1
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testModuloFloat() {
        String code = """
            float a;
            float b;
            a % b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testModuloString() {
        String code = """
            string a;
            string b;
            a % b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testModuloBool() {
        String code = """
            bool a;
            bool b;
            a % b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
