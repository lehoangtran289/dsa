package leetcode.tree.binarytree;

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

    static class Pair<K, V> {
        K key;
        V value;

        public Pair(K node, V pos) {
            this.key = node;
            this.value = pos;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        int res = 0;

        // The queue of elements [(node, col_index)]
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> first = null;
            Pair<TreeNode, Integer> last = null;

            // Iterate through the current level
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                Pair<TreeNode, Integer> cur = queue.poll();
                if (first == null) first = cur;
                last = cur;

                TreeNode node = cur.getKey();
                if (node.left != null)
                    queue.add(new Pair<>(node.left, 2 * cur.getValue()));
                if (node.right != null)
                    queue.add(new Pair<>(node.right, 2 * cur.getValue() + 1));
            }

            res = Math.max(res, last.getValue() - first.getValue() + 1);
        }

        return res;
    }
}
