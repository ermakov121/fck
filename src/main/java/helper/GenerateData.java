package helper;

import java.util.Random;

public class GenerateData {
    public static String genEmail() {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return String.valueOf(n);
    }
}
