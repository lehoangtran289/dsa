package leetcode.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 * You are also given queries[j] = [Cj, Dj] represents the j-th query -> Cj / Dj = ?.
 */
public class M_399_EvaluationDivision {

    /**
     * BFS approach
     * --------------------
     * TC: O(M * N) where N is the number of equations and M is the number of queries.
     * SC: O(N) for the graph representation.
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // build graph
        Map<String, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); ++i) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            double weight = values[i];

            if (!graph.containsKey(from)) graph.put(from, new ArrayList<>());
            if (!graph.containsKey(to)) graph.put(to, new ArrayList<>());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, 1 / weight));
        }

        // BFS on queries
        double[] res = new double[queries.size()];

        for (int i = 0; i < queries.size(); ++i) {
            List<String> query = queries.get(i);
            String from = query.get(0);
            String to = query.get(1);

            if (!graph.containsKey(from) || !graph.containsKey(to)) {
                res[i] = -1.0;
                continue;
            }

            if (from.equals(to)) {
                res[i] = 1.0;
                continue;
            }

            res[i] = bfs(graph, from, to);
        }

        return res;
    }

    private double bfs(
            Map<String, List<Edge>> graph,
            String from,
            String to
    ) {
        Set<String> visited = new HashSet<>();
        Queue<Edge> queue = new ArrayDeque<>();

        visited.add(from);
        queue.add(new Edge(from, 1.0));

        while (!queue.isEmpty()) {
            var cur = queue.poll();
            String node = cur.dest;
            Double curVal = cur.weight;

            if (node.equals(to)) {
                return curVal;
            }

            for (var entry : graph.get(node)) {
                String next = entry.dest;
                Double nextVal = entry.weight;

                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(new Edge(next, curVal * nextVal));
                }
            }
        }
        return -1.0;
    }

    static class Edge {
        String dest;
        Double weight;

        Edge(String dest, Double weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}
