package com.leetcode.graphtheory;

import java.util.*;

public class _1514_PathWithMaximumProbability {
    public static void main(String[] args) {
        System.out.println(maxProbability(3,
                new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}
                , 0, 2));
    }

    static class Node {
        int v;
        double w;

        public Node(int v, double w) {
            this.v = v;
            this.w = w;
        }
    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<Node>[] graph = new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; ++i) {
            int[] e = edges[i];
            int u = e[0];
            int v = e[1];
            double w = succProb[i];
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        return dijkstra(graph, start_node, end_node);
    }

    public static double dijkstra(List<Node>[] graph, int src, int dst) {
        int n = graph.length;
        boolean[] seen = new boolean[n];

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o2.w, o1.w));
        pq.offer(new Node(src, 1.0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.v;
            if (u == dst) {
                return node.w;
            }
            if (seen[u]) {
                continue;
            }
            seen[u] = true;
            for (Node edge : graph[u]) {
                int v = edge.v;
                double vW = edge.w;
                if (seen[v]) {
                    continue;
                }
                pq.offer(new Node(v, node.w * vW));
            }
        }
        return 0;
    }
}
