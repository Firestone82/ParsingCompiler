package cz.mik0486.pjp.project;

import cz.mik0486.pjp.project.antlr.Program;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class TestPrograms {

    @Test
    public void testProgram1() {
        Program programTest1 = new Program("Test 1", "Project/PLC_t1.in.txt");
        if (!programTest1.init()) {
            log.error("Error while initializing program");
            throw new AssertionError("Error while initializing program");
        }

        assertNotNull(programTest1.getExpressions());
    }

    @Test
    public void testProgram2() {
        Program program = new Program("Test 2", "Project/PLC_t2.in.txt");
        if (!program.init()) {
            log.error("Error while initializing program");
            throw new AssertionError("Error while initializing program");
        }

        assertNotNull(program.getExpressions());
    }

    @Test
    public void testProgram3() {
        Program program = new Program("Test 3", "Project/PLC_t3.in.txt");
        if (!program.init()) {
            log.error("Error while initializing program");
            throw new AssertionError("Error while initializing program");
        }

        assertNotNull(program.getExpressions());
    }
}
