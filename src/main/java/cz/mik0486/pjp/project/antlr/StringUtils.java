package cz.mik0486.pjp.project.antlr;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
public class StringUtils {

    public static String stripSpaces(String input) {
        return Arrays.stream(input.split("\n")).map(String::strip).collect(Collectors.joining("\n"));
    }

    public static String readFile(File file) {
        StringBuilder stringBuffer = new StringBuilder();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                stringBuffer.append(scanner.nextLine()).append("\n");
            }

            scanner.close();
        } catch (java.io.FileNotFoundException e) {
            log.error("File not found: %s".formatted(file));
        }

        return stringBuffer.toString();
    }
}
