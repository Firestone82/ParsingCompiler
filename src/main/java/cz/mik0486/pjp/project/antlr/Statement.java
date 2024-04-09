package cz.mik0486.pjp.project.antlr;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statement {
    private String code;
    private Type type;

    public static Statement of(String code, Type type) {
        return new Statement(code, type);
    }
}
