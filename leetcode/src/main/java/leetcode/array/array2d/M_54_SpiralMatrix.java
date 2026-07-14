package leetcode.array.array2d;

import java.util.ArrayList;
import java.util.List;

public class M_54_SpiralMatrix {
    private static final int[][] DIRS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // R, D, L, U

    static void main() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(spiralOrder(matrix)); // Output: [1, 2, 3, 6, 9, 8, 7, 4, 5]
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        boolean[][] visited = new boolean[rows][cols];
        int dir = 0;

        visited[0][0] = true;
        res.add(matrix[0][0]);
        int i = 0, j = 0;
        int dirChange = 0;

        while (dirChange < 2) {
            int nextX = i + DIRS[dir][0];
            int nextY = j + DIRS[dir][1];

            if (isCellValid(nextX, nextY, rows, cols) && !visited[nextX][nextY]) {
                i = nextX;
                j = nextY;
                res.add(matrix[i][j]);
                visited[i][j] = true;
                dirChange = 0;
            } else {
                dir = (dir + 1) % 4;
                dirChange++;
            }
        }

        return res;
    }

    private static boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
