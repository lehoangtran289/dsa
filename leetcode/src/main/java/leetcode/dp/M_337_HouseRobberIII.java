package leetcode.dp;

import leetcode.graph.tree.binarytree.TreeNode;
import leetcode.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class M_337_HouseRobberIII {

    /**
     * DP on tree, top down approach
     * Idea:
     * - traverse from root, each node has 2 options: take or skip
     * - If take a node, then we cannot take its children
     * - Relation: dp[u][1] = dp[v1][0] + ... + dp[vk][0]
     * dp[u][0] = max(dp[v1][0], dp[v1][1]) + ... + max(dp[v1][0], dp[vk][1])
     * -------------------------------
     * TC: O(n)
     * SC: O(n)
     */
    private Map<Pair<TreeNode, Integer>, Integer> memo; // <(node, choose), maxValue>

    public int rob(TreeNode root) {
        this.memo = new HashMap<>();
        int res = Math.max(dp(root, 0), dp(root, 1));
        return res;
    }

    private int dp(TreeNode root, int choose) {
        // base case
        if (root == null) return 0;
        if (memo.containsKey(new Pair<>(root, choose))) return memo.get(new Pair<>(root, choose));

        // dp relation
        int maxValue = 0;
        if (choose == 1) {
            maxValue = root.val + dp(root.left, 0) + dp(root.right, 0);
        } else {
            maxValue = Math.max(dp(root.left, 0), dp(root.left, 1))
                       + Math.max(dp(root.right, 0), dp(root.right, 1));
        }
        memo.put(new Pair<>(root, choose), maxValue);

        return maxValue;
    }
}
