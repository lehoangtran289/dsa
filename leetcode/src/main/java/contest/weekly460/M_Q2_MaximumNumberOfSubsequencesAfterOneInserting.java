package contest.weekly460;

public class M_Q2_MaximumNumberOfSubsequencesAfterOneInserting {
    public static void main(String[] args) {
        System.out.println(numOfSubsequences("LMCT"));
    }

    /**
     * Count max number of subsequences "LCT" in a string s after inserting one character.
     * ------------------
     * The idea is to count the number of subsequences "LCT" in the original string,
     * then consider the three possible insertions:
     * 1. Insert 'L' at the beginning
     * 2. Insert 'C' in the best position to maximize L_before * T_after
     * 3. Insert 'T' at the end
     * -------------------
     * TC: O(n)
     * SC: O(n) - for prefix and suffix arrays
     */
    public static long numOfSubsequences(String s) {
        int n = s.length();
        long lCount = 0, lcCount = 0, lctCount = 0;

        // count number of subsequences "LCT" in the original string
        for (char c : s.toCharArray()) {
            if (c == 'L') lCount++;
            else if (c == 'C') lcCount += lCount;
            else if (c == 'T') lctCount += lcCount;
        }

        // insert L at beginning
        long countL_L = 1, countLC_L = 0, countLCT_L = 0;
        for (char c : s.toCharArray()) {
            if (c == 'C') countLC_L += countL_L;
            else if (c == 'T') countLCT_L += countLC_L;
        }

        // insert T at end
        long countL_T = 0, countLC_T = 0, countLCT_T = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') countL_T++;
            else if (c == 'C') countLC_T += countL_T;
        }
        countLCT_T += countLC_T;

        // insert C in best position: maximize L_before * T_after
        long[] prefixL = new long[n + 1]; // Ls before i
        long[] suffixT = new long[n + 1]; // Ts after i

        for (int i = 0; i < n; i++) {
            prefixL[i + 1] = prefixL[i] + (s.charAt(i) == 'L' ? 1 : 0);
        }
        for (int i = n - 1; i >= 0; i--) {
            suffixT[i] = suffixT[i + 1] + (s.charAt(i) == 'T' ? 1 : 0);
        }

        long countLCT_C = 0;
        for (int i = 0; i <= n; i++) {
            countLCT_C = Math.max(countLCT_C, prefixL[i] * suffixT[i]);
        }

        return lctCount + Math.max(countLCT_L, Math.max(countLCT_C, countLCT_T));
    }
}
