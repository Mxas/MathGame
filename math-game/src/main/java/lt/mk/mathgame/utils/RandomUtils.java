package lt.mk.mathgame.utils;

import java.util.Random;

public class RandomUtils {

    public static int next(int r) {
        if (r > 0) {
            return new Random().nextInt(r);
        }
        return 0;
    }
}
