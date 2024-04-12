package cz.mik0486.pjp.project;

import cz.mik0486.pjp.project.antlr.Program;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        String programName = "Test";
        Path inputFile = Path.of("Project/PLC_test.in.txt");
        Scanner inputData = new Scanner(System.in);

        Program programTest1 = new Program(programName, inputFile, inputData);
        if (!programTest1.init()) {
            log.error("Error while initializing program");
            return;
        }

        if (!programTest1.check()) {
            log.error("Error while checking program");
            return;
        }

        if (!programTest1.compile()) {
            log.error("Error while compiling program");
            return;
        }

        if (!programTest1.process()) {
            log.error("Error while processing program");
            return;
        }

        log.info("Program successfully executed");
    }
}