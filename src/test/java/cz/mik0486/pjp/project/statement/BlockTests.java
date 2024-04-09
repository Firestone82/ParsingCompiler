package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class BlockTests extends TestClass {

    @Test
    public void testEmptyBlock() {
        String input = """
            {
            }
        """;

        String compiled = """
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBlockWithOneStatement() {
        String input = """
            {
                int a;
            }
        """;

        String compiled = """
            push I 0
            save a
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testBlockWithMultipleStatements() {
        String input = """
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

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
