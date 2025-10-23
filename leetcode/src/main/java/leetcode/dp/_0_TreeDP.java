package leetcode.dp;

import leetcode.graph.tree.binarytree.TreeNode;
import leetcode.utils.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Top down DP
 * ---------------------------
 * Idea: DP tree
 * General problem: find max sum of non-adjacent nodes in a tree
 * ---------------------------
 * int f[MAX][2];
 * <p>
 * Void dfs(int u, int par) {
 * f[u][0] = 0; // not choose u
 * f[u][1] = val[u]; // choose u
 * <p>
 * For (int v : adj[u]) {
 * if (v != par) {
 * dfs(v, u);
 * f[u][0] += Math.max(f[v][0], f[v][1]);
 * f[u][1] += f[v][0];
 * }
 * }
 * }
 */
public class _0_TreeDP {
    /**
     * DP on tree, top down approach
     * Idea:
     * - traverse from root, each node has 2 options: take or skip
     * - If take a node, then we cannot take its children
     * - Relation: dp[u][1] = dp[v1][0] + ... + dp[vk][0]
     * dp[u][0] = max(dp[v1][0], dp[v1][1]) + ... + max(dp[v1][0], dp[vk][1])
     */
    private Map<Pair<TreeNode, Integer>, Integer> memo;

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
