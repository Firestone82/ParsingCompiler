package cz.mik0486.pjp.project;

import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import org.junit.jupiter.api.AfterEach;

public class TestClass {

    @AfterEach
    public void reset() {
        ErrorLogger.clearErrors();
        System.out.println();
    }

}
