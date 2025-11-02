package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class M_787_CheapestFlightsWithinKStops {

    static class Flight {
        int to;
        int price;

        Flight(int to, int price) {
            this.to = to;
            this.price = price;
        }
    }

    /**
     * Idea: BFS
     * -----------------------
     * Time: O(E + V) where E is the number of edges and V is the number of vertices
     * Space: O(V) for the adjacency list and distance array
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // init graph
        List<Flight>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (int[] f : flights) {
            adj[f[0]].add(new Flight(f[1], f[2]));
        }

        // init bfs variables
        int stops = 0;
        int[] dist = new int[n];
        Queue<Flight> queue = new ArrayDeque<>();

        Arrays.fill(dist, Integer.MAX_VALUE);
        queue.add(new Flight(src, 0)); // src, total_price

        while (stops++ <= k && !queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                Flight cur = queue.poll();

                for (Flight next : adj[cur.to]) {
                    int newPrice = cur.price + next.price;

                    if (newPrice <= dist[next.to]) {
                        dist[next.to] = newPrice;
                        queue.add(new Flight(next.to, newPrice));
                    }
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
