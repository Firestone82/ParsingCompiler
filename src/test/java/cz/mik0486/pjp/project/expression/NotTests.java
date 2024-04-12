package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class NotTests extends TestClass {

    @Test
    public void testLogicNot() {
        String code = """
            write !false;
        """;

        String compiled = """
            push B false
            not
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
    public void testLogicNotInt() {
        String code = """
            int a;
            !a;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicNotFloat() {
        String code = """
            float a;
            !a;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicNotString() {
        String code = """
            string a;
            !a;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testLogicNotBool() {
        String code = """
            bool a;
            write !a;
        """;

        String compiled = """
            push B false
            save a
            load a
            not
            print 1
        """;

        String input = """        
        """;

        String output = """
            true
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
