package leetcode.array;

public class E_3105_LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {
    public static void main(String[] args) {
        System.out.println(longestMonotonicSubarray(new int[]{1,4,3,3,2}));
    }

    public static int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int res = 0;

        // strictly increasing
        for (int i = 0; i < n; ++i) {
            int len = 1;
            for (int j = i + 1; j < n; ++j) {
                if (nums[j] > nums[j - 1]) {
                    len++;
                } else {
                    break;
                }
            }
            res = Math.max(res, len);
        }

        // strictly decreasing
        for (int i = 0; i < n; ++i) {
            int len = 1;
            for (int j = i + 1; j < n; ++j) {
                if (nums[j] < nums[j - 1]) {
                    len++;
                } else {
                    break;
                }
            }
            res = Math.max(res, len);
        }

        return res;
    }
}
