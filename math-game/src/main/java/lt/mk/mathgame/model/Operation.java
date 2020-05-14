package lt.mk.mathgame.model;

import lt.mk.mathgame.utils.RandomUtils;

public enum Operation {
    PLUS('+'), MINUS('-'), MULTI('X'), DIV('/');

    private final char sign;

    Operation(char sign) {
        this.sign = sign;
    }

    public static Operation nextEasy() {
        return values()[RandomUtils.next(2)];
    }

    public String sign() {
        return String.valueOf(sign);
    }
}
