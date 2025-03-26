package leetcode.array;

import java.util.Arrays;

public class M_2033_MinimumOperationsToMakeAUniValueGrid {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[][]{{931, 128}, {639, 712}}, 73));
    }

    public static int minOperations(int[][] grid, int x) {
        int[] arr = new int[grid.length * grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                arr[i * grid[0].length + j] = grid[i][j];
            }
        }
        Arrays.sort(arr);
        int mid = arr[arr.length / 2];

        int res = 0;
        int remain = -1;
        for (int num : arr) {
            if (remain == -1) {
                remain = num % x;
            } else if (num % x != remain) {
                return -1;
            }

            res += Math.abs(num - mid) / x;
        }

        return res;
    }
}
