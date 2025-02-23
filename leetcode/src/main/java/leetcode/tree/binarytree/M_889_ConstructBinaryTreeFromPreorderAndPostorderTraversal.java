package leetcode.tree.binarytree;

import java.util.HashMap;
import java.util.Map;

public class M_889_ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public static void main(String[] args) {
        System.out.println(constructFromPrePost(
                new int[]{1, 2, 4, 5, 3, 6, 7},
                new int[]{4, 5, 2, 6, 7, 3, 1}
        ));
    }

    // --------------------------------------------------------------------------------------

    private static final Map<Integer, Integer> postOrderIndexMap = new HashMap<>();
    private static int[] preorder;

    public static TreeNode constructFromPrePost(int[] preorderArr, int[] postorder) {
        preorder = preorderArr;

        // build postOrderIndexMap
        for (int i = 0; i < postorder.length; ++i) {
            postOrderIndexMap.put(postorder[i], i);
        }

        return constructTree(0, preorder.length - 1, 0);
    }

    private static TreeNode constructTree(
            int preStartId,     // root id
            int preEndId,       // end id of subtree
            int postStartId     // keep track start id of subtree
    ) {
        // base case: if no nodes to process, return null
        if (preStartId > preEndId) return null;

        // base case: if one node is left, return that node
        if (preStartId == preEndId) {
            return new TreeNode(preorder[preStartId]);
        }

        // left child root value in preorder, (next to root)
        int leftRoot = preorder[preStartId + 1];

        // number of nodes in left-subtree by searching postorder position
        int leftNodesCount = postOrderIndexMap.get(leftRoot) - postStartId + 1;

        // construct tree, root = preorder[startId]
        TreeNode root = new TreeNode(preorder[preStartId]);

        root.left = constructTree(
                preStartId + 1,                    // left root
                preStartId + leftNodesCount,                // end left subtree id
                postStartId                                // keep track start id of subtree
        );

        root.right = constructTree(
                preStartId + leftNodesCount + 1,   // right root
                preEndId,                                   // end right subtree id
                postStartId + leftNodesCount               // keep track start id of subtree
        );

        return root;
    }
}
