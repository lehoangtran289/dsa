package leetcode.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class H_2503_MaximumNumberOFPointsFromGridQueries {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxPoints(new int[][]{{1, 2, 3}, {2, 5, 7}, {3, 5, 1}}, new int[]{5, 6, 2})));
    }

    static class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] maxPoints(int[][] grid, int[] queryOrigin) {
        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; // left, right, up, down

        // clone origin query to sort
        int[] queries = queryOrigin.clone();
        Arrays.sort(queries);

        // init vars for BFS
        int n = grid.length, m = grid[0].length, k = queries.length;
        Map<Integer, Integer> queryResult = new HashMap<>(); // <query, result>
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> grid[a.x][a.y] - grid[b.x][b.y]);

        // init state
        pq.add(new Cell(0, 0));
        int curCount = 0; // cur points count

        for (int query : queries) {
            // BFS
            while (!pq.isEmpty()) {
                // base case
                if (grid[pq.peek().x][pq.peek().y] >= query) break;

                Cell cur = pq.poll();
                visited[cur.x][cur.y] = true;
                curCount++;
                for (int[] dir : dirs) {
                    int nextX = cur.x + dir[0];
                    int nextY = cur.y + dir[1];
                    if (
                            isCellValid(nextX, nextY, n, m)
                            && !visited[nextX][nextY]
                    ) {
                        pq.add(new Cell(nextX, nextY));
                        visited[nextX][nextY] = true;
                    }
                }
            }

            queryResult.put(query, curCount);
        }

        // construct result
        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            res[i] = queryResult.get(queryOrigin[i]);
        }
        return res;
    }

    private static boolean isCellValid(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
