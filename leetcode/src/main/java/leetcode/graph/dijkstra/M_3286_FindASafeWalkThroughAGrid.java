package leetcode.graph.dijkstra;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

public class M_3286_FindASafeWalkThroughAGrid {

    private static final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * Dijkstra
     * ---
     * TC: O(n * m log(n * m))
     * SC: O(n * m)
     */
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int rows = grid.size(), cols = grid.get(0).size();
        int[][] costs = new int[rows][cols];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for (int[] row : costs) Arrays.fill(row, 1 << 30);
        pq.add(new int[]{0, 0, grid.get(0).get(0)});
        costs[0][0] = grid.get(0).get(0);

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int[] dir : DIRS) {
                int nextX = cur[0] + dir[0];
                int nextY = cur[1] + dir[1];

                if (isCellValid(nextX, nextY, rows, cols)) {
                    int nextCost = cur[2] + grid.get(nextX).get(nextY);

                    if (nextCost < costs[nextX][nextY]) {
                        costs[nextX][nextY] = nextCost;
                        pq.add(new int[]{nextX, nextY, nextCost});
                    }
                }
            }
        }
        return costs[rows - 1][cols - 1] < health;
    }


    /**
     * 0-1 BFS
     * Idea: Use a deque to perform a 0-1 BFS, where we prioritize moving to cells (0) over cells (1).
     * ---
     * TC: O(n * m)
     * SC: O(n * m)
     */
    public boolean findSafeWalk2(List<List<Integer>> grid, int health) {
        int rows = grid.size(), cols = grid.get(0).size();
        int[][] costs = new int[rows][cols];
        Deque<int[]> deque = new ArrayDeque<>();

        for (int[] row : costs) Arrays.fill(row, 1 << 30);
        deque.addLast(new int[]{0, 0, grid.get(0).get(0)});
        costs[0][0] = grid.get(0).get(0);

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();

            for (int[] dir : DIRS) {
                int nextX = cur[0] + dir[0];
                int nextY = cur[1] + dir[1];

                if (
                        isCellValid(nextX, nextY, rows, cols)
                        && cur[2] + grid.get(nextX).get(nextY) < costs[nextX][nextY]
                ) {
                    int nextCost = cur[2] + grid.get(nextX).get(nextY);
                    costs[nextX][nextY] = nextCost;

                    // If the next cell is a 1, we add it to the BACK of deque and cost++
                    if (grid.get(nextX).get(nextY) == 1) {
                        deque.addLast(new int[]{nextX, nextY, nextCost});
                    } else {
                        deque.addFirst(new int[]{nextX, nextY, nextCost});
                    }
                }
            }
        }
        return costs[rows - 1][cols - 1] < health;
    }

    private boolean isCellValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
