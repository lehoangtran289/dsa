package leetcode.graph.tree.binarytree;

public class E_104_MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5,
                                     new TreeNode(4,
                                                  new TreeNode(11,
                                                               new TreeNode(7),
                                                               new TreeNode(2)),
                                                  null),
                                     new TreeNode(8,
                                                  new TreeNode(13),
                                                  new TreeNode(4,
                                                               null, new TreeNode(1)))
        );
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        return getMaxDepth(root, 0);
    }

    private static int getMaxDepth(TreeNode root, int depth) {
        if (root == null) return depth;
        return Math.max(getMaxDepth(root.left, depth + 1), getMaxDepth(root.right, depth + 1));
    }
}
