package leetcode.tree.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_102_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrderRecursion(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        traverse(res, root, 0);
        return res;
    }

    private void traverse(
            List<List<Integer>> res,
            TreeNode cur,
            int level
    ) {
        // start current level
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }

        res.get(level).add(cur.val);

        if (cur.left != null) traverse(res, cur.left, level + 1);
        if (cur.right != null) traverse(res, cur.right, level + 1);
    }

    // ---------------------------------------------------------------------------------------

    public List<List<Integer>> levelOrderQueue(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) return res;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            List<Integer> levelValues = new ArrayList<>();
            for (int i = 0; i < levelSize; ++i) {
                TreeNode cur = queue.poll();
                levelValues.add(cur.val);

                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }

            res.add(levelValues);
        }

        return res;
    }
}
