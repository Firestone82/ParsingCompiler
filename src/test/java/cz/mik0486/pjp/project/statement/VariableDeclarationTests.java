package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class VariableDeclarationTests extends TestClass {

    @Test
    public void testVariableDeclaration() {
        String input = """
            int a, b, c;
            bool d;
            float e;
            string chachar;
        """;

        String compiled = """
            push I 0
            save a
            push I 0
            save b
            push I 0
            save c
            push B false
            save d
            push F 0.0
            save e
            push S ""
            save chachar
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testDoubleDeclaration() {
        String input = """
            int a, b;
            int a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testDoubleDeclarationDifferentTypes() {
        String input = """
            int a, b, c;
            bool a;
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
