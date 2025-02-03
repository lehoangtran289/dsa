package leetcode.array;

public class E_3105_LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {
    public static void main(String[] args) {
        System.out.println(longestMonotonicSubarray(new int[]{1,4,3,3,2}));
    }

    public static int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int res = 0;

        // strictly increasing
        int incr = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[i - 1]) {
                incr++;
            } else {
                incr = 1;
            }
            res = Math.max(res, incr);
        }

        // strictly decreasing
        int decr = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] < nums[i - 1]) {
                decr++;
            } else {
                decr = 1;
            }
            res = Math.max(res, decr);
        }

        return res;
    }
}
