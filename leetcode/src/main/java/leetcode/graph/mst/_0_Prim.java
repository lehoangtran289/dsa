package leetcode.graph.mst;

import leetcode.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class _0_Prim {

    public static void main(String[] args) {
        int vertices = 9;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 7, 8));
        edges.add(new Edge(1, 7, 11));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(7, 6, 1));
        edges.add(new Edge(2, 8, 2));
        edges.add(new Edge(7, 8, 7));
        edges.add(new Edge(8, 6, 6));
        edges.add(new Edge(6, 5, 2));
        edges.add(new Edge(2, 5, 4));
        edges.add(new Edge(2, 3, 7));
        edges.add(new Edge(3, 5, 14));
        edges.add(new Edge(3, 4, 9));
        edges.add(new Edge(5, 4, 10));

        Pair<List<Edge>, Integer> result = prim(vertices, edges, 0);
        System.out.println(result);
    }

    /**
     * Prim's Algorithm to find Minimum Spanning Tree (MST) of a connected, undirected graph
     * represented as an edge list.
     * -----------------
     * TC: O(E log V)
     * SC: O(V + E)
     */
    public static Pair<List<Edge>, Integer> prim(
            int n,
            List<Edge> edges,
            int start
    ) {
        List<Edge> res = new ArrayList<>();
        int totalWeight = 0;

        // build adjacent list of undirected graph
        List<Edge>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }
        for (Edge edge : edges) {
            adj[edge.src].add(edge);
            adj[edge.dest].add(new Edge(edge.dest, edge.src, edge.weight));
        }

        // init parameters and set start node
        Queue<Edge> minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        boolean[] visited = new boolean[n];

        visited[start] = true;
        minHeap.addAll(adj[start]);

        // process edges in minHeap
        while (!minHeap.isEmpty()) {
            Edge cur = minHeap.poll();

            // if destination node is not visited, add the edge to result
            if (!visited[cur.dest]) {
                res.add(cur);
                visited[cur.dest] = true;
                totalWeight += cur.weight;

                // add all edges from the newly visited node
                for (Edge next : adj[cur.dest]) {
                    if (!visited[next.dest]) {
                        minHeap.offer(next);
                    }
                }
            }
        }

        return new Pair<>(res, totalWeight);
    }
}
