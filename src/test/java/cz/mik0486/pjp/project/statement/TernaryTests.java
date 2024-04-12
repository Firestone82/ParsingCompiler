package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class TernaryTests extends TestClass {

    @Test
    public void testTernary() {
        String code = """
            int a;
            int b;
            int c;
            a = 1;
            b = 2;
            c = (a > b ? a : b);
        """;

        String compiled = """
            TODO
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, null, new Scanner(StringUtils.stripSpaces(input)), null, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testTernaryWithoutParentheses() {
        String code = """
            int a;
            int b;
            int c;
            a = 1;
            b = 2;
            c = a > b ? a : b;
        """;

        String compiled = """
            TODO
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, null, new Scanner(StringUtils.stripSpaces(input)), null, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testTernaryIntFloat() {
        String code = """
            int a;
            float b;
            float c;
            a = 1;
            b = 2.0;
            c = (a > b ? a : b);
        """;

        String compiled = """
            TODO
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, null, new Scanner(StringUtils.stripSpaces(input)), null, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testTernaryWrongCondition() {
        String code = """
            string a;
            float b;
            int c;
            a = "ahoj";
            b = 2.0;
            c = (a ? a : b);
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testTernaryWrongAssignment() {
        String code = """
            int a;
            int b;
            string c;
            a = 1;
            b = 2;
            c = (a > b ? a : b);
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testTernaryWrongType() {
        String code = """
            int a;
            string b;
            int c;
            a = 1;
            b = "ahoj";
            c = (true ? a : b);
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
