package leetcode.graph.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class E_101_SymmetricTree {

    /**
     * Recursion approach
     * Idea: check mirror = (root1.left == root2.right) && (root1.right == root2.left)
     * ----
     * TC: O(n)
     * SC: O(h) where h is the height of the tree
     */
    public boolean isSymmetricRecursive(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) return true;
        if (r1 == null || r2 == null) return false;
        if (r1.val != r2.val) return false;

        return isSymmetric(r1.left, r2.right) && isSymmetric(r1.right, r2.left);
    }

    /**
     * BFS approach
     * Idea: check mirror = (root1.left == root2.right) && (root1.right == root2.left)
     * Note:
     *  + ArrayDeque not allow null
     *  + LinkedList allow null
     */
    public boolean isSymmetricBFS(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode r1 = queue.poll();
            TreeNode r2 = queue.poll();

            if (r1 == null && r2 == null) continue;
            if (r1 == null || r2 == null) return false;
            if (r1.val != r2.val) return false;

            queue.add(r1.left);
            queue.add(r2.right);
            queue.add(r1.right);
            queue.add(r2.left);
        }

        return true;
    }
}
