package leetcode.graph.tree.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_1214_TwoSumBSTs {

    /**
     * Inorder Traversal + Two Pointers
     * --------------------------
     * TC: O(m + n)
     * SC: O(m + n)
     * --------------------------
     */
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<TreeNode> sortedNodes1 = new ArrayList<>();
        List<TreeNode> sortedNodes2 = new ArrayList<>();
        inorder(root1, sortedNodes1);
        inorder(root2, sortedNodes2);

        int l = 0, r = sortedNodes2.size() - 1;

        while (l < sortedNodes1.size() && r >= 0) {
            int sum = sortedNodes1.get(l).val + sortedNodes2.get(r).val;

            if (sum == target) return true;
            else if (sum < target) l++;
            else r--;
        }

        return false;
    }

    private void inorder(TreeNode root, List<TreeNode> nodes) {
        if (root == null) return;

        inorder(root.left, nodes);
        nodes.add(root);
        inorder(root.right, nodes);
    }

    /**
     * BFS + BST Search
     * --------------------------
     * TC: O(m * log n)
     * SC: O(m)
     * --------------------------
     */
    public boolean twoSumBSTs1(TreeNode root1, TreeNode root2, int target) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root1);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            int complement = target - cur.val;
            if (find(root2, complement)) return true;

            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }

        return false;
    }

    private boolean find(TreeNode root, int target) {
        if (root == null) return false;

        if (root.val == target) return true;
        else if (root.val < target) return find(root.right, target);
        return find(root.left, target);
    }
}
