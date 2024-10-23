package leetcode.tree.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

public class _M_2641_CousinsInBinaryTreeII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(4, new TreeNode(1), new TreeNode(10)), new TreeNode(9, null, new TreeNode(7)));
        System.out.println(new _M_2641_CousinsInBinaryTreeII().replaceValueInTree(root));
    }

    // 2 pass BFS
    public TreeNode replaceValueInTree(TreeNode root) {
        // pass 1: build level sum arr
        int[] sum = new int[10];
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int curLevel = 0;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; ++i) {
                TreeNode cur = queue.remove();
                sum[curLevel] += cur.val;

                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            curLevel++;
        }

        // pass 2: update root node
        queue = new ArrayDeque<>();
        queue.add(root);
        root.val = 0;

        curLevel = 0;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; ++i) {
                TreeNode cur = queue.remove();
                int childSum = (cur.left != null ? cur.left.val : 0) + (cur.right != null ? cur.right.val : 0);
                if (cur.left != null) {
                    cur.left.val = sum[curLevel + 1] - childSum;
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    cur.right.val = sum[curLevel + 1] - childSum;
                    queue.add(cur.right);
                }
            }
            curLevel++;
        }

        return root;
    }
}
