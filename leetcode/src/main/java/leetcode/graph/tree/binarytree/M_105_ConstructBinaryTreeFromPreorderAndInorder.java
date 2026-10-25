package leetcode.graph.tree.binarytree;

import java.util.HashMap;
import java.util.Map;

public class M_105_ConstructBinaryTreeFromPreorderAndInorder {
    static void main() {
        M_105_ConstructBinaryTreeFromPreorderAndInorder sol = new M_105_ConstructBinaryTreeFromPreorderAndInorder();
        System.out.println(sol.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    }

    private Map<Integer, Integer> inorderRootIndices;
    private int[] preorder;

    /**
     * Idea: Divide and Conquer, preorder[] -> root index at beginning of the array
     * From preorder root index -> Find that root index in the inorder[]
     * To avoid scanning whole array -> pre-build a hashmap to store each node index.
     * ---
     * TC: O(N)
     * SC: O(N) - stack
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.inorderRootIndices = new HashMap<>();
        this.preorder = preorder;

        for (int i = 0; i < inorder.length; ++i) {
            inorderRootIndices.put(inorder[i], i);
        }

        return buildTree(0, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int preorderRootIndex, int left, int right) {
        if (left > right) return null;

        // create root node by preorder[idx] value
        int rootValue = preorder[preorderRootIndex];
        TreeNode root = new TreeNode(rootValue);

        // find corresponding inorder root
        int inorderRootIndex = inorderRootIndices.get(rootValue);

        // find leftSubtree root and rightSubtree root
        int leftSize = inorderRootIndex - left;
        int leftPreorderRootIndex = preorderRootIndex + 1;
        int rightPreorderRootIndex = preorderRootIndex + leftSize + 1; // skip cur root

        // build subtree
        root.left = buildTree(leftPreorderRootIndex, left, inorderRootIndex - 1);
        root.right = buildTree(rightPreorderRootIndex, inorderRootIndex + 1, right);
        return root;
    }
}
