package cz.mik0486.pjp.project.statement;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmptyTests extends TestClass {

    @Test
    public void testEmptyStat() {
        String code = """
           
        """;

        Program program = new Program(Thread.currentThread().getStackTrace()[1].getMethodName(), code, null);
        assertFalse(program.init());
    }

    @Test
    public void testOneSemicolon() {
        String code = """
           ;
        """;

        String compiled = """
            
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testTwoSemicolons() {
        String code = """
            ;;
        """;

        String compiled = """
            
        """;

        String input = """        
        """;

        String output = """
        """;

        processSuccess(code, compiled, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
