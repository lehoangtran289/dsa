package leetcode.graph.tree.binarytree;

import java.util.Stack;

public class M_98_ValidateBinarySearchTree {

    /**
     * Validates if a binary tree is a valid binary search tree (BST).
     * Idea: maintain a range for each node
     * -----
     * TC: O(n)
     * SC: O(n)
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) return true;

        if (root.val <= min) return false;
        if (root.val >= max) return false;

        return isValidBST(root.right, root.val, max) && // right > root
               isValidBST(root.left, min, root.val); // left < root
    }

    /**
     * Validates if a binary tree is a valid binary search tree (BST) using an iterative approach.
     * Idea: use a stack to maintain the range for each node
     * -----
     * TC: O(n)
     * SC: O(n)
     */
    public boolean isValidBST2(TreeNode root) {
        Stack<Node> stack = new Stack<>();
        stack.add(new Node(root, null, null));

        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            if (cur.root == null) continue;
            if (cur.min != null && cur.root.val <= cur.min) return false;
            if (cur.max != null && cur.root.val >= cur.max) return false;

            stack.add(new Node(cur.root.left, cur.min, cur.root.val));
            stack.add(new Node(cur.root.right, cur.root.val, cur.max));
        }

        return true;
    }

    static class Node {
        TreeNode root;
        Integer min;
        Integer max;

        Node(TreeNode root, Integer min, Integer max) {
            this.root = root;
            this.min = min;
            this.max = max;
        }
    }
}
