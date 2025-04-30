package leetcode.array.twopointers;

public class H_2444_CountSubarraysWithFixedBound {
    public static void main(String[] args) {
        System.out.println(countSubarrays(new int[]{1, 3, 5, 2, 7, 5}, 1, 5)); // 2
        System.out.println(countSubarrays(new int[]{1, 1, 1, 1}, 1, 1)); // 10
    }

    /**
     * Idea: Count how many valid subarrays ending at i-th
     * -----
     * 3 pointers :
     * - leftBound = most recent element out of range [minK, maxK]
     * - maxPos = most recent element == maxK
     * - minPos = most recent element == minK
     * -----
     * -> minimum valid subarray ending at i-th is [min(minPos, maxPos), i]
     */
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        long res = 0;
        int n = nums.length;
        int leftBound = -1, maxPos = -1, minPos = -1;

        for (int i = 0; i < n; ++i) {
            if (nums[i] == minK) minPos = i;
            if (nums[i] == maxK) maxPos = i;
            if (nums[i] < minK || nums[i] > maxK) leftBound = i;

            res += Math.max(0, Math.min(minPos, maxPos) - leftBound);
        }

        return res;
    }
}
