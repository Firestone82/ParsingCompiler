package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class DoWhileTests extends TestClass {

    @Test
    public void testDoWhile() {
        String input = """
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

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
