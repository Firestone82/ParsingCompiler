package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class VariableDeclarationTests extends TestClass {

    @Test
    public void testVariableDeclaration() {
        String code = """
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

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testDoubleDeclaration() {
        String code = """
            int a, b;
            int a;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testDoubleDeclarationDifferentTypes() {
        String code = """
            int a, b, c;
            bool a;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
