package leetcode.array;

import java.util.Arrays;

public class E_3379_TransformedArray {
    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(constructTransformedArray(new int[]{3, -2, 1, 1}))
        ); // [1, 1, 1, 3]
    }

    /**
     * Circular Array 2 ways
     * -----------
     * Idea: ((i + nums[i]) % n + n) % n;
     * -> Even if nums[i] < 0 -> index will be positive
     * -----------
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; ++i) {
            int resIndex = ((i + nums[i]) % n + n) % n;
            res[i] = nums[resIndex];
        }

        return res;
    }
}
