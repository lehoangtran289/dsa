package leetcode.graph.tree.binarytree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 0. Max Sum of Non-Adjacent Nodes in a Binary Tree
 *
 * Given a tree, find the maximum sum of values of nodes such that no two adjacent nodes are included in the sum.
 *
 * Example:
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *
 * The maximum sum is 15 (1 + 4 + 5 + 6).
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -10^4 <= Node.val <= 10^4
 */
public class _0_MaxSumNonAdjacentNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println(new _0_MaxSumNonAdjacentNodes().getMaxSum(root)); // 15
    }

    /**
     * Top down DP
     * ---------------------------
     * Idea: DP tree
     * General problem: find max sum of non-adjacent nodes in a tree
     * ---------------------------
     * int f[MAX][2];
     *
     * Void dfs(int u, int par) {
     *     f[u][0] = 0; // not choose u
     *     f[u][1] = val[u]; // choose u
     *
     *     For (int v : adj[u]) {
     *         if (v != par) {
     *              dfs(v, u);
     *              f[u][0] += Math.max(f[v][0], f[v][1]);
     *              f[u][1] += f[v][0];
     *         }
     *     }
     * }
     */
    private Map<TreeNode, int[]> memo;

    public int getMaxSum(TreeNode root) {
        this.memo = new HashMap<>();
        return Math.max(dp(root, false), dp(root, true));
    }

    private int dp(TreeNode root, boolean choose) {
        if (root == null) return 0;

        if (memo.containsKey(root) && memo.get(root)[choose ? 1 : 0] != -1) {
            return memo.get(root)[choose ? 1 : 0];
        }

        if (root.left == null && root.right == null) {
            int value = choose ? root.val : 0;
            updateMap(root, choose, value);
            return value;
        }

        int maxSum;
        if (choose) {
            maxSum = root.val
                     + dp(root.left, false)
                     + dp(root.right, false);
        } else {
            maxSum = Math.max(dp(root.left, false), dp(root.left, true))
                     + Math.max(dp(root.right, false), dp(root.right, true));
        }

        updateMap(root, choose, maxSum);
        return maxSum;
    }

    private void updateMap(TreeNode root, boolean choose, int sum) {
        if (!memo.containsKey(root)) {
            memo.put(root, new int[2]);
            Arrays.fill(memo.get(root), -1);
        }
        if (choose) memo.get(root)[1] = sum;
        else memo.get(root)[0] = sum;
    }
}

