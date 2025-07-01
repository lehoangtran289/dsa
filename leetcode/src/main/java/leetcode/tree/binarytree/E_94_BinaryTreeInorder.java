package leetcode.tree.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E_94_BinaryTreeInorder {
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

    // ---------------------------------------------------

    public static List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }

        return res;
    }
}
