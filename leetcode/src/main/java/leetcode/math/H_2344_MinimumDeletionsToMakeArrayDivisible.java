package leetcode.math;

import java.util.Arrays;

public class H_2344_MinimumDeletionsToMakeArrayDivisible {

    /**
     * Idea: find global gcd
     * ---
     * TC: O(n logn + m)
     * SC: O(1)
     */
    public int minOperations(int[] nums, int[] numsDivide) {
        // find global gcd in numsDivide[]
        int globalGcd = numsDivide[0];
        for (int num : numsDivide) {
            globalGcd = gcd(globalGcd, num);
        }

        // sort nums and find min index that globalGcd can divide
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; ++i) {
            if (globalGcd < nums[i]) break;
            if (globalGcd % nums[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
