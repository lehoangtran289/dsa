package leetcode.array;

public class E_3105_LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {
    static void main() {
        System.out.println(longestMonotonicSubarray(new int[]{1,4,3,3,2}));
    }

    /**
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    public static int longestMonotonicSubarray(int[] nums) {
        int maxIncrLength = 1, maxDecrLength = 1;
        int curIncrLength = 1, curDecrLength = 1;

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] < nums[i]) { // strictly increasing
                curIncrLength++;
                curDecrLength = 1;
            } else if (nums[i - 1] > nums[i]) { // strictly decreasing
                curDecrLength++;
                curIncrLength = 1;
            } else {
                curIncrLength = 1;
                curDecrLength = 1;
            }

            maxDecrLength = Math.max(maxDecrLength, curDecrLength);
            maxIncrLength = Math.max(maxIncrLength, curIncrLength);
        }
        return Math.max(maxIncrLength, maxDecrLength);
    }
}
