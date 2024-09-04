package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _0_KMP {

    /**
     * Fills lps[] for given pattern pat
      */
    static void computeLPSArray(String pattern, int M, int[] lps) {
        // Length of the previous longest prefix suffix
        int len = 0;

        // lps[0] is always 0
        lps[0] = 0;

        // Loop calculates lps[i] for i = 1 to M-1
        int i = 1;
        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    /**
     *  Prints occurrences of pattern in text
     */
    static List<Integer> KMPSearch(String pattern, String text) {
        int M = pattern.length();
        int N = text.length();

        // Create lps[] that will hold the longest prefix
        // suffix values for pattern
        int[] lps = new int[M];
        List<Integer> result = new ArrayList<>();

        // Preprocess the pattern (calculate lps[] array)
        computeLPSArray(pattern, M, lps);

        int i = 0; // index for text
        int j = 0; // index for pattern
        while ((N - i) >= (M - j)) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }

            if (j == M) {
                result.add(i - j + 1);
                j = lps[j - 1];
            } else if (i < N && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return result;
    }

    // Driver code
    public static void main(String[] args) {
        String txt = "geeksforgeeks";
        String pat = "geeks";

        List<Integer> result = KMPSearch(pat, txt);

        // Print all the occurrences (1-based indices)
        for (int index : result) {
            System.out.print(index + " ");
        }
    }
}
