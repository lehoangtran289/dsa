package leetcode.array;

import java.util.Arrays;

public class M_2033_MinimumOperationsToMakeAUniValueGrid {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[][]{{931, 128}, {639, 712}}, 73));
    }

    /**
     * sort + median
     * ---
     * TC: O(mn*log(mn))
     * SC: O(mn)
     */
    public static int minOperations(int[][] grid, int x) {
        int rows = grid.length, cols = grid[0].length;
        int[] nums = new int[rows * cols];
        int remain = -1;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (remain == -1) remain = grid[i][j] % x;
                if (grid[i][j] % x != remain) return -1;

                nums[i * cols + j] = grid[i][j];
            }
        }
        Arrays.sort(nums);

        int median = nums[nums.length / 2];

        int res = 0;
        for (int num : nums) {
            res += Math.abs(num - median) / x;
        }
        return res;
    }
}
