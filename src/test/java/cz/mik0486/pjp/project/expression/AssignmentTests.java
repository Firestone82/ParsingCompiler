package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class AssignmentTests extends TestClass {

    @Test
    public void testAssignInt() {
        String code = """
            int a;
            a = 5;
            write a;
        """;

        String compiled = """
            push I 0
            save a
            push I 5
            save a
            load a
            pop
            load a
            print 1
        """;

        String input = """        
        """;

        String output = """
            5
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testAssignFloat() {
        String code = """
            float a;
            a = 5.0;
            write a;
        """;

        String compiled = """
            push F 0.0
            save a
            push F 5.0
            save a
            load a
            pop
            load a
            print 1
        """;

        String input = """        
        """;

        String output = """
            5.0
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testAssignString() {
        String code = """
            string a;
            a = "hello";
            write a;
        """;

        String compiled = """
            push S ""
            save a
            push S "hello"
            save a
            load a
            pop
            load a
            print 1
        """;

        String input = """        
        """;

        String output = """
            hello
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testAssignBool() {
        String code = """
            bool a;
            a = true;
            write a;
        """;

        String compiled = """
            push B false
            save a
            push B true
            save a
            load a
            pop
            load a
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
    public void testAssignMixed() {
        String code = """
            int a;
            a = 0.9;
            
            float b;
            b = 5;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testAssignIntToFloat() {
        String code = """
            float a;
            a = 5;
            write a;
        """;

        String compiled = """
            push F 0.0
            save a
            push I 5
            itof
            save a
            load a
            pop
            load a
            print 1
        """;

        String input = """        
        """;

        String output = """
            5.0
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
