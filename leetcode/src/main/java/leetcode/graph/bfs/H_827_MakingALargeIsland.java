package leetcode.graph.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class H_827_MakingALargeIsland {
    static void main() {
        H_827_MakingALargeIsland sol = new H_827_MakingALargeIsland();
        System.out.println(sol.largestIsland(new int[][]{{1, 1}, {1, 1}}));
        System.out.println(sol.largestIsland(new int[][]{{0, 0}, {0, 0}}));
        System.out.println(sol.largestIsland(new int[][]{{1, 0, 1}, {0, 1, 0}}));
        System.out.println(sol.largestIsland(new int[][]{{1, 1}, {1, 0}}));
    }

    /**
     * Idea: Assign each island an id,
     * Then try to switch each 0 -> 1 and connect island to see which one results in the largest island.
     * ---
     * TC: O(rows * cols)
     * SC: O(rows * cols)
     */
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // U, D, L, R
    private int[][] grid;
    private int rows, cols;
    private Map<Integer, Integer> islandSizeMap;
    private int res;

    public int largestIsland(int[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.grid = grid;
        this.islandSizeMap = new HashMap<>();

        // explore all islands, assign ids and put into islandSizeMap
        exploreIslands();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] != 0) continue;

                int mergeSize = 1;
                Set<Integer> mergedIsland = new HashSet<>();
                for (int[] dir : DIRS) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];

                    if (
                            isValidCell(nx, ny, rows, cols)
                            && grid[nx][ny] != 0
                            && !mergedIsland.contains(grid[nx][ny])
                    ) {
                        mergeSize += islandSizeMap.get(grid[nx][ny]);
                        mergedIsland.add(grid[nx][ny]);
                    }
                }

                res = Math.max(res, mergeSize);
            }
        }

        return res;
    }

    /**
     * explore all islands, assign ids and put into islandSizeMap
     */
    private void exploreIslands() {
        int curId = 2;

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 1) {
                    int islandSize = dfs(curId, new boolean[rows][cols], i, j);

                    res = Math.max(res, islandSize);
                    islandSizeMap.put(curId, islandSize);
                    curId++;
                }
            }
        }
    }

    private int dfs(
            int id,
            boolean[][] visited,
            int x,
            int y
    ) {
        if (!isValidCell(x, y, rows, cols) || visited[x][y] || grid[x][y] == 0) return 0;

        visited[x][y] = true;
        grid[x][y] = id;
        int size = 1;

        for (int[] dir : DIRS) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            size += dfs(id, visited, nx, ny);
        }
        return size;
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
