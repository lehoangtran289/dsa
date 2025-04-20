package leetcode.array.prefixSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_560_SubarraySumEqualsK {
    public static void main(String[] args) {
        System.out.println(subarraySum3(new int[]{-1, 1, 0}, 3)); // 3
        System.out.println(subarraySum3(new int[]{1, 2, 3}, 3)); // 2
    }

    /**
     * 1. PREFIX SUM O(n^2)
     * sum(i -> j) = prefixSum[j + 1] - prefixSum[i]
     */
    public static int subarraySum(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];

        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        System.out.println(Arrays.toString(prefixSum));

        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j < nums.length; ++j) {
                if (prefixSum[j + 1] - prefixSum[i] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 2. NO ADDITIONAL SPACE O(n^2)
     */
    public static int subarraySum2(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            int sum = 0;
            for (int j = i; j < nums.length; ++j) {
                sum += nums[j];
                if (sum == k) res++;
            }
        }
        return res;
    }

    /**
     * 3. PREFIX SUM + HASHMAP O(n)
     * [1, 1, 1] -> [1, 2, 3]
     * - prefSum = k -> res++
     * - prefSum = X + k -> res += map[X]
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static int subarraySum3(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> prefMap = new HashMap<>();
        int prefixSum = 0;

        for (int num : nums) {
            prefixSum += num;
            if (prefixSum == k) res++;

            res += prefMap.getOrDefault(prefixSum - k, 0);
            prefMap.put(prefixSum, prefMap.getOrDefault(prefixSum, 0) + 1);
        }

        return res;
    }
}
