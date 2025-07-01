package leetcode.tree.binarytree;

import leetcode.utils.Pair;

import java.util.LinkedList;
import java.util.Queue;

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

    public static boolean dfs(TreeNode root, int target) {
        if (root == null) return false;

        target -= root.val;
        if (root.left == null && root.right == null) return target == 0;

        return dfs(root.left, target) || dfs(root.right, target);
    }

    public boolean hasPathSumBFS(TreeNode root, int target) {
        if (root == null) return false;

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, root.val));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> cur = queue.poll();
            TreeNode node = cur.getKey();
            int sum = cur.getValue();

            if (node.left == null && node.right == null && sum == target) return true;
            if (node.left != null) queue.add(new Pair<>(node.left, sum + node.left.val));
            if (node.right != null) queue.add(new Pair<>(node.right, sum + node.right.val));
        }

        return false;
    }
}
