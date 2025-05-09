package leetcode.graph.dijkstra;

import java.util.PriorityQueue;

public class H_2577_MinimumTimeToVisitACellInAGrid {
    public static void main(String[] args) {
        System.out.println(minimumTime(new int[][]{{0, 1, 3, 2}, {5, 1, 2, 5}, {4, 3, 8, 6}}));
    }

    /**
     * The challenge arises when we find ourselves stuck in a cell,
     * unable to move forward because all neighboring cells are inaccessible, with higher minimum times.
     * In such situations, we must "waste" time to move forward. How do we do that? By wandering around!
     * We can move back and forth between the current cell and
     * any previously accessible cells until a neighboring cell becomes accessible.
     */
    public static int minimumTime(int[][] grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
        pq.add(new Cell(0, 0, 0));
        boolean[][] visited = new boolean[rows][cols];

        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            if (cur.x == rows - 1 && cur.y == cols - 1) {
                return cur.w;
            }

            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;

            for (int[] dir : dirs) {
                int newX = cur.x + dir[0];
                int newY = cur.y + dir[1];

                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                    if (grid[newX][newY] <= cur.w + 1) {
                        pq.add(new Cell(newX, newY, cur.w + 1));
                    } else {
                        int wait = (grid[newX][newY] - cur.w) % 2 == 0 ? 1 : 0;
                        int nextW = grid[newX][newY] + wait;
                        pq.add(new Cell(newX, newY, nextW));
                    }
                }
            }
        }

        return -1;
    }

    static class Cell {
        int x;
        int y;
        int w;

        public Cell(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}
