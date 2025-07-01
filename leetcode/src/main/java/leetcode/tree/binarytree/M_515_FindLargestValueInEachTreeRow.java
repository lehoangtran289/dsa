package leetcode.tree.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class M_515_FindLargestValueInEachTreeRow {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        System.out.println(new M_515_FindLargestValueInEachTreeRow().largestValues(root));
    }

    // level order traversal
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();

            int curMax = Integer.MIN_VALUE;
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                curMax = Math.max(curMax, cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(curMax);
        }

        return res;
    }
}
