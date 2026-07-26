package leetcode.graph.dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class H_4003_MinimumCostPathWithAlternatingDirectionsIII {

    private static final int[][] DIRS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // R, D, L, U

    /**
     * Dijkstra with state
     * ---
     * TC: O(n * m log(n * m))
     * SC: O(n * m)
     */
    public long minCost(int rows, int cols, int[][] penalty) {
        long[][][] dist = new long[rows][cols][2]; // [x, y, par] -> minCost
        PriorityQueue<State> minHeap = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));

        // init states
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                Arrays.fill(dist[i][j], Long.MAX_VALUE);
            }
        }
        dist[0][0][0] = 1;
        minHeap.add(new State(0, 0, 0, 1));

        while (!minHeap.isEmpty()) {
            State cur = minHeap.poll();
            int row = cur.row;
            int col = cur.col;
            int par = cur.par;
            long cost = cur.cost;

            int nextPar = (par + 1) % 2;

            // case 1: wait in current cell -> pay penalty
            long waitCost = cost + penalty[row][col];

            if (waitCost < dist[row][col][nextPar]) {
                dist[row][col][nextPar] = waitCost;
                minHeap.add(new State(row, col, nextPar, waitCost));
            }

            // case 2: move 4 directions
            for (int dir = 0; dir < 4; ++dir) {
                int nextRow = row + DIRS[dir][0];
                int nextCol = col + DIRS[dir][1];

                if (!isValidCell(nextRow, nextCol, rows, cols)) continue;

                // init move cost to next cell
                long moveCost = cur.cost + (long) (nextRow + 1) * (nextCol + 1);

                // check for penalty
                if ((nextPar == 0 && dir <= 1) || (nextPar == 1 && dir >= 2)) {
                    moveCost += penalty[row][col];
                }

                if (moveCost < dist[nextRow][nextCol][nextPar]) {
                    dist[nextRow][nextCol][nextPar] = moveCost;
                    minHeap.add(new State(nextRow, nextCol, nextPar, moveCost));
                }
            }
        }

        return Math.min(
                dist[rows - 1][cols - 1][0],
                dist[rows - 1][cols - 1][1]
        );
    }

    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    static class State {
        int row;
        int col;
        int par;
        long cost;

        State(int row, int col, int par, long cost) {
            this.row = row;
            this.col = col;
            this.par = par;
            this.cost = cost;
        }
    }
}
