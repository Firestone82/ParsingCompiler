package cz.mik0486.pjp.project;

import cz.mik0486.pjp.project.antlr.LanguageCompiler;
import cz.mik0486.pjp.project.antlr.Program;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.nio.file.Path;
import java.util.Scanner;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Program programTest1 = new Program("Test 1", Path.of("Project/PLC_test.in.txt"), new Scanner(System.in));
        if (!programTest1.init()) {
            log.error("Error while initializing program");
            return;
        }

        ParseTreeWalker walker = new ParseTreeWalker();
        LanguageCompiler languageCompiler = new LanguageCompiler();

        walker.walk(languageCompiler, programTest1.getContext());
        languageCompiler.getCompiledCodeLines().forEach(log::info);

//        for (ParserRuleContext expr : programTest1.getExpressions()) {
//            log.debug("Expression: " + expr.toStringTree(programTest1.getParser()));
//        }
    }
}