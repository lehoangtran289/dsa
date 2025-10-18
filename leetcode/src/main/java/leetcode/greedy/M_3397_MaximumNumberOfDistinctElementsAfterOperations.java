package leetcode.greedy;

import java.util.Arrays;

public class M_3397_MaximumNumberOfDistinctElementsAfterOperations {
    public static void main(String[] args) {
        System.out.println(maxDistinctElements(new int[]{1, 2, 2, 3, 3, 4}, 2)); // 6
    }

    /**
     * Greedy
     * TC: O(n log n)
     * SC: O(1)
     */
    public static int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);

        int res = 0;
        int leftBound = Integer.MIN_VALUE;

        for (int num : nums) {
            leftBound = Math.max(leftBound, num - k);
            int rightBound = num + k;

            if (leftBound <= rightBound) {
                leftBound++;
                res++;
            }
        }

        return res;
    }
}
