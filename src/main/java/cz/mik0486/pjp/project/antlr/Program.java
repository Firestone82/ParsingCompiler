package cz.mik0486.pjp.project.antlr;

import cz.mik0486.pjp.project.antlr.error.ErrorListener;
import cz.mik0486.pjp.project.antlr.error.ErrorLogger;
import cz.mik0486.pjp.project.antlr.gen.LanguageLexer;
import cz.mik0486.pjp.project.antlr.gen.LanguageParser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Program {

    private final String programName;
    private final String inputFilePath;
    private String inputText;

    private LanguageLexer lexer;
    private CommonTokenStream tokens;
    private LanguageParser parser;

    private LanguageParser.ProgramContext context;

    public Program(String programName, String inputText) {
        this.programName = programName;
        this.inputText = inputText;
        this.inputFilePath = null;
    }

    public Program(String programName, Path fileInputPath) {
        this.programName = programName;
        this.inputText = null;
        this.inputFilePath = fileInputPath.toString();
    }

    public boolean init() {
        long startTime = System.currentTimeMillis();

        log.info(STR . "Initializing program \"\{ programName }\"");
        CharStream input = null;

        if (inputFilePath != null) {
            log.debug(STR . " - Loading input file src/main/antlr4/\{ inputFilePath }");

            File inputFile = new File("src/main/antlr4", inputFilePath);
            if (!inputFile.exists()) {
                log.error("    - Input file not found");
                return false;
            }

            try {
                input = CharStreams.fromFileName(inputFile.getAbsolutePath());

                if (input.size() == 0) {
                    log.error("    - Input file is empty");
                    System.exit(1);
                }
            } catch (Exception e) {
                log.error(STR . "    - Error while loading input file: \{ e.getMessage() }");
                return false;
            }
        } else {
            log.debug(" - Using text input");
            StringBuilder sb = new StringBuilder();

            int i = 1;
            for (String line : inputText.split("\n")) {
                sb.append(line.trim()).append("\n");
                log.debug(STR . " | \{ i } | \{ line.trim() }");
                i++;
            }

            input = CharStreams.fromString(sb.toString());
        }

        lexer = new LanguageLexer(input);
        tokens = new CommonTokenStream(lexer);
        parser = new LanguageParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new ErrorListener());

        context = parser.program();

        // Check for syntax errors
        if (parser.getNumberOfSyntaxErrors() > 0) {
            log.error(" - Syntax errors found!");
            return false;
        }

        // Run type checking
        LanguageTypeVisitor visitor = new LanguageTypeVisitor();
        visitor.visit(context);

        if (ErrorLogger.hasErrors()) {
            log.error(" - Type errors found!");
            ErrorLogger.printErrors();

            log.info("Program initialization failed. Took %d ms".formatted(System.currentTimeMillis() - startTime));
            return false;
        }

        log.info("Program initialized successfully. Took %d ms".formatted(System.currentTimeMillis() - startTime));
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
