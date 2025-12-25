package leetcode.graph.tree.binarytree;

public class M_549_BinaryTreeLongestConsecutiveSequenceII {

    /**
     * DFS
     * Idea: for each node, return two values:
     * the length of the longest increasing path and the length of the longest decreasing path
     * result = max(result, incr + decr - 1) // -1 to avoid double count the current node
     * ----------------------------------
     * TC: O(n)
     * SC: O(h)
     * ----------------------------------
     */
    private int result;

    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return result;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int incr = 1, decr = 1;

        if (root.left != null) {
            int[] pair = dfs(root.left);
            if (root.left.val + 1 == root.val) {
                decr = pair[1] + 1;
            } else if (root.left.val - 1 == root.val) {
                incr = pair[0] + 1;
            }
        }

        if (root.right != null) {
            int[] pair = dfs(root.right);
            if (root.right.val + 1 == root.val) {
                decr = Math.max(decr, pair[1] + 1);
            } else if (root.right.val - 1 == root.val) {
                incr = Math.max(incr, pair[0] + 1);
            }
        }

        result = Math.max(result, incr + decr - 1);
        return new int[]{incr, decr};
    }
}
