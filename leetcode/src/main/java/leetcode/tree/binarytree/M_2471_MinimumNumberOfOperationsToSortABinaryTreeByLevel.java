package leetcode.tree.binarytree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class M_2471_MinimumNumberOfOperationsToSortABinaryTreeByLevel {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        System.out.println(minimumOperations(root));
    }

    public static int minimumOperations(TreeNode root) {
        int res = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] levelVals = new int[size];

            for (int i = 0; i < levelVals.length; ++i) {
                TreeNode cur = queue.poll();
                levelVals[i] = cur.val;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }

            res += getStepsToSort(levelVals);
        }

        return res;
    }

    private static int getStepsToSort(int[] levelVals) {
        int[] target = levelVals.clone();
        Arrays.sort(target);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; ++i) map.put(levelVals[i], i);

        int res = 0;
        for (int i = 0; i < target.length; ++i) {
            if (target[i] != levelVals[i]) {
                res++;

                int idx = map.get(target[i]);
                int temp = levelVals[idx];
                levelVals[idx] = levelVals[i];
                levelVals[i] = temp;
                map.put(levelVals[idx], idx);
                map.put(levelVals[i], i);
            }
        }
        return res;
    }
}
