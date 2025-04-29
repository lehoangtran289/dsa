package leetcode.array.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The number of distinct elements in the subarray is equal to the number of distinct elements in the whole array.
 */
public class M_2799_CountCompleteSubarraysInAnArray {

    public static void main(String[] args) {
        System.out.println(countCompleteSubarrays(new int[]{1, 3, 1, 2, 2,})); // 4
    }

    /**
     * Sliding Window
     * TC: O(n)
     * SC: O(n)
     */
    public static int countCompleteSubarrays(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) seen.add(num);
        int uniqueCount = seen.size();

        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length;
        int res = 0;
        int l = 0;

        for (int r = 0; r < n; ++r) {
            freq.put(nums[r], freq.getOrDefault(nums[r], 0) + 1);

            while (l <= r && freq.size() == uniqueCount) {
                freq.put(nums[l], freq.get(nums[l]) - 1);
                if (freq.get(nums[l]) == 0) freq.remove(nums[l]);
                l++;
            }

            // For current r, there are l subarrays ending at r that have D distinct elements.
            res += l;
        }

        return res;
    }
}
