package leetcode.array;

public class M_189_RotateArray {

    /**
     * Rotate an array to the right by k steps using reverse approach
     * ----------
     * TC: O(n)
     * SC: O(1)
     */
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        if (start < 0 || end >= nums.length) return;

        while (start <= end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
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
