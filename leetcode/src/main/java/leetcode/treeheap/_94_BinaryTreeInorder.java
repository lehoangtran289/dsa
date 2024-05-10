package leetcode.treeheap;

import java.util.ArrayList;
import java.util.List;

public class _94_BinaryTreeInorder {
    public static void main(String[] args) {
        System.out.println(inorderTraversal(new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null))));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        visit(root, res);
        return res;
    }

    private static void visit(TreeNode root, List<Integer> res) {
        if (root == null) return;
        visit(root.left, res);
        res.add(root.val);
        visit(root.right, res);
    }
}
