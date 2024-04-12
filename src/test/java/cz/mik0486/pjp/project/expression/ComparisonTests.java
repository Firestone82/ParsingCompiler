package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ComparisonTests extends TestClass {

    @Test
    public void testEqual() {
        String code = """
            write 2 == 2;
        """;

        String compiled = """
            push I 2
            push I 2
            eq
            print 1
        """;

        String input = """        
        """;

        String output = """
            true
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testNotEqual() {
        String code = """
            write 2 != 2;
        """;

        String compiled = """
            push I 2
            push I 2
            eq
            not
            print 1
        """;

        String input = """        
        """;

        String output = """
            false
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testInt() {
        String code = """
            int a;
            int b;
            write a == b;
        """;

        String compiled = """
            push I 0
            save a
            push I 0
            save b
            load a
            load b
            eq
            print 1
        """;

        String input = """        
        """;

        String output = """
            true
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testFloat() {
        String code = """
            float a;
            float b;
            write a == b;
        """;

        String compiled = """
            push F 0.0
            save a
            push F 0.0
            save b
            load a
            load b
            eq
            print 1
        """;

        String input = """        
        """;

        String output = """
            true
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testString() {
        String code = """
            string a;
            string b;
            write a == b;
        """;

        String compiled = """
            push S ""
            save a
            push S ""
            save b
            load a
            load b
            eq
            print 1
        """;

        String input = """        
        """;

        String output = """
            true
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBool() {
        String code = """
            bool a;
            bool b;
            a == b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
