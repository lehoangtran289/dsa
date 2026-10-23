package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class M_523_ContinuousSubarraySum {

    static void main() {
        System.out.println(checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6)); // true
        System.out.println(checkSubarraySum(new int[]{0, 0}, 10)); // true
    }

    /**
     * Idea: subarray sum % k == 0 -> use prefix sum and store map of first modulo index occurrence
     * If map already contains an index with same modulo -> valid subarray
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> moduloIndices = new HashMap<>();
        int prefixSum = 0;

        for (int i = 0; i < nums.length; ++i) {
            prefixSum += nums[i];
            int mod = prefixSum % k;

            if (i > 0 && mod == 0) return true;

            if (moduloIndices.containsKey(mod)) {
                if (i - moduloIndices.get(mod) > 1) return true;
            } else {
                moduloIndices.put(mod, i);
            }
        }

        return false;
    }
}
