package cz.mik0486.pjp.project;

import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.StringUtils;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import org.junit.jupiter.api.AfterEach;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class TestClass {

    @AfterEach
    public void reset() {
        ErrorLogger.clearErrors();
        System.out.println();
    }

    public void processSuccess(String input, String output, String programName) {
        Program program = new Program(programName, input);
        assertTrue(program.init());
        assertTrue(program.check());

        if (output != null) {
            assertTrue(program.compile());
            assertEquals(program.getCompiledCode(), StringUtils.stripSpaces(output));
        }
    }

    public void processSuccess(Path fileInPath, Path fileOutPath, String programName) {
        Program program = new Program(programName, fileInPath);
        assertTrue(program.init());
        assertTrue(program.check());
        assertTrue(program.compile());

        // Read the output file
        File file = new File(STR . "src/main/antlr4/" + fileOutPath.toString());
        String output = StringUtils.stripSpaces(StringUtils.readFile(file));

        assertEquals(program.getCompiledCode(), StringUtils.stripSpaces(output));
    }

    public void processFail(String input, String programName) {
        Program program = new Program(programName, input);
        assertTrue(program.init());
        assertFalse(program.check());
    }

    public void processFail(Path fileInPath, String programName) {
        Program program = new Program(programName, fileInPath);
        assertTrue(program.init());
        assertFalse(program.check());
    }
}
