package cz.mik0486.pjp.project.antlr;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberUtils {

    public static float parseToFloat(Object number) {
        if (number instanceof Integer intNumber) {
            return intNumber.floatValue();
        } else if (number instanceof Float floatNumber) {
            return floatNumber;
        } else {
            throw new IllegalArgumentException("Number is not a float or int");
        }
    }
}
