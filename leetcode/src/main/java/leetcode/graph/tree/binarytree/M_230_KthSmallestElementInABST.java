package leetcode.graph.tree.binarytree;

public class M_230_KthSmallestElementInABST {

    private int count;
    private int k;
    private int res;

    /**
     * Idea: Inorder traversal since BST -> k-th smallest = k-th visited node
     * Maintain count, check if we visit a node at k iteration -> that is our result
     * ---
     */
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        count++;
        if (count == k) {
            res = root.val;
            return;
        }

        inorder(root.right);
    }
}
