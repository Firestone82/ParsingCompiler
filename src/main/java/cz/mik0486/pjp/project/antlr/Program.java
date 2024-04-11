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
import java.util.Scanner;

@Data
@Slf4j
public class Program {

    private final String programName;
    private LanguageParser.ProgramContext context;

    private final String inputFilePath;
    private String inputText;
    private Scanner inputData;

    private List<String> compiledCodeLines;
    private List<String> outputLines;

    public Program(String programName, String inputText, Scanner inputData) {
        this.programName = programName;
        this.inputText = inputText;
        this.inputFilePath = null;
        this.inputData = inputData;
    }

    public Program(String programName, Path fileInputPath, Scanner inputData) {
        this.programName = programName;
        this.inputText = null;
        this.inputFilePath = fileInputPath.toString();
        this.inputData = inputData;
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

        LanguageLexer lexer = new LanguageLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LanguageParser parser = new LanguageParser(tokens);
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

        LanguageCompiler languageCompiler = new LanguageCompiler();
        new ParseTreeWalker().walk(languageCompiler, context);
        compiledCodeLines = languageCompiler.getCompiledCodeLines();

        int totalLines = String.valueOf(compiledCodeLines.size()).length();
        int lineNum = 1;

        for (String line : languageCompiler.getCompiledCodeLines()) {
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

    public boolean process() {
        long startTime = System.currentTimeMillis();
        log.debug(" - Processing program");

        LanguageProcessor languageProcessor = new LanguageProcessor(inputData);
        languageProcessor.process(compiledCodeLines);
        outputLines = languageProcessor.getProcessedLines();

        int totalLines = String.valueOf(outputLines.size()).length();
        int lineNum = 1;

        for (String line : languageProcessor.getProcessedLines()) {
            String lineNumStr = " ".repeat(totalLines - String.valueOf(lineNum).length()) + lineNum;
            lineNum++;

            log.trace((STR . " | \{ lineNumStr } | \{ line }"));
        }

        if (outputLines.isEmpty()) {
            log.trace(" - No output generated!");
        }

        if (ErrorLogger.hasErrors()) {
            log.error(" - Evaluation errors found!");
            ErrorLogger.printErrors();
            return false;
        }

        log.info("Program processed in %d ms".formatted(System.currentTimeMillis() - startTime));
        return true;
    }

    public String getCompiledCode() {
        if (compiledCodeLines == null) {
            return null;
        }

        return String.join("\n", compiledCodeLines);
    }

    public String getOutput() {
        if (outputLines == null) {
            return null;
        }

        return String.join("\n", outputLines);
    }
}
