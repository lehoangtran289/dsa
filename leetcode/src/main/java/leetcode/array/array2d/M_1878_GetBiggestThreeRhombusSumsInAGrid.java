package leetcode.array.array2d;

import java.util.TreeSet;

public class M_1878_GetBiggestThreeRhombusSumsInAGrid {

    private static final int[][] DIRS = new int[][]{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}};

    /**
     * Idea: Brute force all square size
     * ---
     * TC: O(mn * min(n, m)) ~ where n = number of rows, m = number of columns
     * SC: O(1)
     */
    public int[] getBiggestThree(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        TreeSet<Integer> minHeap = new TreeSet<>(Integer::compare);

        // start at left corner of the square
        for (int i = 0; i < rows; ++i) { // O(rows)
            for (int j = 0; j < cols; ++j) { // O(cols)
                for (int len = 0; len <= Math.min(rows, cols); ++len) { // O(min(rows, cols))
                    if (i - len < 0 || i + len >= rows || j + 2 * len >= cols) break;

                    if (len == 0) {
                        addToHeap(minHeap, grid[i][j]);
                        continue;
                    }

                    int sum = 0;
                    int x = i, y = j;
                    for (int[] dir : DIRS) {
                        for (int k = 0; k < len; ++k) {
                            sum += grid[x][y];
                            x += dir[0];
                            y += dir[1];
                        }
                    }

                    addToHeap(minHeap, sum);
                }
            }
        }

        int[] res = new int[minHeap.size()];
        int idx = res.length - 1;
        while (!minHeap.isEmpty()) {
            res[idx--] = minHeap.pollFirst();
        }
        return res;
    }

    private void addToHeap(TreeSet<Integer> minHeap, int num) {
        minHeap.add(num);
        if (minHeap.size() > 3) minHeap.pollFirst();
    }
}
