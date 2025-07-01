package leetcode.tree.binarytree;

import java.util.ArrayList;
import java.util.List;

public class M_113_PathSumII {
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
        System.out.println(pathSum(root, 22));
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();

        dfs(root, targetSum, res, cur);
        return res;
    }

    private static void dfs(
            TreeNode root,
            int targetSum,
            List<List<Integer>> res,
            List<Integer> cur
    ) {
        if (root == null) return;

        // process cur node
        cur.add(root.val);
        targetSum -= root.val;

        // check leaf node
        if (root.left == null && root.right == null) {
            if (targetSum == 0) {
                res.add(new ArrayList<>(cur));
            }
        }

        // traverse left and right children
        dfs(root.left, targetSum, res, cur);
        dfs(root.right, targetSum, res, cur);

        // backtrack
        cur.remove(cur.size() - 1);
    }
}
