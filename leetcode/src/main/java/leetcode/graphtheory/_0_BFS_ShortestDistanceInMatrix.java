package leetcode.graphtheory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class _0_BFS_ShortestDistanceInMatrix {
    public static void main(String[] args) {
        System.out.println(shortestDistance(3, 4, new int[][]{{1, 0, 0, 0}, {1, 1, 0, 1}, {0, 1, 1, 1}}, 2, 3));
        System.out.println(Arrays.toString(shortestPath(new int[][]{{0, 1}, {0, 3}, {3, 4}, {4, 5}, {5, 6},
                {1, 2}, {2, 6}, {6, 7}, {7, 8}, {6, 8}}, 9, 10, 0)));
    }

    static int shortestDistance(int N, int M, int A[][], int X, int Y) {
        if (A[0][0] == 0 || A[X][Y] == 0) return -1;

        // init distance matrix
        Integer[][] d = new Integer[N][M];
        for (Integer[] row : d) Arrays.fill(row, 0);

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[N][M];
        Deque<int[]> queue = new ArrayDeque<>();

        // start from (0, 0)
        visited[0][0] = true;
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == X && cur[1] == Y) // reach dest
                return d[cur[0]][cur[1]];

            for (int[] direction : directions) {
                int r = cur[0] + direction[0];
                int c = cur[1] + direction[1];

                if (Math.min(r, c) < 0 || r >= N || c >= M || A[r][c] != 1 || visited[r][c])
                    continue;

                d[r][c] = d[cur[0]][cur[1]] + 1;
                visited[r][c] = true;
                queue.add(new int[]{r, c});
            }
        }
        return -1;
    }

    public static int[] shortestPath(int[][] edges, int n, int m, int src) {
        // construct adj list from edges
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        // init distance arr
        int[] d = new int[n];
        Arrays.fill(d, -1);

        boolean[] visited = new boolean[n];
        Deque<Integer> queue = new ArrayDeque<>();

        d[src] = 0;
        visited[src] = true;
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj[u]) {
                if (!visited[v]) {
                    d[v] = d[u] + 1;
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return d;
    }

    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        if (V == 0) return result;

        boolean[] visited = new boolean[V];
        Deque<Integer> queue = new ArrayDeque<>();

        visited[0] = true;
        queue.add(0);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            result.add(u);
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return result;
    }
}
