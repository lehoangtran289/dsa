package leetcode.tree.binarytree;

import java.util.Stack;

public class E_100_SameTree {
    public boolean isSameTreeRecursion(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val != q.val) return false;

        return isSameTreeRecursion(p.left, q.left) &&
               isSameTreeRecursion(p.right, q.right);
    }

    public boolean isSameTreeIteration(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(p);
        stack.add(q);

        while (!stack.isEmpty()) {
            TreeNode a = stack.pop();
            TreeNode b = stack.pop();

            if (a == null && b == null) continue;
            if (a == null || b == null) return false;
            if (a.val != b.val) return false;

            stack.add(a.left);
            stack.add(b.left);
            stack.add(a.right);
            stack.add(b.right);
        }

        return true;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

