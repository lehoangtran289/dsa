package leetcode.graph.tree.binarytree;

public class M_450_DeleteNodeInABST {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // search in right value if key > root
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        // search in left value if key < root
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }

        // found key, check if key has 1 child
        if (root.left == null) return root.right;
        if (root.right == null) return root.left;

        // key has 2 childs -> find successor and re-structure the tree
        TreeNode successor = findSuccessor(root);

        // Attach the left subtree of deleted node to the successor's left
        successor.left = root.left;
        root.left = null;

        // replace deleted node with its right subtree
        root = root.right;

        return root;
    }

    /**
     * Find successor of a node ~ smallest node in right subtree
     */
    private TreeNode findSuccessor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
