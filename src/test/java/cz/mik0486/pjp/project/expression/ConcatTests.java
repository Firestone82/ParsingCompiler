package cz.mik0486.pjp.project.expression;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ConcatTests extends TestClass {

    @Test
    public void testConcatenation() {
        String code = """
            write "string" . "string";
        """;

        String compiled = """
            push S "string"
            push S "string"
            concat
            print 1
        """;

        String input = """        
        """;

        String output = """
            stringstring
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testConcatenationInt() {
        String code = """
            1 . 2;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testConcatenationFloat() {
        String code = """
            1.0 . 2.0;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testConcatenationBool() {
        String code = """
            true . false;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
