package leetcode.array.slidingwindow;

public class M_2962_CountSubarraysWhereMaxElementAppearsAtLeastKTimes {
    public static void main(String[] args) {
        System.out.println(countSubarrays(new int[]{1, 3, 2, 3, 3}, 2)); // 6
    }

    /**
     * -----------------------------------------------------------
     * Sliding window
     * -----------------------------------------------------------
     * TC: O(n)
     * SC: O(1)
     */
    public static long countSubarrays(int[] nums, int k) {
        long res = 0;
        int n = nums.length;
        int maxNum = 0;
        int curCount = 0;
        int l = 0;

        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        for (int r = 0; r < n; ++r) {
            if (nums[r] == maxNum) curCount++;

            while (l <= r && curCount == k) {
                if (nums[l] == maxNum) curCount--;
                l++;
            }
            res += l;
        }

        return res;
    }
}
