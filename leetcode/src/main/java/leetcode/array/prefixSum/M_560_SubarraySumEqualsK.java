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
     * Prefix Sum + HashMap O(n)
     * Idea: store the frequency of prefix sums in a map
     * ----------------------------------
     * TC: O(n)
     * SC: O(n)
     */
    public static int subarraySum3(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>(); // prefixSum -> count
        int prefixSum = 0;

        for (int num : nums) {
            prefixSum += num;
            if (prefixSum == k) res++;

            res += freq.getOrDefault(prefixSum - k, 0);
            freq.put(prefixSum, freq.getOrDefault(prefixSum, 0) + 1);
        }

        return res;
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
}
