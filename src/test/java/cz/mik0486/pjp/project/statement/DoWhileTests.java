package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class DoWhileTests extends TestClass {

    @Test
    public void testDoWhile() {
        String code = """
            int i;
            do {
                int a;
                a = 1;
                
                i = i + a;
            } while (i < 10);
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
}
