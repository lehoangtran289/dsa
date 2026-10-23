package leetcode.array.slidingwindow;

public class M_713_SubarrayProductLessThanK {
    static void main() {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100)); // 8
    }

    /**
     * Idea: If segment [l:r] is valid -> [l+1:r] is guaranteed to be valid as well
     *      But if segment [l:r] is valid -> [l:r+1] is not guaranteed to be valid
     * -> For each l, find max r where [l, r] is valid -> number of valid subarrays = r - l + 1
     * -> Use Sliding window
     * ---
     * TC: O(N)
     * SC: O(1)
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int result = 0;

        long curProduct = 1;
        int l = 0;
        for (int r = 0; r < nums.length; ++r) {
            curProduct *= nums[r];

            while (l <= r && curProduct >= k) {
                curProduct /= nums[l++];
            }

            result += r - l + 1;
        }

        return result;
    }
}
