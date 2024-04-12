package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class RelationalOperatorsTests extends TestClass {

    @Test
    public void testGreater() {
        String code = """
            1 > 2;
        """;

        String compiled = """
            push I 1
            push I 2
            gt
            pop
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLess() {
        String code = """
            1 < 2;
        """;

        String compiled = """
            push I 1
            push I 2
            lt
            pop
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testInt() {
        String code = """
            int a;
            int b;
            a > b;
        """;

        String compiled = """
            push I 0
            save a
            push I 0
            save b
            load a
            load b
            gt
            pop
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testFloat() {
        String code = """
            float a;
            float b;
            a > b;
        """;

        String compiled = """
            push F 0.0
            save a
            push F 0.0
            save b
            load a
            load b
            gt
            pop
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testString() {
        String code = """
            string a;
            string b;
            a > b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBool() {
        String code = """
            bool a;
            bool b;
            a > b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
