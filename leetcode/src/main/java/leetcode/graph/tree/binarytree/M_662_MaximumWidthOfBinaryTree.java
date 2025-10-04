package leetcode.graph.tree.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

public class M_662_MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                                     new TreeNode(3,
                                                  new TreeNode(5,
                                                               new TreeNode(6),
                                                               null),
                                                  null),
                                     new TreeNode(2,
                                                  null,
                                                  new TreeNode(9,
                                                               new TreeNode(7),
                                                               null))
        );
        System.out.println(widthOfBinaryTree(root));
    }

    public static int widthOfBinaryTree(TreeNode root) {
        int res = 0;

        // The queue of elements [(node, col_index)]
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair first = null;
            Pair last = null;

            // Iterate through the current level
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Pair cur = queue.poll();
                if (first == null) first = cur;
                last = cur;

                TreeNode node = cur.node;
                if (node.left != null)
                    queue.add(new Pair(node.left, 2 * cur.idx));
                if (node.right != null)
                    queue.add(new Pair(node.right, 2 * cur.idx + 1));
            }

            res = Math.max(res, last.idx - first.idx + 1);
        }

        return res;
    }

    static class Pair {
        TreeNode node;
        int idx;

        public Pair(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }
}
