package leetcode.graph.tree.binarytree;

import java.util.HashMap;
import java.util.Map;

public class M_106_ConstructBinaryTreeFromPostorderAndInorder {

    private Map<Integer, Integer> inorderValueIndices;
    private int[] postorder;

    /**
     * Idea: see M_105_ConstructBinaryTreeFromPreorderAndInorder
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorderValueIndices = new HashMap<>();
        this.postorder = postorder;

        for (int i = 0; i < inorder.length; ++i) {
            inorderValueIndices.put(inorder[i], i); // [num, index]
        }

        return buildTree(postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTree(
            int postorderRootIndex,
            int left,
            int right
    ) {
        if (left > right) return null;

        // create root node
        int rootValue = postorder[postorderRootIndex];
        TreeNode root = new TreeNode(rootValue);

        // find left and right subtree postorder root index
        int inorderRootIndex = inorderValueIndices.get(rootValue);
        int rightSize = right - inorderRootIndex;

        int leftPostorderRootIndex = postorderRootIndex - rightSize - 1;
        int rightPostorderRootIndex = postorderRootIndex - 1;

        // construct left and right subtree
        root.left = buildTree(leftPostorderRootIndex, left, inorderRootIndex - 1);
        root.right = buildTree(rightPostorderRootIndex, inorderRootIndex + 1, right);
        return root;
    }
}
