package leetcode.array.prefixSum;

import leetcode.graph.tree.binarytree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Find a number of continuous subarrays/submatrices/tree paths that sum to target
 * => use prefix sum
 */
public class M_437_PathSumIII {
    private int targetSum;
    private int count = 0;
    private Map<Long, Integer> freq;

    public int pathSum(TreeNode root, int targetSum) {
        this.freq = new HashMap<>();
        this.targetSum = targetSum;

        dfs(root, 0L);
        return count;
    }

    private void dfs(TreeNode root, long curSum) {
        if (root == null) return;

        curSum += root.val;
        if (curSum == targetSum) count++;
        count += freq.getOrDefault(curSum - targetSum, 0);

        freq.put(curSum, freq.getOrDefault(curSum, 0) + 1);

        // traverse child nodes with new curSum
        dfs(root.left, curSum);
        dfs(root.right, curSum);

        // backtrack
        freq.put(curSum, freq.get(curSum) - 1);
    }
}
