package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import org.junit.jupiter.api.Test;

public class WhileTests extends TestClass {

    @Test
    public void testWhile() {
        String code = """
            int i;
            while (i < 10) {
                int a;
            }
        """;

        String compiled = """
            push I 0
            save i
            label L0
            load i
            push I 10
            lt
            fjmp L1
            push I 0
            save a
            jmp L0
            label L1
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, null, null, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWhileComplex() {
        String code = """
            int i, b;
            read b;
            
            while (i < b) {
                int a;
                a = 1;
                i = i + a;
            }
            
            write i;
        """;

        String compiled = """
            push I 0
            save i
            push I 0
            save b
            read I
            save b
            label L0
            load i
            load b
            lt
            fjmp L1
            push I 0
            save a
            push I 1
            save a
            load a
            pop
            load i
            load a
            add
            save i
            load i
            pop
            jmp L0
            label L1
            load i
            print 1
        """;

        String input = """    
            10
        """;

        String output = """
            10
        """;

        processSuccess(code, compiled, null, null, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWhileWithIf() {
        String code = """
            int i, b;
            read b;
            
            while (i < b) {
                int a;
                a = 1;
                i = i + a;
                
                if (i == 5) {
                    write "i is 5";
                }
            }
        """;

        String compiled = """
            push I 0
            save i
            push I 0
            save b
            read I
            save b
            label L2
            load i
            load b
            lt
            fjmp L3
            push I 0
            save a
            push I 1
            save a
            load a
            pop
            load i
            load a
            add
            save i
            load i
            pop
            load i
            push I 5
            eq
            fjmp L1
            push S "i is 5"
            print 1
            label L1
            jmp L2
            label L3
        """;

        String input = """    
            10
        """;

        String output = """
            i is 5
        """;

        processSuccess(code, compiled, null, null, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testIfNotDeclared() {
        String code = """
            while (i < 10) {
                int a;
            }
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
