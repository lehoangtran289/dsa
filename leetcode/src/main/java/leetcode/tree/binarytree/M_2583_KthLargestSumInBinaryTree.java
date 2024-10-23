package leetcode.tree.binarytree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class M_2583_KthLargestSumInBinaryTree {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(8);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(6);

        System.out.println(new M_2583_KthLargestSumInBinaryTree().kthLargestLevelSum(root, 2));
    }

    // BFS + Max heap
    public long kthLargestLevelSum(TreeNode root, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        int curLevel = 0;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.remove();
                sum += cur.val;

                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            pq.add(sum);
            curLevel++;
        }

        if (curLevel < k) return -1;
        for (int i = 0; i < k - 1; i++) pq.remove();
        return pq.peek();
    }

}