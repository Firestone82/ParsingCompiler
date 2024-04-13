package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
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
            int a;
            int x;
            read x;
            
            while (x < 30) {
                if (x > 0 && x < 10) {
                    if (a % 2 == 0) {
                        a = a + 1;
                    } else {
                        a = a + 2;
                    }
                    
                    x = x + 1;
                } else if (x > 10 && x < 20) {
                    if (a % 2 == 0) {
                        a = a * 2;
                    } else {
                        a = a * 3;
                    }
                    
                    x = x + 2;
                } else {
                    a = a + 3;
                    x = x + 3;
                }
            }
            
            write a;
        """;

        String compiled = """
            push I 0
            save a
            push I 0
            save x
            read I
            save x
            label L8
            load x
            push I 30
            lt
            fjmp L9
            load x
            push I 0
            gt
            load x
            push I 10
            lt
            and
            fjmp L6
            load a
            push I 2
            mod
            push I 0
            eq
            fjmp L0
            load a
            push I 1
            add
            save a
            load a
            pop
            jmp L1
            label L0
            load a
            push I 2
            add
            save a
            load a
            pop
            label L1
            load x
            push I 1
            add
            save x
            load x
            pop
            jmp L7
            label L6
            load x
            push I 10
            gt
            load x
            push I 20
            lt
            and
            fjmp L4
            load a
            push I 2
            mod
            push I 0
            eq
            fjmp L2
            load a
            push I 2
            mul
            save a
            load a
            pop
            jmp L3
            label L2
            load a
            push I 3
            mul
            save a
            load a
            pop
            label L3
            load x
            push I 2
            add
            save x
            load x
            pop
            jmp L5
            label L4
            load a
            push I 3
            add
            save a
            load a
            pop
            load x
            push I 3
            add
            save x
            load x
            pop
            label L5
            label L7
            jmp L8
            label L9
            load a
            print 1
        """;

        String input = """     
            5
        """;

        String output = """
            201
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
