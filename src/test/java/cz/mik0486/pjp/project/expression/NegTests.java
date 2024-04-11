package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class NegTests extends TestClass {

    @Test
    public void testUnaryMinusInt() {
        String code = """
            write -1;
        """;

        String compiled = """
            push I 1
            uminus
            print 1
        """;

        String input = """        
        """;

        String output = """
            -1
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusFloat() {
        String code = """
            write -1.0;
        """;

        String compiled = """
            push F 1.0
            uminus
            print 1
        """;

        String input = """        
        """;

        String output = """
            -1.0
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusBool() {
        String code = """
            -true;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testUnaryMinusString() {
        String code = """
            -"string";
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
