package leetcode.array.slidingwindow;

public class M_1004_MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int res = 0;
        int n = nums.length;
        int zeroCnt = 0;
        int l = 0;

        for (int r = 0; r < n; ++r) {
            if (nums[r] == 0) zeroCnt++;

            while (l <= r && zeroCnt > k) {
                if (nums[l] == 0) zeroCnt--;
                l++;
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
