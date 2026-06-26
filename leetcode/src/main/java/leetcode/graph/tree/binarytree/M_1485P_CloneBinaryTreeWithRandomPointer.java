package leetcode.graph.tree.binarytree;

import java.util.HashMap;

public class M_1485P_CloneBinaryTreeWithRandomPointer {

    private final HashMap<Node, NodeCopy> newOldPairs = new HashMap<>();

    public NodeCopy copyRandomBinaryTree(Node root) {
        NodeCopy newRoot = deepCopy(root);
        mapRandomPointers(root);
        return newRoot;
    }

    /**
     * Deep copy binary tree (without concerning random pointers)
     * Store mapping of old -> new nodes
     */
    private NodeCopy deepCopy(Node node) {
        if (node == null) return null;

        NodeCopy newNode = new NodeCopy(node.val);
        newNode.left = deepCopy(node.left);
        newNode.right = deepCopy(node.right);

        newOldPairs.put(node, newNode);

        return newNode;
    }

    /**
     * Traverse tree and map random pointers
     */
    private void mapRandomPointers(Node node) {
        if (node == null) return;

        NodeCopy corrNewNode = newOldPairs.get(node);
        corrNewNode.random = newOldPairs.get(node.random); // corresponding random node

        mapRandomPointers(node.left);
        mapRandomPointers(node.right);
    }

    //-------------------DEFINITION-------------------//

    static class NodeCopy {
        int val;
        NodeCopy random;
        NodeCopy left;
        NodeCopy right;

        NodeCopy(int val) {
            this.val = val;
        }
    }

    static class Node {
        int val;
        Node random;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }
}