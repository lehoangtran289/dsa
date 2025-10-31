package leetcode.math;

/**
 * Find 2 numbers that appear twice in an array of size n + 2 containing numbers from 0 to n - 1
 */
public class E_3289_TheTwoSneakyNumbersOfDigitville {
    /**
     * Hash table
     * ----------------
     * TC: O(n)
     * SC: O(n)
     */
    public int[] getSneakyNumbers0(int[] nums) {
        int[] res = new int[2];
        int[] seen = new int[nums.length - 2];
        int index = 0;

        for (int num : nums) {
            if (seen[num] > 0) res[index++] = num;
            seen[num]++;
        }

        return res;
    }

    /**
     * Math
     * ----------------
     * Formula:
     * Sum of [0, n]: S = n * (n + 1) / 2
     * Squared sum of [0, n]: SS = n * (n + 1) * (2n + 1) / 6
     * ----------------
     * TC: O(n)
     * SC: O(1)
     */
    public int[] getSneakyNumbers1(int[] nums) {
        int n = nums.length - 2; // true n
        int sum = 0, squareSum = 0;

        for (int num : nums) {
            sum += num;
            squareSum += num * num;
        }

        double sum2 = sum - (n * (n - 1)) / 2.0;
        double squaredSum2 = squareSum - (n * (n - 1) * (2 * n - 1)) / 6.0;

        double sqrted = Math.sqrt(2 * squaredSum2 - sum2 * sum2);
        int x1 = (int) ((sum2 - sqrted) / 2);
        int x2 = (int) ((sum2 + sqrted) / 2);

        return new int[]{x1, x2};
    }
}
