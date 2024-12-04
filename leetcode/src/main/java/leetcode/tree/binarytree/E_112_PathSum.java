package leetcode.tree.binarytree;

public class E_112_PathSum {
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
        System.out.println(hasPathSum(root, 22));
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }

    public static boolean dfs(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            if (sum == root.val) return true;
        }

        return dfs(root.left, sum - root.val) || dfs(root.right, sum - root.val);
    }
}
