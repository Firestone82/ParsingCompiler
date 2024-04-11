package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ForTests extends TestClass {

    @Test
    public void testFor() {
        String code = """
            int i;
            for (i = 0; i < 10; i = i + 1) {
                int a;
            }
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
    public void testForDecl() {
        String code = """
            for (int i = 0; i < 10; i = i + 1) {
                int a;
            }
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), code, null);
        assertFalse(program.init());
    }
}
