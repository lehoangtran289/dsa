package leetcode.array;

public class M_189_RotateArray {

    /**
     * Rotate an array to the right by k steps using reverse approach
     * ----------
     * TC: O(n)
     * SC: O(1)
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        rotate(nums, 0, n - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, n - 1);
    }

    private void rotate(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;

            l++;
            r--;
        }
    }

    /**
     * Intuitive O(n) space rotation approach
     */
    public static void rotate2(int[] nums, int k) {
        int n = nums.length;

        int[] temp = new int[n];
        for (int i = 0; i < n; ++i) {
            temp[(i + k) % n] = nums[i];
        }

        for (int i = 0; i < n; ++i) {
            nums[i] = temp[i];
        }
    }
}
