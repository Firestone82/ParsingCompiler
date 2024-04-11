package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class IfTests extends TestClass {

    @Test
    public void testIf() {
        String code = """
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

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfWrite() {
        String code = """
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


        String input = """        
        """;

        String output = """
            condition was true
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfElse() {
        String code = """
            if (true) {
                write "true";
            } else {
                write "false";
            }
        """;

        String compiled = """
            push B true
            fjmp L0
            push S "true"
            print 1
            jmp L1
            label L0
            push S "false"
            print 1
            label L1
        """;

        String input = """        
        """;

        String output = """
            true
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfElseIf() {
        String code = """
            bool a;
            a = true;
            
            if (a) {
                write "true";
            } else if (false) {
                write "false";
            }
        """;

        String compiled = """
            push B false
            save a
            push B true
            save a
            load a
            pop
            load a
            fjmp L2
            push S "true"
            print 1
            jmp L3
            label L2
            push B false
            fjmp L1
            push S "false"
            print 1
            label L1
            label L3
        """;

        String input = """        
        """;

        String output = """
            true
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfElseIfElse() {
        String code = """
            if (false) {
                write "a";
            } else if (true) {
                write "b";
            } else {
                write "c";
            }
        """;

        String compiled = """
            push B false
            fjmp L2
            push S "a"
            print 1
            jmp L3
            label L2
            push B true
            fjmp L0
            push S "b"
            print 1
            jmp L1
            label L0
            push S "c"
            print 1
            label L1
            label L3
        """;

        String input = """        
        """;

        String output = """
            b
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfCondition() {
        String code = """
            int a;
            if (a == 5) {
                write "a is 5";
            }
        """;

        String compiled = """
            push I 0
            save a
            load a
            push I 5
            eq
            fjmp L1
            push S "a is 5"
            print 1
            label L1
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfElseConditionNoDeclaration() {
        String code = """
            if (a == 5) {
                int b;
            } else {
                float c;
            }
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfWrongCondition() {
        String code = """
            if (a = 5) {
                int b;
            }
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfNotBooleanCondition() {
        String code = """
            if ("ahoj") {
                int b;
            }
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testComplexIf() {
        String code = """
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
            load x
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

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
