package leetcode.graph.tree.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_114_FlattenBinaryTreeToLinkedList {

    /**
     * Flatten Binary Tree to Linked List
     * <p>
     * Idea: Use pre-order traversal to collect nodes in a queue,
     * then reconstruct the tree by linking nodes in the order they were visited.
     * ------------------------
     * Time: O(N)
     * Space: O(N)
     */
    public void flatten(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        preOrder(root, queue);
        queue.poll();

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            root.right = cur;
            root.left = null;
            root = cur;
        }
    }

    private void preOrder(TreeNode root, Queue<TreeNode> queue) {
        if (root == null) return;

        // process root
        queue.add(root);
        preOrder(root.left, queue);
        preOrder(root.right, queue);
    }
}
