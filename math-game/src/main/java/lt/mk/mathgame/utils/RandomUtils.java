package lt.mk.mathgame.utils;

import java.util.Random;

public class RandomUtils {

    public static int next(int r) {
        return new Random().nextInt(r);
    }
}
