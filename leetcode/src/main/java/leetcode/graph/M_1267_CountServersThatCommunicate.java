package leetcode.graph;

public class M_1267_CountServersThatCommunicate {
    public static void main(String[] args) {
        System.out.println(countServers(new int[][]{{1, 0}, {1, 1}}));
    }

    public static int countServers(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[] rowCount = new int[rows];
        int[] colCount = new int[cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[i]++;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (
                        grid[i][j] == 1
                        && (rowCount[i] > 1 || colCount[j] > 1)
                ) {
                    res++;
                }
            }
        }

        return res;
    }
}
