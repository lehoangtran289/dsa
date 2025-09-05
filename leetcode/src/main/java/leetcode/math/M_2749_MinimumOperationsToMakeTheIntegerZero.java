package leetcode.math;

public class M_2749_MinimumOperationsToMakeTheIntegerZero {

    /**
     * Find min c, i from [1, 60], such that:
     * num1 = c * 2^i + c * num2
     * ------------
     * Idea: to make an integer n = 0, we need at least (bitCount of n) operations by only subtracting powers of 2
     * range of the result is [bitCount, 60]
     * ------------
     * TC: O(log num1)
     * SC: O(1)
     */
    public int makeTheIntegerZero(int num1, int num2) {
        for (int i = 1; i <= 60; ++i) {
            long temp = num1 - (long) i * num2;

            if (temp < i) break;
            if (Long.bitCount(temp) <= i) return i;
        }

        return -1;
    }
}
