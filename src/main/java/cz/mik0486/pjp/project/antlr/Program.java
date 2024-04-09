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
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.nio.file.Path;
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
    private List<String> compiledCodeLines;

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

        log.info("Initializing program \"%s\"".formatted(programName));
        CharStream input = null;

        if (inputFilePath != null) {
            log.debug(" - Loading input file src/main/antlr4/%s".formatted(inputFilePath));

            File inputFile = new File("src/main/antlr4", inputFilePath);
            if (!inputFile.exists()) {
                log.error("    - Input file not found");
                return false;
            }

            try {
                input = CharStreams.fromFileName(inputFile.getAbsolutePath());
                inputText = input.toString();

                if (input.size() == 0) {
                    log.error("    - Input file is empty");
                    System.exit(1);
                }
            } catch (Exception e) {
                log.error("    - Error while loading input file: %s".formatted(e.getMessage()));
                return false;
            }

        } else {
            log.debug(" - Using text input");

            StringBuilder sb = new StringBuilder();
            int lineNum = 1;

            for (String line : inputText.split("\n")) {
                line = line.strip();
                sb.append(line).append("\n");

                log.trace(" | %d | %s".formatted(lineNum++, line));
            }

            inputText = sb.toString();
            input = CharStreams.fromString(inputText);
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

        long endTime = System.currentTimeMillis();
        log.info("Program initialized in %d ms".formatted(endTime - startTime));
        return true;
    }

    public boolean check() {
        long startTime = System.currentTimeMillis();
        log.debug(" - Running type check");

        LanguageTypeVisitor visitor = new LanguageTypeVisitor();
        visitor.visit(context);

        if (ErrorLogger.hasErrors()) {
            log.error(" - Type errors found!");
            ErrorLogger.printErrors();
            return false;
        }

        log.trace(" - Total rows: {}", inputText.split("\n").length);
        log.trace(" - Code contains no type errors");

        log.info("Type check passed in %d ms".formatted(System.currentTimeMillis() - startTime));
        return true;
    }

    public boolean compile() {
        long startTime = System.currentTimeMillis();
        log.debug(" - Compiling program");

        LanguageProcessor languageProcessor = new LanguageProcessor();
        new ParseTreeWalker().walk(languageProcessor, context);
        compiledCodeLines = languageProcessor.getCompiledCodeLines();

        int totalLines = String.valueOf(compiledCodeLines.size()).length();
        int lineNum = 1;

        for (String line : languageProcessor.getCompiledCodeLines()) {
            String lineNumStr = " ".repeat(totalLines - String.valueOf(lineNum).length()) + lineNum;
            lineNum++;

            log.trace((STR . " | \{ lineNumStr } | \{ line }"));
        }

        if (ErrorLogger.hasErrors()) {
            log.error(" - Compilation errors found!");
            ErrorLogger.printErrors();
            return false;
        }

        log.info("Compilation finished in %d ms".formatted(System.currentTimeMillis() - startTime));
        return true;
    }

    public String getCompiledCode() {
        if (compiledCodeLines == null) {
            return null;
        }

        return String.join("\n", compiledCodeLines);
    }
}
