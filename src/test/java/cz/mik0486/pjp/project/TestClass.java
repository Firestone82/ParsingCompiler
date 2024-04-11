package cz.mik0486.pjp.project;

import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.StringUtils;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class TestClass {

    @AfterEach
    public void reset() {
        ErrorLogger.clearErrors();
        System.out.println();
    }

    public void processSuccess(String programCode, String expectedCompiledCode, Scanner inputData, String expectedResult, String programName) {
        Program program = new Program(programName, programCode, inputData);
        assertTrue(program.init());
        assertTrue(program.check());

        if (expectedCompiledCode != null) {
            assertTrue(program.compile());
            assertEquals(StringUtils.stripSpaces(program.getCompiledCode()), StringUtils.stripSpaces(expectedCompiledCode));
        }

        if (expectedResult != null) {
            assertTrue(program.process());
            assertEquals(StringUtils.stripSpaces(program.getOutput()), StringUtils.stripSpaces(expectedResult));
        }
    }

    public void processSuccess(Path programCodePath, Path expectedCompiledCodePath, Scanner inputData, String expectedResult, String programName) {
        Program program = new Program(programName, programCodePath, inputData);
        assertTrue(program.init());
        assertTrue(program.check());

        if (expectedCompiledCodePath != null) {
            File compiledFile = new File(STR . "src/main/antlr4/\{ expectedCompiledCodePath.toString() }");
            String output = StringUtils.stripSpaces(StringUtils.readFile(compiledFile));

            assertTrue(program.compile());
            assertEquals(StringUtils.stripSpaces(program.getCompiledCode()), StringUtils.stripSpaces(output));
        }

        if (expectedResult != null) {
            assertTrue(program.process());
            assertEquals(StringUtils.stripSpaces(program.getOutput()), StringUtils.stripSpaces(expectedResult));
        }
    }

    public void processFail(String input, String programName) {
        Program program = new Program(programName, input, null);
        assertTrue(program.init());
        assertFalse(program.check());
    }

    public void processFail(Path fileInPath, String programName) {
        Program program = new Program(programName, fileInPath, null);
        assertTrue(program.init());
        assertFalse(program.check());
    }
}
