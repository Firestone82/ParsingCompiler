package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class LogicTests extends TestClass {

    @Test
    public void testLogicAnd() {
        String code = """
            write true && false;
        """;

        String compiled = """
            push B true
            push B false
            and
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
    public void testLogicOr() {
        String code = """
            write true || false;
        """;

        String compiled = """
            push B true
            push B false
            or
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
    public void testLogicAndInt() {
        String code = """
            int a;
            int b;
            a && b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicAndFloat() {
        String code = """
            float a;
            float b;
            a && b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicAndString() {
        String code = """
            string a;
            string b;
            a && b;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicAndBool() {
        String code = """
            bool a;
            bool b;
            write a && b;
        """;

        String compiled = """
            push B false
            save a
            push B false
            save b
            load a
            load b
            and
            print 1
        """;

        String input = """        
        """;

        String output = """
            false
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
