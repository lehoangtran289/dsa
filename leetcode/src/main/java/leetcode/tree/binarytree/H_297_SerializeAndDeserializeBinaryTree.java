package leetcode.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class H_297_SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder res = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                res.append("null,");
            } else {
                res.append(node.val).append(",");

                queue.add(node.left);
                queue.add(node.right);
            }
        }

        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;

        String[] arr = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // process left
            if (!arr[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(arr[i]));
                node.left = left;
                queue.add(left);
            }
            i++;

            // process right
            if (!arr[i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(arr[i]));
                node.right = right;
                queue.add(right);
            }
            i++;
        }

        return root;
    }
}
