package cz.mik0486.pjp.project.antlr;

import cz.mik0486.pjp.project.gen.LanguageLexer;
import cz.mik0486.pjp.project.gen.LanguageParser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@RequiredArgsConstructor
public class Program {

    private final String programName;
    private final String inputFilePath;

    private LanguageLexer lexer;
    private CommonTokenStream tokens;
    private LanguageParser parser;

    private LanguageParser.ProgramContext context;

    public boolean init() {
        long startTime = System.currentTimeMillis();

        log.info("Initializing program %s".formatted(programName));
        log.debug(" - Loading input file src/main/antlr4/%s".formatted(inputFilePath));

        File inputFile = new File("src/main/antlr4", inputFilePath);
        if (!inputFile.exists()) {
            log.error("    - Input file not found");
            return false;
        }

        CharStream input = null;
        try {
            input = CharStreams.fromFileName(inputFile.getAbsolutePath());

            if (input.size() == 0) {
                log.error("    - Input file is empty");
                System.exit(1);
            }
        } catch (Exception e) {
            log.error("    - Error while loading input file: %s".formatted(e.getMessage()));
            return false;
        }

        lexer = new LanguageLexer(input);
        tokens = new CommonTokenStream(lexer);
        parser = new LanguageParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ErrorListener());

        context = parser.program();

        if (parser.getNumberOfSyntaxErrors() > 0) {
            log.error(" - Syntax errors found!");
            return false;
        }

        long endTime = System.currentTimeMillis();
        log.info("Program %s initialized in %d ms".formatted(programName, (endTime - startTime)));
        return true;
    }

    public List<LanguageParser.StatementContext> getExpressions() {
        if (!check()) {
            return new ArrayList<>();
        }

        return context.statement();
    }

    private boolean check() {
        if (lexer == null || tokens == null || parser == null) {
            log.error("Program %s not initialized".formatted(programName));
            return false;
        }

        if (context == null) {
            log.error("Program %s not parsed".formatted(programName));
            return false;
        }

        return true;
    }
}
