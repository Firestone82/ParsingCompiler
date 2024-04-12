package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ReadTests extends TestClass {

    @Test
    public void testReadInt() {
        String code = """
            int a;
            read a;
            write a;
        """;

        String compiled = """
            push I 0
            save a
            read I
            save a
            load a
            print 1
        """;

        String input = """        
            5
        """;

        String output = """
            5
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadFloat() {
        String code = """
            float a;
            read a;
            write a;
        """;

        String compiled = """
            push F 0.0
            save a
            read F
            save a
            load a
            print 1
        """;

        String input = """       
            5.5
        """;

        String output = """
            5.5
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadString() {
        String code = """
            string a;
            read a;
            write a;
        """;

        String compiled = """
            push S ""
            save a
            read S
            save a
            load a
            print 1
        """;

        String input = """       
            Hello World
        """;

        String output = """
            Hello World
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadBool() {
        String code = """
            bool a;
            read a;
            write a;
        """;

        String compiled = """
            push B false
            save a
            read B
            save a
            load a
            print 1
        """;

        String input = """      
            true
        """;

        String output = """
            true
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadMultipleVariables() {
        String code = """
            int a;
            float b;
            string c;
            bool d;
            read a, b, c, d;
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
            read I
            save a
            read F
            save b
            read S
            save c
            read B
            save d
        """;

        String input = """        
            5
            5.5
            string
            true
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testReadNoDeclaration() {
        String code = """
            read a;
        """;

        processFail(code, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
