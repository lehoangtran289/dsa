package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class M_1590_MakeSumDivisibleByP {
    public static void main(String[] args) {
        M_1590_MakeSumDivisibleByP solution = new M_1590_MakeSumDivisibleByP();
        System.out.println(solution.minSubarray(new int[]{3, 1, 4, 2}, 6)); // 1
        System.out.println(solution.minSubarray(new int[]{6, 3, 5, 2}, 9)); // 2
    }

    /**
     * Prefix Sum + HashMap
     * --------------------------
     * TC: O(n)
     * SC: O(n)
     * --------------------------
     */
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;

        // init base case and calculate target remainder
        int totalSum = 0;
        for (int num : nums) {
            totalSum = (totalSum + num) % p;
        }
        int remainder = totalSum % p;
        if (remainder == 0) return 0;

        // prefix sum approach
        int res = Integer.MAX_VALUE;
        int curSum = 0;
        Map<Integer, Integer> modIndexMap = new HashMap<>();
        modIndexMap.put(0, -1); // handle case when subarray starts at index 0

        for (int i = 0; i < n; ++i) {
            curSum = (curSum + nums[i]) % p;
            int target = (curSum - remainder + p) % p; // remainder needed to form valid subarray

            if (modIndexMap.containsKey(target)) {
                res = Math.min(res, i - modIndexMap.get(target));
            }
            modIndexMap.put(curSum, i);
        }

        return res == n ? -1 : res; // cannot remove the whole array
    }
}
