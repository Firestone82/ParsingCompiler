package cz.mik0486.pjp.project.antlr;

public enum Type {
    INT, FLOAT, BOOL, STRING, VOID, ERROR; // ERROR for incompatible types or operations

    /**
     * Determines the result type of a binary operation given two operand types.
     * This is a simplistic implementation and might need to be extended based on the language specifications.
     */
    public static Type resultType(Type left, Type right, String operator) {
        if (left == ERROR || right == ERROR) return ERROR;

        switch (operator) {
            case "+":
            case "-":
            case "*":
            case "/":
                if (left == INT && right == INT) return INT;
                if ((left == INT || left == FLOAT) && (right == INT || right == FLOAT)) return FLOAT;
                return ERROR;

            case "<":
            case ">":
            case "==":
            case "!=":
                return BOOL;

            case "%":
                if (left == INT && right == INT) return INT;
                return ERROR;

            case ".":
                if (left == STRING && right == STRING) return STRING;
                return ERROR;

            case "=":
                if (left == INT && right == FLOAT) return INT;
                if (left == FLOAT && right == INT) return FLOAT;
                return ERROR;

            case "&&":
            case "||":
                if (left == BOOL && right == BOOL) return BOOL;
                return ERROR;

            default:
                return ERROR;
        }
    }
}