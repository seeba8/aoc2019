package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Utils {
    /**
     * Creates all permutations (lexicographic order) based on Narayana Pandita's algorithm from Wikipedia
     *
     * @param input
     * @return
     */
    public static int[][] getPermutations(int[] input) {
        int len = input.length;
        int[] buf = input.clone();
        Arrays.sort(buf);
        List<int[]> permutations = new ArrayList<>();
        permutations.add(buf.clone());
        while (true) {
            int k = len - 2;
            while (buf[k] > buf[k + 1]) {
                k--;
                if (k == -1) return permutations.toArray(new int[][]{});
            }
            int l = len - 1;
            while (buf[l] < buf[k]) {
                l--;
            }

            int tmp = buf[k];
            buf[k] = buf[l];
            buf[l] = tmp;

            reverse(buf, k + 1);
            permutations.add(buf.clone());
        }
    }

    private static void reverse(int[] arr, int startIndex) {
        int[] buf = arr.clone();
        for (int i = startIndex; i < arr.length; i++) {
            arr[i] = buf[buf.length - (i - startIndex) - 1];
        }
    }


    public static int factorial(int number) {
        if (number < 0) return 0;
        if (number == 0) return 1;
        return number * factorial(number - 1);

    }

    public static long gcd(long a, long b) {
        // https://en.wikipedia.org/wiki/Greatest_common_divisor#Calculation
        if (b > a) return gcd(b, a);
        if(a % b == 0)  return b;
        return gcd(b, a % b);
    }

    public static long gcd(long[] values) {
        if(values.length < 2) throw new IllegalArgumentException("Array needs to be 2 or longer");
        long gcd = gcd(values[0], values[1]);
        for (int i = 2; i < values.length; i++) {
            gcd = gcd(gcd, values[i]);
        }
        return gcd;
    }

    public static long lcm(long a, long b) {
        // https://stackoverflow.com/a/4202114
        if (b > a) return lcm(b, a);
        return a * (b / gcd(a, b));
    }

    public static long lcm(long[] values) {
        if(values.length < 2) throw new IllegalArgumentException("Array needs to be 2 or longer");
        long lcm = lcm(values[0], values[1]);
        for (int i = 2; i < values.length; i++) {
            lcm = lcm(lcm, values[i]);
        }
        return lcm;
    }

    public static int dotProduct(int[] a1, int[] a2) {
        int sum = 0;
        for (int i = 0; i < Math.max(a1.length, a2.length); i++) {
            sum += (a1.length <= i ? 0 : a1[i]) * (a2.length <= i ? 0 : a2[i]);
        }
        return sum;
    }

    public static int dotProductModulo(int[] a1, int[] a2) {
        int sum = 0;
        for (int i = 0; i < Math.max(a1.length, a2.length); i++) {
            sum += a1[i % a1.length] * a2[i % a2.length];
        }
        return sum;
    }

}
