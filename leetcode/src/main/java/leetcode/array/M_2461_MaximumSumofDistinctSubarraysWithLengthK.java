package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class M_2461_MaximumSumofDistinctSubarraysWithLengthK {
    public static void main(String[] args) {
        System.out.println(maximumSubarraySum(new int[] {1,5,4,2,9,9,9}, 3));
    }

    public static long maximumSubarraySum(int[] nums, int k) {
        long res = 0;
        int l = 0, r = 0;

        Set<Integer> set = new HashSet<>();
        long curSum = 0;
        while (r < nums.length) {
            while (r - l + 1 > k || set.contains(nums[r])) {
                curSum -= nums[l];
                set.remove(nums[l]);
                l++;
            }
            set.add(nums[r]);
            curSum += nums[r];

            if (r - l + 1 == k) {
                res = Math.max(res, curSum);
            }
            r++;
        }

        return res;
    }
}
