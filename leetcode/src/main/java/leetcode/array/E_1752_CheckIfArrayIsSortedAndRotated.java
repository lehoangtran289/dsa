package leetcode.array;

public class E_1752_CheckIfArrayIsSortedAndRotated {

    /**
     * Check if an array is sorted and rotated
     * ------------
     * TC: O(n)
     * SC: O(1)
     */
    public boolean check(int[] nums) {
        int n = nums.length;
        int pivot = -1;

        for (int i = 0; i < n - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                if (pivot != -1) return false;

                pivot = i + 1;
            }
        }

        return pivot == -1 ? true : nums[0] >= nums[n - 1];
    }
}
