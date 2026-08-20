package leetcode.graph.tree.binarytree;

public class M_236_LowestCommonAncestorOfABinaryTree {

    /**
     * LCA of Binary Tree
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right subtrees return non-null values,
        // it means p and q are found in different subtrees,
        // so the current root is the LCA
        if (left != null && right != null) {
            return root;
        }

        // If only one subtree returns a non-null value
        // If both are null, return null
        return left != null ? left : right;
    }
}
