package leetcode.graph.tree.binarytree;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class M_1660P_CorrectABinaryTree {

    /**
     * Idea: Since defect node points to same-depth node -> using level BFS
     * At a certain level, if a node's child points to an already processed node -> defect node
     * Update defect node's parent points to null -> Need to store {node : parent} mapping
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public TreeNode correctBinaryTree(TreeNode root) {
        Map<TreeNode, TreeNode> nodeParentMap = new HashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        nodeParentMap.put(root, null);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                TreeNode node = queue.poll();

                // node or node.right cant be NPE
                if (nodeParentMap.containsKey(node.right)) { // already process -> defection
                    TreeNode parent = nodeParentMap.get(node);
                    if (parent.left == node) parent.left = null;
                    else parent.right = null;
                    return root;
                }

                if (node.left != null) {
                    queue.add(node.left);
                    nodeParentMap.put(node.left, node);
                }

                if (node.right != null) {
                    queue.add(node.right);
                    nodeParentMap.put(node.right, node);
                }
            }
        }
        return root;
    }
}
