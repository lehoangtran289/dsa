package leetcode.graph.tree.binarytree;

public class M_298_BinaryTreeLongestConsecutiveSequence {

    /**
     * DFS
     * ----------------------------------
     * TC: O(n)
     * SC: O(h)
     * ----------------------------------
     */
    private int res = 1; // need res if we find sub-path

    public int longestConsecutive(TreeNode root) {
        dfs(root.left, root, 1);
        dfs(root.right, root, 1);
        return res;
    }

    private void dfs(TreeNode root, TreeNode par, int length) {
        if (root == null) return;

        length = (par.val + 1 == root.val) ? length + 1 : 1;
        res = Math.max(res, length);

        dfs(root.left, root, length);
        dfs(root.right, root, length);
    }
}
