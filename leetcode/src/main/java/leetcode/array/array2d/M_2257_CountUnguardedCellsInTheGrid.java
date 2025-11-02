package leetcode.array.array2d;

public class M_2257_CountUnguardedCellsInTheGrid {
    private static final int UNGUARDED = 0;
    private static final int GUARDED = 1;
    private static final int GUARD = 2;
    private static final int WALL = 3;

    public static void main(String[] args) {
        System.out.println(countUnguarded(4, 6, new int[][]{{0, 0}, {1, 1}, {2, 3}}, new int[][]{{0, 1}, {2, 2}, {1, 4}}));
    }

    public static int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] map = new int[m][n];

        for (int[] w : walls) {
            map[w[0]][w[1]] = WALL;
        }

        for (int[] g : guards) {
            map[g[0]][g[1]] = GUARD;
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] g : guards) {
            int row = g[0];
            int col = g[1];

            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];

                while (r >= 0 && r < m && c >= 0 && c < n) {
                    if (map[r][c] == WALL || map[r][c] == GUARD) {
                        break;
                    }
                    map[r][c] = GUARDED;
                    r += dir[0];
                    c += dir[1];
                }
            }
        }

        int res = 0;
        for (int[] row : map) {
            for (int cell : row) {
                if (cell == UNGUARDED) res++;
            }
        }
        return res;
    }
}
