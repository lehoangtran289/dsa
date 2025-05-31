package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_909_SnakesAndLadder {

    /**
     * BFS
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        // init BFS
        boolean[][] visited = new boolean[n][n];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[n - 1][0] = true;
        queue.add(1);
        int dist = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int cur = queue.poll();

                // if end position is reached -> return
                if (cur == n * n) return dist;

                // BFS 6 dice rolls
                for (int next = cur + 1; next <= Math.min(cur + 6, n * n); next++) {
                    int[] nextPos = getPos(next, n);
                    int nextX = nextPos[0];
                    int nextY = nextPos[1];

                    if (!visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;

                        if (board[nextX][nextY] == -1) {
                            queue.add(next);
                        } else {
                            queue.add(board[nextX][nextY]);
                        }
                    }
                }
            }
            dist++;
        }

        return -1;
    }

    private int[] getPos(int pos, int n) {
        pos--;

        int row = pos / n;
        int col = pos % n;

        if (row % 2 != 0) { // right to left
            col = n - 1 - col;
        }

        return new int[] {n - 1 - row, col};
    }
}
