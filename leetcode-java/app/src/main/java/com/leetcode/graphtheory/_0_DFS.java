package com.leetcode.graphtheory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class _0_DFS {
    static class Graph {
        private final int V; // number of vertices
        private final ArrayList<Integer>[] adj; // adjacency list

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

        // recursive DFS
        private void dfs(int s, boolean[] visited) {
            visited[s] = true;
            System.out.print(s + " ");
            for (int v : adj[s]) {
                if (!visited[v]) {
                    dfs(v, visited);
                }
            }
        }

        public void dfs(int s) {
            boolean[] visited = new boolean[V];
            dfs(s, visited);
        }

        // stack DFS
        public void dfs_stack(int s) {
            Deque<Integer> stack = new ArrayDeque<>();
            boolean[] visited = new boolean[V];

            stack.push(s);
            while (!stack.isEmpty()) {
                int u = stack.pop();
                if (!visited[u]) {
                    visited[u] = true;          // only mark visited when pop from stack
                    System.out.print(u + " ");  // process u
                    for (int v : adj[u]) {
                        if (!visited[v]) {
                            stack.push(v);
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        int V = 13;
        Graph g = new Graph(V);
        g.addEdge(1, 3);
        g.addEdge(1, 5);
        g.addEdge(1, 6);
        g.addEdge(1, 9);
        g.addEdge(2, 6);
        g.addEdge(2, 4);
        g.addEdge(2, 8);
        g.addEdge(3, 1);
        g.addEdge(3, 5);
        g.addEdge(3, 6);
        g.addEdge(3, 7);
        g.addEdge(4, 10);
        g.addEdge(4, 2);
        g.addEdge(5, 1);
        g.addEdge(5, 3);
        g.addEdge(5, 7);
        g.addEdge(6, 3);
        g.addEdge(6, 1);
        g.addEdge(6, 2);
        g.addEdge(7, 3);
        g.addEdge(7, 5);
        g.addEdge(8, 2);
        g.addEdge(8, 3);
        g.addEdge(9, 1);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(10, 4);
        g.addEdge(11, 9);
        g.addEdge(11, 12);
        g.addEdge(12, 11);
        g.addEdge(12, 9);

        System.out.print("Start recursive DFS from 1: ");
        g.dfs(1);
        System.out.print("\nStart stack DFS from 1: ");
        g.dfs_stack(1);
        System.out.println();
    }
}
