package leetcode.graph.tree.binarytree;

public class M_951_FlipEquivalentBinaryTrees {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(7), new TreeNode(8))), new TreeNode(3, new TreeNode(6), null));
        TreeNode root2 = new TreeNode(1, new TreeNode(3, null, new TreeNode(6)), new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(8), new TreeNode(7))));
        System.out.println(new M_951_FlipEquivalentBinaryTrees().flipEquiv(root, root2)); // true
        System.out.println(new M_951_FlipEquivalentBinaryTrees().flipEquiv(null, null));// true
        System.out.println(new M_951_FlipEquivalentBinaryTrees().flipEquiv(null, new TreeNode(1))); // false
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;

        boolean noSwap = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        boolean swap = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
        return noSwap || swap;
    }
}
