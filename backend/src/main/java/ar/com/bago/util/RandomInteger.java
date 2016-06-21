package ar.com.bago.util;

import java.util.Date;
import java.util.Random;

public class RandomInteger {

    private static final Random RANDOM = new Random(new Date().getTime());

    public static Integer getNext() {
        return RANDOM.nextInt();
    }
}
