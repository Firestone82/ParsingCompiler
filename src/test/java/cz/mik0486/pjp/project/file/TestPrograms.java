package cz.mik0486.pjp.project.file;

import cz.mik0486.pjp.project.antlr.Program;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TestPrograms {

    @AfterEach
    public void reset() {
        ErrorLogger.clearErrors();
    }

    @Test
    public void testProgram1() {
        Program program = new Program("Test 1", Path.of("Project/PLC_t1.in.txt"));
        assertTrue(program.init());
    }

    @Test
    public void testProgram2() {
        Program program = new Program("Test 2", Path.of("Project/PLC_t2.in.txt"));
        assertTrue(program.init());
    }

    @Test
    public void testProgram3() {
        Program program = new Program("Test 3", Path.of("Project/PLC_t3.in.txt"));
        assertTrue(program.init());
    }

    @Test
    public void testProgramErrors() {
        Program program = new Program("Test 3", Path.of("Project/PLC_errors.in.txt"));
        assertFalse(program.init());
    }
}
