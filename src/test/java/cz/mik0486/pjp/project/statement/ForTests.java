package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.Program;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForTests extends TestClass {

    @Test
    public void testFor() {
        String input = """
            int i;
            for (i = 0; i < 10; i = i + 1) {
                int a;
            }
        """;

        String compiled = """
            TODO
        """;

        processSuccess(input, null, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testForDecl() {
        String input = """
            for (int i = 0; i < 10; i = i + 1) {
                int a;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), input);
        assertFalse(program.init());
    }
}
