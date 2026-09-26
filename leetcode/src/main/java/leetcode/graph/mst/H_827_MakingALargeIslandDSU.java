package leetcode.graph.mst;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class H_827_MakingALargeIslandDSU {

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U, D, L, R
    private int rows, cols;

    /**
     * DSU 2d grid
     * ---
     * TC: O(rows * cols)
     * SC: O(rows * cols)
     */
    public int largestIsland(int[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        UnionFind dsu = createDsu(grid);

        int res = dsu.maxSize();

        // traverse and try to merge islands together
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] != 0) continue;

                int mergedSize = 1;
                Set<Integer> seen = new HashSet<>();
                for (int[] dir : DIRS) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];

                    if (isValidCell(nx, ny) && grid[nx][ny] == 1) {
                        int islandId = dsu.find(encode(nx, ny));

                        if (!seen.contains(islandId)) {
                            mergedSize += dsu.size(islandId);
                            seen.add(islandId);
                        }
                    }
                }
                res = Math.max(res, mergedSize);
            }
        }
        return res;
    }

    private UnionFind createDsu(int[][] grid) {
        UnionFind dsu = new UnionFind(rows * cols + 1);

        // build DSU
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 0) continue;

                int id = encode(i, j);

                for (int[] dir : DIRS) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];

                    if (isValidCell(nx, ny) && grid[nx][ny] == 1) {
                        int nextId = encode(nx, ny);
                        dsu.union(id, nextId);
                    }
                }
            }
        }
        return dsu;
    }

    private int encode(int x, int y) {
        return x * rows + y;
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    static class UnionFind {
        int[] lab;

        UnionFind(int n) {
            this.lab = new int[n];
            Arrays.fill(lab, -1);
        }

        int find(int u) {
            return lab[u] < 0 ? u : (lab[u] = find(lab[u]));
        }

        void union(int u, int v) {
            int x = find(u);
            int y = find(v);

            if (x == y) return;
            lab[x] += lab[y];
            lab[y] = x;
        }

        int size(int u) {
            return -lab[find(u)];
        }

        int maxSize() {
            int res = 0;
            for (int val : lab) {
                if (val < 0) res = Math.max(res, -val);
            }
            return res;
        }
    }
}
