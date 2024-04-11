package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ArithmeticTests extends TestClass {

    @Test
    public void testAddition() {
        String code = """
            write 2 + 2;
        """;

        String compiled = """
            push I 2
            push I 2
            add
            print 1
        """;

        String input = """        
        """;

        String output = """
            4
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testSubtraction() {
        String code = """
            write 2 - 2;
        """;

        String compiled = """
            push I 2
            push I 2
            sub
            print 1
        """;

        String input = """        
        """;

        String output = """
            0
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testMultiplication() {
        String code = """
            write 2 * 2;
        """;

        String compiled = """
            push I 2
            push I 2
            mul
            print 1
        """;

        String input = """        
        """;

        String output = """
            4
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testDivision() {
        String code = """
            write 2 / 2;
        """;

        String compiled = """
            push I 2
            push I 2
            div
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
    public void testInt() {
        String code = """
            int a;
            int b;
            write a + b;
        """;

        String compiled = """
            push I 0
            save a
            push I 0
            save b
            load a
            load b
            add
            print 1
        """;

        String input = """        
        """;

        String output = """
            0
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testFloat() {
        String code = """
            float a;
            float b;
            write a + b;
        """;

        String compiled = """
            push F 0.0
            save a
            push F 0.0
            save b
            load a
            load b
            add
            print 1
        """;

        String input = """        
        """;

        String output = """
            0.0
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testRecast() {
        String code = """
            int a;
            float b;
            write a + b;
        """;

        String compiled = """
            push I 0
            save a
            push F 0.0
            save b
            load a
            itof
            load b
            add
            print 1
        """;

        String input = """        
        """;

        String output = """
            0.0
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testString() {
        String code = """
            string a;
            string b;
            a + b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBool() {
        String code = """
            bool a;
            bool b;
            a + b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testNotDeclared() {
        String code = """
            a + b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
