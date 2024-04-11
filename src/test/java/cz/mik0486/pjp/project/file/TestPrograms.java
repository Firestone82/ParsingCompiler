package cz.mik0486.pjp.project.file;

import cz.mik0486.pjp.project.TestClass;
import cz.mik0486.pjp.project.antlr.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Scanner;

@Slf4j
public class TestPrograms extends TestClass {

    @Test
    public void testProgram1() {
        Path fileInPath = Path.of("Project/PLC_t1.in.txt");
        Path fileOutPath = Path.of("Project/PLC_t1.out.txt");

        String input = """     
            1
            2.0
            Three
            false
        """;

        String output = """
            <Constants>
            10: 10
            1.25: 1.25
            
            <Variables>
            s(Abcd): Abcd
            d(3.141592): 3.141592
            n(-500): -500
            
            boolean(true): true
            
            <Expressions>
            2+3*5(17): 17
            17 / 3(5): 5
            17 % 3(2): 2
            2.5*2.5/6.25(1.0): 1.0
            1.5*3(4.5): 4.5
            abc+def (abcdef): abcdef
            
            <Comments>
            <Automatic int conversion>
            y (10.0): 10.0
            <Multiple Assignments>
            i=j=k=55: 55=55=55
            <Input - a(int),b(float),c(string),d(bool)>
            a,b,c,e: 1,2.0,Three,false
        """;

        processSuccess(fileInPath, fileOutPath, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testProgram2() {
        Path fileInPath = Path.of("Project/PLC_t2.in.txt");
        Path fileOutPath = Path.of("Project/PLC_t2.out.txt");

        String input = """        
        """;

        String output = """
            <Relational operators>
            1<5: true
            1>3.5: false
            aa==aa: true
            aa==ab: false
            aa!=ab: true
            
            <Logic operators>
            false and true (false):false
            false or true (true):true
            not 1==2 (true):true
            true or false and true (true):true
        """;

        processSuccess(fileInPath, fileOutPath, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testProgram3() {
        Path fileInPath = Path.of("Project/PLC_t3.in.txt");
        Path fileOutPath = Path.of("Project/PLC_t3.out.txt");

        String input = """    
            10
        """;

        String output = """
            condition was true
            inside
            second
            if
            a=0
            a=1
            a=2
            a=3
            a=4
            a=5
            a=6
            a=7
            a=8
            a=9
            a=0, b=10
            a=1, b=10
            a=2, b=10
            a=3, b=10
            a=4, b=10
            a=5, b=10
            a=6, b=10
            a=7, b=10
            a=8, b=10
            a=9, b=10
        """;

        processSuccess(fileInPath, fileOutPath, new Scanner(StringUtils.stripSpaces(input)), output, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testProgramErrors() {
        Path fileInPath = Path.of("Project/PLC_errors.in.txt");
        processFail(fileInPath, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
