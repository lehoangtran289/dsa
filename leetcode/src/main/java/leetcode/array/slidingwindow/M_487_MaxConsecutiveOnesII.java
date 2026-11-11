package leetcode.array.slidingwindow;

public class M_487_MaxConsecutiveOnesII {
    static void main() {
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 1, 0, 1})); // 4
        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1})); // 4
    }

    /**
     Idea: sliding window, since:
     - if [i, j] is valid -> [i + 1, j] is valid, [i, j + 1] is not guaranteed to be valid
     ---
     TC: O(N)
     SC: O(1)
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int zeroCount = 0;
        int l = 0;

        for (int r = l; r < nums.length; ++r) {
            if (nums[r] == 0) zeroCount++;

            while (zeroCount > 1) {
                zeroCount -= nums[l++];
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    /**
     Idea: maintain last zero index, if we encounter next zero -> calculate window size
     No inner loop, only work with 1 flip
     [start...lastZero...i] -> if nums[i] == 0 -> update start to lastZero + 1, lastZero to i
     */
    public int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0;
        int lastZero = -1;
        int start = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                start = lastZero + 1;
                lastZero = i;
            }
            res = Math.max(res, i - start + 1);
        }

        return res;
    }
}
