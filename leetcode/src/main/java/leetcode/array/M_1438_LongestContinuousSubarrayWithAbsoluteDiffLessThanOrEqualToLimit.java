package leetcode.array;

import java.util.TreeMap;

public class M_1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));
    }

    public static int longestSubarray(int[] nums, int limit) {
        int res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();

        int l = 0;
        for (int r = 0; r < nums.length; ++r) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);

            while (Math.abs(map.firstKey() - map.lastKey()) > limit) {
                l++;
                if (map.get(nums[l]) != 0) map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) map.remove(nums[l]);
            }

            res = Math.max(res, Math.abs(r - l) + 1);
        }

        return res;
    }
}
