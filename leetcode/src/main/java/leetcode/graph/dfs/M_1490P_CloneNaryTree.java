package leetcode.graph.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_1490P_CloneNaryTree {

    /**
     * Recursive DFS
     */
    public Node cloneTree(Node root) {
        return dfs(root);
    }

    private Node dfs(Node root) {
        if (root == null) return null;

        Node newRoot = new Node(root.val);
        List<Node> newChildren = new ArrayList<>();

        for (Node child : root.children) {
            newChildren.add(dfs(child));
        }
        newRoot.children = newChildren;
        return newRoot;
    }

    /**
     * BFS Iterative
     */
    public Node cloneTree2(Node root) {
        if (root == null) return null;

        Node newRoot = new Node(root.val);

        Queue<Node[]> queue = new ArrayDeque<>();
        queue.add(new Node[]{root, newRoot});

        while (!queue.isEmpty()) {
            Node[] nodePair = queue.poll();
            Node oldNode = nodePair[0];
            Node newNode = nodePair[1];

            List<Node> newChildren = new ArrayList<>();

            for (Node child : oldNode.children) {
                // Make a copy for each child node.
                Node newChild = new Node(child.val);
                newChildren.add(newChild);

                // Visit to copy the child nodes of each child node.
                queue.add(new Node[]{child, newChild});
            }
            newNode.children = newChildren;
        }
        return newRoot;
    }

    // -------- DEFINITION

    static class Node {
        public int val;
        public List<Node> children;

        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
}


