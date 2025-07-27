package contest.weekly460;

import java.util.Arrays;

public class M_Q1_MaximumMedianSumOfSubsequencesOfSize3 {

    public long maximumMedianSum(int[] nums) {
        long res = 0;

        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            res += nums[r - 1];
            r -= 2;
            l++;
        }

        return res;
    }
}
