package leetcode.bit;

public class M_2401_LongestNiceSubarray {
    /**
     * If two numbers have bitwise AND equal to zero, they do not have any common set bit.
     * Sliding window approach.
     * TC: O(N)
     * SC: O(1)
     */
    public static int longestNiceSubarray(int[] nums) {
        int res = 0;
        int mask = 0;
        int l = 0;

        for (int r = 0; r < nums.length; ++r) {
            // if cur num & mask != 0 ~ exists bit set equals in same position
            while ((mask & nums[r]) != 0) {
                mask ^= nums[l]; // remove left most element using XOR
                l++;
            }

            mask |= nums[r];
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
