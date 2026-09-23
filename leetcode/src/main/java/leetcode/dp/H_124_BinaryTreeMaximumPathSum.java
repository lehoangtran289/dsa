package leetcode.dp;

import leetcode.graph.tree.binarytree.TreeNode;

/**
 * Find max path sum of non-empty path in binary tree.
 * Note: a node can only appear in the sequence at most once
 */
public class H_124_BinaryTreeMaximumPathSum {

    private int res = -1 << 30;

    /**
     * Idea: Use max sum subarray problem (kadane algo)
     * ---
     * dp[i] = max path sum starting at i-th node
     * dp[i] = i.val + max(dp[i.left], 0) + max(dp[i.right], 0)
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int leftGain = Math.max(dfs(root.left), 0);
        int rightGain = Math.max(dfs(root.right), 0);

        res = Math.max(res, root.val + leftGain + rightGain);

        // since a node can appear in the sequence at most once
        return Math.max(root.val + leftGain, root.val + rightGain);
    }
}
