package leetcode.array.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class M_2958_LengthOfLongestSubarrayWithAtMostKFrequency {

    static void main() {
        System.out.println(maxSubarrayLength(new int[]{1, 4, 4, 5}, 1)); // 2  
    }

    public static int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int res = 1;
        Map<Integer, Integer> numCounts = new HashMap<>();

        int l = 0;
        for (int r = 0; r < n; ++r) {
            numCounts.put(nums[r], numCounts.getOrDefault(nums[r], 0) + 1);

            while (l <= r && numCounts.get(nums[r]) > k) {
                numCounts.put(nums[l], numCounts.get(nums[l]) - 1);
                if (numCounts.get(nums[l]) == 0)
                    numCounts.remove(nums[l]);
                l++;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

}
