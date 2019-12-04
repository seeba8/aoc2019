package day4;

import java.util.HashMap;

public class Password {
    private int minimum;
    private int maximum;

    public Password(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public boolean isValid(int password) {
        return password >= minimum
                && password <= maximum
                && hasTwin(password)
                && isIncreasing(password);
    }

    public boolean hasTwin(int password) {
        return String.valueOf(password).matches(".*(.)\\1+.*");
    }

    public boolean hasExactTwin(int password) {
        HashMap<String, Integer> tokens = new HashMap<>();
        for (String s : String.valueOf(password).split("")) {
            tokens.merge(s, 1, Integer::sum);
        }
        return tokens.values().contains(2);
    }

    public boolean isIncreasing(int password) {
        String pw = String.valueOf(password);
        for (int i = 1; i < Math.log10(password); i++) {
            if (pw.charAt(i) < pw.charAt(i - 1)) return false;
        }
        return true;
    }

    public int countValid() {
        return countValid(false);
    }

    public int countValid(boolean exactTwin) {
        int c = 0;
        for (int i = minimum; i <= maximum; i++) {
            if (isValid(i) && (!exactTwin || hasExactTwin(i))) c++;
        }
        return c;
    }

    public static void main(String[] args) {
        Password p = new Password(123257, 647015);
        System.out.printf("There are %d possible passwords\n", p.countValid());
        System.out.printf("With exact twins, there are %d possible passwords\n", p.countValid(true));
    }
}
