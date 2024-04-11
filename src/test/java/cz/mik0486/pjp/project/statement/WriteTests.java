package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class WriteTests extends TestClass {

    @Test
    public void testWriteInt() {
        String code = """
            int a;
            write a;
        """;

        String compiled = """
            push I 0
            save a
            load a
            print 1
        """;

        String input = """        
        """;

        String output = """
            0
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteFloat() {
        String code = """
            float a;
            write a;
        """;

        String compiled = """
            push F 0.0
            save a
            load a
            print 1
        """;

        String input = """        
        """;

        String output = """
            0.0
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteString() {
        String code = """
            string a;
            write a;
        """;

        String compiled = """
            push S ""
            save a
            load a
            print 1
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteBool() {
        String code = """
            bool a;
            write a;
        """;

        String compiled = """
            push B false
            save a
            load a
            print 1
        """;

        String input = """        
        """;

        String output = """
            false
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteMultipleVariables() {
        String code = """
            int a;
            float b;
            string c;
            bool d;
            write a, ",", b, ",", c, ",", d;
        """;

        String compiled = """
            push I 0
            save a
            push F 0.0
            save b
            push S ""
            save c
            push B false
            save d
            load a
            push S ","
            load b
            push S ","
            load c
            push S ","
            load d
            print 7
        """;

        String input = """        
        """;

        String output = """
            0,0.0,,false
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteNoDeclaration() {
        String code = """
            write a;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteNonsense() {
        String code = """
            write "ahoj" > 5;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testWriteWord() {
        String code = """
            write "ahoj";
        """;

        String compiled = """
            push S "ahoj"
            print 1
        """;

        String input = """        
        """;

        String output = """
            ahoj
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
