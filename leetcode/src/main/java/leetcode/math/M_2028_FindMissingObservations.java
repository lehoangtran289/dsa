package leetcode.math;

import java.util.Arrays;

public class M_2028_FindMissingObservations {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(missingRolls(new int[]{3, 2, 4, 3}, 4, 2)) // [6, 6]
        );
    }

    /**
     * Given an array of integers rolls and two integers mean and n,
     * return an array of n integers representing the missing rolls.
     * ------------------------
     * Idea: Calculate the total sum of rolls, then derive the missing rolls based on the mean.
     * ------------------------
     * TC: O(m + n)
     * SC: O(n)
     */
    public static int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int[] res = new int[n];
        int totalSum = mean * (n + m);

        // find sum of m rolls
        int sumOfM = 0;
        for (int r : rolls) sumOfM += r;

        // find sum of n rolls
        int sumOfN = totalSum - sumOfM;

        // edge cases
        if (sumOfN < n || sumOfN > 6 * n) return new int[0];

        // process
        int part = sumOfN / n;
        int remain = sumOfN % n;

        for (int i = 0; i < n; ++i) {
            int cur = part;
            if (remain > 0) {
                remain--;
                cur++;
            }
            res[i] = cur;
        }

        return res;
    }
}
