package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

public class IfTests extends TestClass {

    @Test
    public void testIf() {
        String input = """
            if (true) {
                int a;
            }
        """;

        String compiled = """
            push B true
            fjmp L1
            push I 0
            save a
            label L1
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfWrite() {
        String input = """
            if (3<4) write "condition was true";
            else write "condition was false";
        """;

        String compiled = """
            push I 3
            push I 4
            lt
            fjmp L0
            push S "condition was true"
            print 1
            jmp L1
            label L0
            push S "condition was false"
            print 1
            label L1
        """;

        processSuccess(StringUtils.stripSpaces(input), StringUtils.stripSpaces(compiled), Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfElse() {
        String input = """
            if (true) {
                int a;
            } else {
                float b;
            }
        """;

        String compiled = """
            push B true
            fjmp L0
            push I 0
            save a
            jmp L1
            label L0
            push F 0.0
            save b
            label L1
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfElseIf() {
        String input = """
            if (true) {
                int a;
            } else if (false) {
                float b;
            }
        """;

        String compiled = """
            push B true
            fjmp L2
            push I 0
            save a
            jmp L3
            label L2
            push B false
            fjmp L1
            push F 0.0
            save b
            label L1
            label L3
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfElseIfElse() {
        String input = """
            if (true) {
                int a;
            } else if (false) {
                float b;
            } else {
                string c;
            }
        """;

        String compiled = """
            push B true
            fjmp L2
            push I 0
            save a
            jmp L3
            label L2
            push B false
            fjmp L0
            push F 0.0
            save b
            jmp L1
            label L0
            push S ""
            save c
            label L1
            label L3
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfCondition() {
        String input = """
            int a;
            if (a == 5) {
                int b;
            }
        """;

        String compiled = """
            push I 0
            save a
            load a
            push I 5
            eq
            fjmp L1
            push I 0
            save b
            label L1
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfElseConditionNoDeclaration() {
        String input = """
            if (a == 5) {
                int b;
            } else {
                float c;
            }
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfWrongCondition() {
        String input = """
            if (a = 5) {
                int b;
            }
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfNotBooleanCondition() {
        String input = """
            if ("ahoj") {
                int b;
            }
        """;

        processFail(input, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testComplexIf() {
        String input = """
            int x;
            x = 5;
            if (x > 0 && x < 10) {
                int a;
            } else if (x > 10 && x < 20) {
                float b;
            } else {
                string c;
            }
        """;

        String compiled = """
            push I 0
            save x
            push I 5
            save x
            pop
            load x
            push I 0
            gt
            load x
            push I 10
            lt
            and
            fjmp L2
            push I 0
            save a
            jmp L3
            label L2
            load x
            push I 10
            gt
            load x
            push I 20
            lt
            and
            fjmp L0
            push F 0.0
            save b
            jmp L1
            label L0
            push S ""
            save c
            label L1
            label L3
        """;

        processSuccess(input, compiled, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
