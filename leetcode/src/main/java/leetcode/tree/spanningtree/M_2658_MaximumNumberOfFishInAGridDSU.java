package leetcode.tree.spanningtree;

public class M_2658_MaximumNumberOfFishInAGridDSU {
    public static void main(String[] args) {
        System.out.println(findMaxFish(new int[][]{{0, 2, 1, 0}, {4, 0, 0, 3}, {1, 0, 0, 4}, {0, 3, 2, 0}}));
    }

    public static int findMaxFish(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // left, right, up, down

        DisjointSet dsu = new DisjointSet(grid);
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] != 0) {
                    int index = calIndex(i, j, cols);

                    for (int[] dir : dirs) {
                        int nextX = i + dir[0];
                        int nextY = j + dir[1];

                        if (isCellValid(grid, nextX, nextY, rows, cols)) {
                            int nextIndex = calIndex(nextX, nextY, cols);
                            dsu.union(index, nextIndex);
                        }
                    }
                }
            }
        }

        return dsu.findMaxFish();
    }

    private static boolean isCellValid(int[][] grid, int r, int c, int rows, int cols) {
        return r >= 0 && c >= 0 && r < rows && c < cols && grid[r][c] != 0;
    }

    private static int calIndex(int i, int j, int cols) {
        return i * cols + j;
    }

    static class DisjointSet {
        private final int[] parent;
        private final int[] totalFish;

        public DisjointSet(int[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            int totalCells = rows * cols;

            parent = new int[totalCells];
            totalFish = new int[totalCells];

            for (int i = 0; i < totalCells; i++) {
                parent[i] = i;
            }

            // Set initial fish count for each cell
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int index = calIndex(i, j, cols);
                    totalFish[index] = grid[i][j];
                }
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return parent[x];
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                parent[rootY] = rootX;
                totalFish[rootX] += totalFish[rootY];
            }
        }

        // find max from root of group only
        public int findMaxFish() {
            int res = 0;

            for (int i = 0; i < parent.length; ++i) {
                if (find(i) == i) {
                    res = Math.max(res, totalFish[i]);
                }
            }

            return res;
        }
    }
}
