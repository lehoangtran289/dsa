package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class M_523_ContinuousSubarraySum {

    public static void main(String[] args) {
        System.out.println(checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6)); // true
        System.out.println(checkSubarraySum(new int[]{0, 0}, 10)); // true
    }

    /**
     * Prefix Sum & Hashing
     * -------------
     * Time: O(n)
     * Space: O(n)
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;

        // build prefix sum
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];

        for (int i = 1; i < n; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        // hashing to find mod
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            int mod = prefixSum[i] % k;

            if (mod == 0 && i >= 1) return true;

            if (map.containsKey(mod)) {
                if (i - map.get(mod) > 1) return true;
            } else {
                map.put(mod, i);
            }
        }

        return false;
    }
}
