package leetcode.array;

public class M_487_MaxConsecutiveOnesII {
    public static void main(String[] args) {
        System.out.println(findMaxConsecutiveOnes2(new int[]{1, 1, 0, 1}));
        System.out.println(findMaxConsecutiveOnes2(new int[]{1, 0, 1, 1, 0, 1}));
    }

    private static final int MAX_ZEROS_FLIPPABLE = 1;

    /**
     * Brute force approach
     * O(n^2) -> Since every index has to check every other index.
     */
    public static int findMaxConsecutiveOnes1(int[] nums) {
        int res = 0;

        for (int i = 0; i < nums.length; ++i) {
            int zeroCount = 0;
            int j = i;
            while (j < nums.length) {
                if (nums[j] == 0) zeroCount++;

                if (zeroCount > MAX_ZEROS_FLIPPABLE) {
                    break;
                }
                ++j;
            }
            res = Math.max(res, j - i);
        }

        return res;
    }

    /**
     * Sliding window approach </br>
     * O(n) -> Since left and right pointers traverse a maximum of n steps </br>
     * Can work with flipping any number of zero(s) -> one(s)
     */
    public static int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0;
        int l = 0;
        int zeroCount = 0;

        for (int r = 0; r < nums.length; ++r) {
            if (nums[r] == 0) {
                zeroCount++;
            }

            if (zeroCount > MAX_ZEROS_FLIPPABLE) {
                while (l <= r && zeroCount > MAX_ZEROS_FLIPPABLE) {
                    if (nums[l] == 0) zeroCount--;
                    l++;
                }
            }

            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    /**
     * Counting approach, only works for flipping 1 zero.
     */
    public static int findMaxConsecutiveOnes3(int[] nums) {
        int res = 0;
        int totalOneCount = 0;
        int consecutiveOneCount = 0;

        for (int num : nums) {
            if (num == 1) {
                totalOneCount++;
                consecutiveOneCount++;
            } else {
                totalOneCount = consecutiveOneCount;
                totalOneCount++;
                consecutiveOneCount = 0;
            }

            res = Math.max(res, totalOneCount);
        }

        return res;
    }
}
