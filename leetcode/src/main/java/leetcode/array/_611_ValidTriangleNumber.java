package leetcode.array;

import java.util.Arrays;

public class _611_ValidTriangleNumber {
    public static void main(String[] args) {
        System.out.println(triangleNumber(new int[]{2, 2, 3, 4}));
    }

    public static int binarySearch(int[] nums, int target, int low) {
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target)
                high = mid - 1;
            else if (nums[mid] <= target)
                low = mid + 1;
        }
        return high;
    }

    public static int triangleNumber(int[] nums) {
        int count = 0;
        int n = nums.length;
        if (n < 3) return count;

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (binarySearch(nums, nums[i] + nums[j] - 1, j + 1) != -1) {
                    count++;
                }

                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] > nums[k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
