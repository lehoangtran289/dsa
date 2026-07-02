package leetcode.graph.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class M_133_CloneGraph {
    /**
     * recursive DFS
     * Idea: Use a map to keep track of visited nodes to avoid cycles.
     */
    private static final Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (visited.containsKey(node)) return visited.get(node);

        Node newNode = new Node(node.val);
        visited.put(node, newNode);

        for (Node n : node.neighbors) {
            newNode.neighbors.add(cloneGraph(n));
        }

        return newNode;
    }

    /**
     * Iterative BFS approach to clone a graph
     */
    public Node cloneGraphBFS(Node root) {
        if (root == null) return null;

        Map<Node, Node> visited = new HashMap<>(); // old node -> new node
        Queue<Node> queue = new ArrayDeque<>(); // BFS queue of old nodes

        queue.add(root);
        visited.put(root, new Node(root.val));

        while (!queue.isEmpty()) {
            Node oldNode = queue.poll();
            Node newNode = visited.get(oldNode);

            // traverse the neighbors of the old node
            for (Node neighbor : oldNode.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }

                // link the new neighbor to the new node
                Node newNeighbor = visited.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }
        return visited.get(root);
    }

    // -------------- DEFINITION ----------------
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
    }
}
