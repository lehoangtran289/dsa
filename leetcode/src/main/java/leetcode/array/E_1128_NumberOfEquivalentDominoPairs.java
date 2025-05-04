package leetcode.array;

public class E_1128_NumberOfEquivalentDominoPairs {

    /**
     * Count the number of equivalent domino pairs
     * Approach: Count the frequency of each domino
     * ----
     * TC: O(n)
     * SC: O(1)
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        final int MAX_VALUE = 9;
        int[] freq = new int[MAX_VALUE * MAX_VALUE + MAX_VALUE + 1];

        for (int[] d : dominoes) {
            if (d[0] <= d[1]) {
                freq[d[0] * MAX_VALUE + d[1]]++;
            } else {
                freq[d[1] * MAX_VALUE + d[0]]++;
            }
        }

        int res = 0;
        for (int f : freq) {
            if (f > 1) {
                res += f * (f - 1) / 2;
            }
        }

        return res;
    }
}
