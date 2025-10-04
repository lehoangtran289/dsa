package leetcode.graph.tree.binarytree;

import java.util.ArrayList;
import java.util.List;

public class E_145_BinaryTreePostorder {
    public static void main(String[] args) {
        System.out.println(preorderTraversal(new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null))));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        visit(root, res);
        return res;
    }

    private static void visit(TreeNode root, List<Integer> res) {
        if (root == null) return;
        visit(root.left, res);
        visit(root.right, res);
        res.add(root.val);
    }
}


