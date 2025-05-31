package codility;

import java.util.Arrays;

public class _5_M_GenomicRangeQuery {
    public static void main(String[] args) {
        String S = "CAGCCTA";
        int[] P = {2, 5, 0};
        int[] Q = {4, 5, 6};
        System.out.println(Arrays.toString(solution(S, P, Q))); // [2, 4, 1]
    }

    /**
     * Find the minimal nucleotide from a range of DNA sequence.
     * Idea: Use prefix sums to count occurrences of each nucleotide.
     * ---
     * TC: O(n + m) ~ where n is the length of S and m is the length of P/Q
     * SC: O(n)
     */
    public static int[] solution(String S, int[] P, int[] Q) {
        // Implement your solution here
        int n = S.length();
        int[] A = new int[n + 1];
        int[] C = new int[n + 1];
        int[] G = new int[n + 1];

        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);

            if (c == 'A') {
                A[i + 1] = A[i] + 1;
                C[i + 1] = C[i];
                G[i + 1] = G[i];
            } else if (c == 'C') {
                A[i + 1] = A[i];
                C[i + 1] = C[i] + 1;
                G[i + 1] = G[i];
            } else if (c == 'G') {
                A[i + 1] = A[i];
                C[i + 1] = C[i];
                G[i + 1] = G[i] + 1;
            } else {
                A[i + 1] = A[i];
                C[i + 1] = C[i];
                G[i + 1] = G[i];
            }
        }

        int len = P.length;
        int[] res = new int[len];

        for (int i = 0; i < len; ++i) {
            int start = P[i], end = Q[i];

            int aCount = A[end + 1] - A[start];
            int cCount = C[end + 1] - C[start];
            int gCount = G[end + 1] - G[start];

            if (aCount != 0) res[i] = 1;
            else if (cCount != 0) res[i] = 2;
            else if (gCount != 0) res[i] = 3;
            else res[i] = 4;
        }

        return res;
    }
}
