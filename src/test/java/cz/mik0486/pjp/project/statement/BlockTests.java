package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class BlockTests extends TestClass {

    @Test
    public void testEmptyBlock() {
        String code = """
            {
            }
        """;

        String compiled = """
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBlockWithOneStatement() {
        String code = """
            {
                int a;
            }
        """;

        String compiled = """
            push I 0
            save a
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBlockWithMultipleStatements() {
        String code = """
            {
                int a;
                float b;
                string c;
                bool d;
            }
        """;

        String compiled = """
            push I 0
            save a
            push F 0.0
            save b
            push S ""
            save c
            push B false
            save d
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
