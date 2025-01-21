package leetcode.array;

public class M_2017_GridGame {
    public static void main(String[] args) {
        System.out.println(gridGame(new int[][]{{20,3,20,17,2,12,15,17,4,15}, {20,10,13,14,15,5,2,3,14,3}}));
    }

    public static long gridGame(int[][] grid) {
        long topSum = 0;
        for (int i = 0; i < grid[0].length; ++i) {
            topSum += grid[0][i];
        }

        long res = Long.MAX_VALUE;
        long bottomSum = 0;

        for (int i = 0; i < grid[0].length; ++i) {
            topSum -= grid[0][i];

            // Calculate the maximum score left for the second robot using max(top, bottom)
            // Then Track the minimum "maximum score" possible using res 🏁.
            res = Math.min(res, Math.max(topSum, bottomSum));
            bottomSum += grid[1][i];
        }

        return res;
    }
}
