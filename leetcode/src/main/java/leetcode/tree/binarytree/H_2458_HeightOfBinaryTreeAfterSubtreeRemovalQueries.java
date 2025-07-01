package leetcode.tree.binarytree;

import java.util.Arrays;

/**
 * Maximum height of tree = tree height <br>
 * // <br>
 * We’ll perform a preorder traversal, tracking the maximum height from the root. <br>
 * However, if the maximum height is achieved in the right subtree, we may miss it when traversing the left.  <br>
 * To address this, we perform a second traversal in reverse preorder (root, right, left). <br>
 */
public class H_2458_HeightOfBinaryTreeAfterSubtreeRemovalQueries {
    static final int[] curMaxHeights = new int[11];
    int curMaxHeight = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5,
                                     new TreeNode(8,
                                                  new TreeNode(2,
                                                               new TreeNode(4),
                                                               new TreeNode(6)),
                                                  new TreeNode(1)),
                                     new TreeNode(9,
                                                  new TreeNode(3),
                                                  new TreeNode(7))
        );

        System.out.println(Arrays.toString(new H_2458_HeightOfBinaryTreeAfterSubtreeRemovalQueries()
                                                   .treeQueries(root, new int[]{3, 2, 4, 8})));
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        treeTraverseLeft(root, 0);
        curMaxHeight = 0;
        treeTraverseRight(root, 0);

//        System.out.println(Arrays.toString(curMaxHeights));

        // construct query results
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            result[i] = curMaxHeights[queries[i]];
        }
        return result;
    }

    public void treeTraverseLeft(TreeNode node, int curHeight) {
        if (node == null) return;

        // store max height if this node is removed
        curMaxHeights[node.val] = curMaxHeight;

        // update current max height of the tree and continue traverse
        curMaxHeight = Math.max(curMaxHeight, curHeight);

        treeTraverseLeft(node.left, curHeight + 1);
        treeTraverseLeft(node.right, curHeight + 1);
    }

    public void treeTraverseRight(TreeNode node, int curHeight) {
        if (node == null) return;

        // compare to height when traversing from the left and update max height if this node is removed
        curMaxHeights[node.val] = Math.max(curMaxHeight, curMaxHeights[node.val]);
        curMaxHeight = Math.max(curMaxHeight, curHeight);

        treeTraverseRight(node.right, curHeight + 1);
        treeTraverseRight(node.left, curHeight + 1);
    }
}
