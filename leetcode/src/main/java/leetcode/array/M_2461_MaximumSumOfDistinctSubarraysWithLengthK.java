package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class M_2461_MaximumSumOfDistinctSubarraysWithLengthK {
    public static void main(String[] args) {
        System.out.println(maximumSubarraySum(new int[]{1,5,4,2,9,9,9}, 3));
    }

    // TODO
    public static long maximumSubarraySum(int[] nums, int k) {
        int l = 0, r = k - 1;
        long res = 0;

        Set<Integer> set = new HashSet<>();
        for (int i = l; i <= r; ++i) {
            set.add(nums[i]);
        }

        while (r < nums.length) {

            r++;
        }

        return res;
    }
}
