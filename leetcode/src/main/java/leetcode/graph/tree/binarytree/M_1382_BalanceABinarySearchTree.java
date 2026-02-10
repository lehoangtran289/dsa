package leetcode.graph.tree.binarytree;

import java.util.ArrayList;
import java.util.List;

public class M_1382_BalanceABinarySearchTree {

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        inorder(root, inorderList);

        return createBalanceBST(inorderList, 0, inorderList.size() - 1);
    }

    private void inorder(TreeNode root, List<Integer> inorderList) {
        if (root == null) return;
        inorder(root.left, inorderList);
        inorderList.add(root.val);
        inorder(root.right, inorderList);
    }

    private TreeNode createBalanceBST(List<Integer> inorderList, int start, int end) {
        if (start > end) return null;

        int mid = end - (end - start) / 2;

        TreeNode left = createBalanceBST(inorderList, start, mid - 1);
        TreeNode right = createBalanceBST(inorderList, mid + 1, end);

        return new TreeNode(inorderList.get(mid), left, right);
    }
}
