package leetcode.graphtheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class M_743_NetworkDelayTime {
    public static int dijkstra(List<int[]>[] graph, int s) {
        int n = graph.length;
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(Comparator.comparingInt(node -> dist[node[0]]));
        pq.offer(new Integer[]{s, 0});

        while (!pq.isEmpty()) {
            Integer[] node = pq.poll();
            int u = node[0];

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int vW = edge[1];
                int tempDist = dist[u] + vW;
                if (tempDist < dist[v]) {
                    dist[v] = tempDist;
                    pq.offer(new Integer[]{v, tempDist});
                }
            }
        }
        int maxDist = Arrays.stream(dist).max().getAsInt();
        return maxDist == Integer.MAX_VALUE ? -1 : maxDist;
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new List[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] time : times) {
            final int u = time[0] - 1;
            final int v = time[1] - 1;
            final int w = time[2];
            graph[u].add(new int[]{v, w});
        }

        return dijkstra(graph, k - 1);
    }
}
