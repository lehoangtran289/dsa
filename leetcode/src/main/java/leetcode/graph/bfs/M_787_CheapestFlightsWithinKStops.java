package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class M_787_CheapestFlightsWithinKStops {
    static void main() {
        System.out.println(
                findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1)
        ); // Output: 200
    }

    /**
     * Idea: BFS limit K steps
     * -----------------------
     * Time: O(E + V) where E is the number of edges and V is the number of vertices
     * Space: O(V) for the adjacency list and distance array
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // init graph
        List<int[]>[] adjList = new List[n]; // [src -> [dst, dist]]
        for (int i = 0; i < n; ++i) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            adjList[flight[0]].add(new int[]{flight[1], flight[2]});
        }

        // init bfs states
        int[] dist = new int[n]; // min dist of src to dst within k stops
        Queue<int[]> queue = new ArrayDeque<>(); // cur stop, curDist
        int steps = 0;

        Arrays.fill(dist, 1 << 30);
        dist[src] = 0;
        queue.add(new int[]{src, 0});

        while (steps++ <= k && !queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; ++i) {
                int[] cur = queue.poll();
                int curSrc = cur[0];
                int curDist = cur[1];

                for (int[] neighbor : adjList[curSrc]) {
                    int nextDst = neighbor[0];
                    int nextDist = neighbor[1] + curDist;

                    if (nextDist < dist[nextDst]) {
                        dist[nextDst] = nextDist;
                        queue.add(new int[]{nextDst, nextDist});
                    }
                }
            }
        }

        return dist[dst] == 1 << 30 ? -1 : dist[dst];
    }
}
