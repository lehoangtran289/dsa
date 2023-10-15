package com.leetcode.graphtheory;

import java.util.*;

public class _0_BFS {
    static class Graph {
        private int V; // number of vertices
        private ArrayList<Integer>[] adj; // adjacency list

        public Graph(int V) {
            this.V = V;
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        public void addEdge(int u, int v) {
            adj[u].add(v);
        }

        // -----------------------------------------------------------------

        // BFS all paths from source s to reachable vertices
        public void bfs(int s) {
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();

            visited.add(s);
            queue.add(s);
            while (!queue.isEmpty()) {
                int u = queue.remove();
                // process u
                System.out.print(u + " ");

                for (int v : adj[u]) {
                    if (!visited.contains(v)) {
                        visited.add(v);
                        queue.add(v);
                    }
                }
            }
        }

        // BFS shortest path from source to destination
        public void bfs(int src, int dest) {
            if (src == dest) return;

            // track the path
            Integer[] d = new Integer[V]; // distance from src
            Integer[] prev = new Integer[V]; // track path from src to all reachable vertices
            Arrays.fill(prev, -1);
            Arrays.fill(d, 0);

            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            visited.add(src);
            queue.add(src);
            while (!queue.isEmpty()) {
                int u = queue.remove();
                if (u == dest) break;

                for (int v : adj[u]) {
                    if (!visited.contains(v)) {
                        d[v] = d[u] + 1;
                        prev[v] = u;
                        visited.add(v);
                        queue.add(v);
                    }
                }
            }

            // print the path
            if (!visited.contains(dest)) {
                System.out.println("No path found");
                return;
            }
            ArrayList<Integer> path = new ArrayList<>();
            int cur = dest;
            while (cur != -1) {
                path.add(cur);
                cur = prev[cur]; // backtrack
            }
            Collections.reverse(path);
            System.out.println("Path from " + src + " to " + dest + ": " + path);
            System.out.println("Distance: " + d[dest]);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(11);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 0);
        g.addEdge(1, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(2, 3);
        g.addEdge(2, 7);
        g.addEdge(3, 2);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(3, 6);
        g.addEdge(4, 0);
        g.addEdge(4, 3);
        g.addEdge(5, 3);
        g.addEdge(5, 8);
        g.addEdge(6, 3);
        g.addEdge(6, 7);
        g.addEdge(7, 2);
        g.addEdge(7, 6);
        g.addEdge(7, 8);
        g.addEdge(7, 9);
        g.addEdge(8, 5);
        g.addEdge(8, 7);
        g.addEdge(8, 10);
        g.addEdge(9, 7);
        g.addEdge(10, 8);

        System.out.print("Start BFS from 0: ");
        g.bfs(0);
        System.out.println();

        g.bfs(0, 10);
    }
}
