package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ExpressionTests extends TestClass {

    @Test
    public void testExpression() {
        String code = """
            1 + 2;
            1 - 2;
            1 * 2;
            1 / 2;
            1 % 2;
            1 == 2;
            1 != 2;
            1 < 2;
            1 > 2;
            true && true;
            true || false;
            !true;
            -1;
            1;
            1.0;
            "string";
            true;
            false;
        """;

        String compiled = """
            push I 1
            push I 2
            add
            pop
            push I 1
            push I 2
            sub
            pop
            push I 1
            push I 2
            mul
            pop
            push I 1
            push I 2
            div
            pop
            push I 1
            push I 2
            mod
            pop
            push I 1
            push I 2
            eq
            pop
            push I 1
            push I 2
            eq
            not
            pop
            push I 1
            push I 2
            lt
            pop
            push I 1
            push I 2
            gt
            pop
            push B true
            push B true
            and
            pop
            push B true
            push B false
            or
            pop
            push B true
            not
            pop
            push I 1
            uminus
            pop
            push I 1
            pop
            push F 1.0
            pop
            push S "string"
            pop
            push B true
            pop
            push B false
            pop
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
