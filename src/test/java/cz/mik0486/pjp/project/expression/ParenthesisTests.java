package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ParenthesisTests extends TestClass {

    @Test
    public void testParenthesis() {
        String code = """
            write (true);
        """;

        String compiled = """
            push B true
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
    public void testParenthesisInt() {
        String code = """
            int a;
            write (a);
        """;

        String compiled = """
            push I 0
            save a
            load a
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
    public void testParenthesisFloat() {
        String code = """
            float a;
            write (a);
        """;

        String compiled = """
            push F 0.0
            save a
            load a
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
    public void testParenthesisString() {
        String code = """
            string a;
            write (a);
        """;

        String compiled = """
            push S ""
            save a
            load a
            print 1
        """;

        String input = """        
        """;

        String output = """
            
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testParenthesisBool() {
        String code = """
            bool a;
            write (a);
        """;

        String compiled = """
            push B false
            save a
            load a
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
    public void testParenthesisOperation() {
        String code = """
            write (true && false);
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
}
