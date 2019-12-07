package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        while(true) {
            int k = len - 2;
            while (buf[k] > buf[k + 1]) {
                k--;
                if (k == -1) return permutations.toArray(new int[][]{});
            }
            int l = len-1;
            while (buf[l] < buf[k]) {
                l--;
            }

            int tmp = buf[k];
            buf[k] = buf[l];
            buf[l] = tmp;

            reverse(buf, k+1);
            permutations.add(buf.clone());
        }
    }

    private static void reverse(int[] arr, int startIndex) {
        int[] buf = arr.clone();
        for(int i = startIndex; i < arr.length; i++) {
            arr[i] = buf[buf.length - (i-startIndex) - 1];
        }
    }


    public static int factorial(int number) {
        if (number < 0) return 0;
        if (number == 0) return 1;
        return number * factorial(number - 1);

    }
}
