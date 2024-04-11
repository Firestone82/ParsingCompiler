package cz.mik0486.pjp.project.antlr;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class LanguageProcessor {
    private final Stack<Object> stack = new Stack<>();
    private final Map<String, Object> variables = new HashMap<>();
    private final Map<String, Integer> labels = new HashMap<>();
    private final Scanner scanner;

    @Getter
    private List<String> processedLines = null;
    private int currentIndex = 0;

    public void process(List<String> instructions) {
        processedLines = new ArrayList<>();

        // Map labels
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);

            if (instruction.startsWith("label")) {
                String[] parts = instruction.split(" ");
                labels.put(parts[1], i);
            }
        }

        while (currentIndex < instructions.size()) {
            String instruction = instructions.get(currentIndex);

            execInstruction(currentIndex, instruction);

            currentIndex++;
        }
    }

    private void execInstruction(int lineNum, String instruction) {
        String[] parts = instruction.split(" ");

        switch (parts[0].toUpperCase()) {
            case "ADD" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                if (left instanceof Float || right instanceof Float ) {
                    stack.push(NumberUtils.parseToFloat(left) + NumberUtils.parseToFloat(right));
                } else {
                    stack.push((int) left + (int) right);
                }
            }

            case "SUB" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                if (left instanceof Float || right instanceof Float ) {
                    stack.push(NumberUtils.parseToFloat(left) - NumberUtils.parseToFloat(right));
                } else {
                    stack.push((int) left - (int) right);
                }
            }

            case "MUL" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                if (left instanceof Float || right instanceof Float ) {
                    stack.push(NumberUtils.parseToFloat(left) * NumberUtils.parseToFloat(right));
                } else {
                    stack.push((int) left * (int) right);
                }
            }

            case "DIV" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                if (left instanceof Float || right instanceof Float ) {
                    stack.push(NumberUtils.parseToFloat(left) / NumberUtils.parseToFloat(right));
                } else {
                    stack.push((int) left / (int) right);
                }
            }

            case "MOD" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                stack.push((int) left % (int) right);
            }

            case "UMINUS" -> {
                Object value = stack.pop();

                if (value instanceof Float) {
                    stack.push(-NumberUtils.parseToFloat(value));
                } else {
                    stack.push(-(int) value);
                }
            }

            case "CONCAT" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                stack.push(left.toString() + right.toString());
            }

            case "AND" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                stack.push((boolean) left && (boolean) right);
            }

            case "OR" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                stack.push((boolean) left || (boolean) right);
            }

            case "GT" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                if (left instanceof Float || right instanceof Float) {
                    stack.push(NumberUtils.parseToFloat(left) > NumberUtils.parseToFloat(right));
                } else {
                    stack.push((int) left > (int) right);
                }
            }

            case "LT" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                if (left instanceof Float || right instanceof Float) {
                    stack.push(NumberUtils.parseToFloat(left) < NumberUtils.parseToFloat(right));
                } else {
                    stack.push((int) left < (int) right);
                }
            }

            case "EQ" -> {
                Object right = stack.pop();
                Object left = stack.pop();

                if (left instanceof Float || right instanceof Float) {
                    stack.push(NumberUtils.parseToFloat(left) == NumberUtils.parseToFloat(right));
                } else if (left instanceof String || right instanceof String) {
                    stack.push(left.toString().equals(right.toString()));
                } else if (left instanceof Boolean || right instanceof Boolean) {
                    stack.push((boolean) left == (boolean) right);
                } else {
                    stack.push((int) left == (int) right);
                }
            }

            case "NOT" -> {
                Object value = stack.pop();

                stack.push(!(boolean) value);
            }

            case "ITOF" -> {
                Object value = stack.pop();

                stack.push(NumberUtils.parseToFloat(value));
            }

            case "PUSH" -> {
                switch (parts[1].toUpperCase()) {
                    case "I" -> stack.push(Integer.parseInt(parts[2]));
                    case "F" -> stack.push(Float.parseFloat(parts[2]));
                    case "B" -> stack.push(Boolean.parseBoolean(parts[2]));
                    case "S" -> {
                        String line = instruction.substring(7);
                        stack.push(line.substring(1, line.length() - 1));
                    }
                }
            }

            case "POP" -> stack.pop();

            case "LOAD" -> stack.push(variables.get(parts[1]));

            case "SAVE" -> variables.put(parts[1], stack.pop());

            case "LABEL" -> {
                // Already being handled by pre-mapping
                // labels.put(parts[1], lineNum);
            }

            case "JMP" -> {
                currentIndex = labels.get(parts[1]);
            }

            case "FJMP" -> {
                if (!(boolean) stack.pop()) {
                    currentIndex = labels.get(parts[1]);
                }
            }

            case "PRINT" -> {
                int count = Integer.parseInt(parts[1]);

                List<String> lines = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    String line = stack.pop().toString();
                    if (line.isBlank()) continue;

                    lines.add(line);
                }

                Collections.reverse(lines);

                StringBuilder builder = new StringBuilder();
                lines.forEach(builder::append);

                lines.clear();
                lines = List.of(builder.toString().split("\n"));

                processedLines.addAll(lines);
            }

            case "READ" -> {
                String type = parts[1].toUpperCase();
                String line = scanner.nextLine();

                switch (type) {
                    case "I" -> stack.push(Integer.parseInt(line));
                    case "F" -> stack.push(Float.parseFloat(line));
                    case "B" -> stack.push(Boolean.parseBoolean(line));
                    case "S" -> stack.push(line);
                }
            }
        }
    }
}
