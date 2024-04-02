package cz.mik0486.pjp.project.antlr;

public enum Type {
    INT, FLOAT, BOOL, STRING, VOID, ERROR;

    public static Type resultType(Type type, String operator) {
        if (type == ERROR) return ERROR;

        return switch (operator) {
            case "-":
                if (type == INT) yield INT;
                if (type == FLOAT) yield FLOAT;
                yield ERROR;

            case "!":
                if (type == BOOL) yield BOOL;
                yield ERROR;

            default:
                yield ERROR;
        };
    }

    public static Type resultType(Type left, Type right, String operator) {
        if (left == ERROR || right == ERROR) return ERROR;

        return switch (operator) {
            case "+", "-", "*", "/":
                if (left == INT && right == INT) yield INT;
                if ((left == INT || left == FLOAT) && (right == INT || right == FLOAT)) yield FLOAT;
                yield ERROR;

            case "%":
                if (left == INT && right == INT) yield INT;
                yield ERROR;

            case ".":
                if (left == STRING && right == STRING) yield STRING;
                yield ERROR;

            case "<", ">":
                if ((left == INT || left == FLOAT) && (right == INT || right == FLOAT)) yield BOOL;
                yield ERROR;

            case "==", "!=":
                if ((left == INT || left == FLOAT) && (right == INT || right == FLOAT)) yield BOOL;
                if (left == STRING && right == STRING) yield BOOL;
                yield ERROR;

            case "&&", "||":
                if (left == BOOL && right == BOOL) yield BOOL;
                yield ERROR;

            case "=":
                if (left == INT && right == FLOAT) yield INT;
                if (left == FLOAT && right == INT) yield FLOAT;
                if (left == STRING && right == STRING) yield STRING;
                if (left == BOOL && right == BOOL) yield BOOL;
                yield ERROR;

            default:
                yield ERROR;
        };
    }
}