package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class M_2492_MinimumScoreOfAPathBetweenTwoCities {

    public int minScore(int n, int[][] roads) {
        List<int[]>[] adjList = new List[n + 1];

        for (int i = 0; i <= n; ++i) adjList[i] = new ArrayList<>();

        for (int[] road : roads) {
            adjList[road[0]].add(new int[]{road[1], road[2]});
            adjList[road[1]].add(new int[]{road[0], road[2]});
        }

        int res = 1 << 30;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        visited[1] = true;
        queue.add(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int[] neighbor : adjList[cur]) {
                res = Math.min(res, neighbor[1]);

                if (!visited[neighbor[0]]) {
                    visited[neighbor[0]] = true;
                    queue.add(neighbor[0]);
                }
            }
        }

        return res;
    }

    /**
     * Dijkstra
     * ---
     * TC: O((n + m) log n) ~ where n = number of cities, m = number of roads
     * SC: O(n + m)
     */
    public int minScore2(int n, int[][] roads) {
        List<int[]>[] adjList = new List[n + 1];

        for (int i = 0; i <= n; ++i) adjList[i] = new ArrayList<>();

        for (int[] road : roads) {
            adjList[road[0]].add(new int[]{road[1], road[2]});
            adjList[road[1]].add(new int[]{road[0], road[2]});
        }

        int[] dist = new int[n + 1];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        Arrays.fill(dist, 1 << 30);
        minHeap.add(new int[]{1, 1 << 30});

        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            for (int[] neighbor : adjList[curNode]) {
                int nextDist = Math.min(curDist, neighbor[1]);

                if (nextDist < dist[neighbor[0]]) {
                    dist[neighbor[0]] = nextDist;
                    minHeap.add(new int[]{neighbor[0], nextDist});
                }
            }
        }

        return dist[n];
    }
}
