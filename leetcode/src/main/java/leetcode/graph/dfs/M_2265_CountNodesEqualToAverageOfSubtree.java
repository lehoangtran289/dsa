package leetcode.graph.dfs;

import leetcode.graph.tree.binarytree.TreeNode;

public class M_2265_CountNodesEqualToAverageOfSubtree {

    private int res;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return res;
    }

    private int[] dfs(TreeNode root) {
        // base case
        if (root == null) return new int[]{0, 0};
        if (root.left == null && root.right == null) {
            res++;
            return new int[]{root.val, 1};
        }

        // traverse tree
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int subtreeSum = left[0] + right[0] + root.val;
        int subtreeCount = left[1] + right[1] + 1;

        if (root.val == subtreeSum / subtreeCount) res++;

        return new int[]{subtreeSum, subtreeCount};
    }
}
