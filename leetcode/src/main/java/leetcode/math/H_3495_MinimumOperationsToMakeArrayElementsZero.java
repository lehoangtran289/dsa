package leetcode.math;

public class H_3495_MinimumOperationsToMakeArrayElementsZero {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[][]{{1, 3}, {4, 6}})); // 5
        System.out.println(minOperations(new int[][]{{2, 5}, {7, 10}})); // 7
    }

    /**
     We can get # of operations in O(1) time using geometric series.

     Numbers [1,3] require 1 operation, sum = 3 * 1 = (4^1 - 1) * 1.
     Numbers [4,15] require 2 operations, sum = 12 * 2 = (4^2 - 4^1) * 2.
     Numbers [16,63] require 3 operations, sum = 48 * 3 = (4^3 - 4^2) * 3.
     ...
     Numbers [4^(k - 1), 4^k - 1] require k operations, sum = (4^k - 4^(k-1)) * k.

     Assume x is in the range [4^k, 4^(k+1) - 1], for numbers in this range we need k + 1 operations.

     sum[1,x]
     = sum[1,3] + sum[4,15] + ... + sum[4^(k-1), 4^k - 1] + sum[4^k, x]
     = sum[4^k, x] + sum[4^(k-1), 4^k - 1] + ... + sum[4,15] + sum[1,3]
     = (x + 1 - 4^k) * (k + 1) + (4^k - 4^(k-1)) * k + ... + (4^2 - 4^1) * 2 + (4^1 - 1) * 1
     = (x + 1) * (k + 1) - 4^k - 4^(k-1) - ... - 4^1 - 1
     = (x + 1) * (k + 1) - (4^k + 4^(k-1) + ... + 4^1 + 1)
     */
    public static long minOperations(int[][] queries) {
        long res = 0;

        for (int[] q : queries) {
            long countL = getOps(q[0] - 1);
            long countR = getOps(q[1]);

            res += (countR - countL + 1) / 2;
        }

        return res;
    }

    // S(x) = sum of required operations for all numbers in [1..x]
    private static long getOps(long x) {
        if (x <= 0) return 0;

        // Find k such that 4^k <= x < 4^(k+1)
        long k = 0;
        while ((long) Math.pow(4, k) <= x) {
            k++;
        }
        k--;

        // sum: 1 + 4 + 4^2 + ... + 4^k
        long sum = 0;
        for (int i = 0; i <= k; ++i) {
            sum += (long) Math.pow(4, i);
        }

        // From derivation: S(x) = (x + 1) * (k + 1) - (1 + 4 + ... + 4^k)
        return (x + 1L) * (k + 1L) - sum;
    }
}
