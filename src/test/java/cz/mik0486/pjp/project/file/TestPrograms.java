package cz.mik0486.pjp.project.file;

import cz.mik0486.pjp.project.TestClass;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

@Slf4j
public class TestPrograms extends TestClass {

    @Test
    public void testProgram1() {
        Path fileInPath = Path.of("Project/PLC_t1.in.txt");
        Path fileOutPath = Path.of("Project/PLC_t1.out.txt");
        processSuccess(fileInPath, fileOutPath, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testProgram2() {
        Path fileInPath = Path.of("Project/PLC_t2.in.txt");
        Path fileOutPath = Path.of("Project/PLC_t2.out.txt");
        processSuccess(fileInPath, fileOutPath, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testProgram3() {
        Path fileInPath = Path.of("Project/PLC_t3.in.txt");
        Path fileOutPath = Path.of("Project/PLC_t3.out.txt");
        processSuccess(fileInPath, fileOutPath, Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Test
    public void testProgramErrors() {
        Path fileInPath = Path.of("Project/PLC_errors.in.txt");
        processFail(fileInPath, Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
