package leetcode.tree.segmenttree;

// implement segment tree with lazy update propagation
public class _0_SegmentTree_1 {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11};
        SegmentTree segmentTree = new SegmentTree(nums);
        segmentTree.printTree(segmentTree.root);

        System.out.println("Range Sum (0, 2): " + segmentTree.sumRange(0, 2)); // 9

        // Update index 1 to value 10
        segmentTree.update(1, 10);
        System.out.println("Range Sum (0, 2): " + segmentTree.sumRange(0, 2)); // 16

        // Additional tests
        System.out.println("Range Sum (1, 3): " + segmentTree.sumRange(1, 3)); // 22
        System.out.println("Range Sum (2, 5): " + segmentTree.sumRange(2, 5)); // 32

        // Update index 4 to value 6
        segmentTree.update(4, 6);
        System.out.println("Range Sum (3, 5): " + segmentTree.sumRange(3, 5)); // 24
    }
}

class Node {
    int start, end;
    int sum;
    Node left, right;

    Node(int start, int end) {
        this.start = start;
        this.end = end;
        this.sum = 0;
        this.left = null;
        this.right = null;
    }
}

class SegmentTree {
    Node root;

    SegmentTree(int[] nums) {
        root = build(nums, 0, nums.length - 1);
    }

    public void printTree(Node node) {
        if (node == null || node.start == node.end) {
            return;
        }
        System.out.println(node.start + " " + node.end + " " + node.sum);
        printTree(node.left);
        printTree(node.right);
    }

    private Node build(int[] nums, int start, int end) {
        if (start > end) {
            return null; // Empty node
        }
        Node node = new Node(start, end);
        if (start == end) {
            node.sum = nums[start]; // store the value directly
        } else {
            int mid = start + (end - start) / 2;
            node.left = build(nums, start, mid); // Build left subtree
            node.right = build(nums, mid + 1, end); // Build right subtree
            node.sum = node.left.sum + node.right.sum; // Combine values from children
        }
        return node;
    }

    public void update(int i, int val) {
        update(root, i, val);
    }

    private void update(Node node, int index, int val) {
        if (node.start == node.end) {
            // Leaf node: update the value
            node.sum = val;
        } else {
            int mid = node.start + (node.end - node.start) / 2;
            if (index <= mid) {
                // Update left subtree
                update(node.left, index, val);
            } else {
                // Update right subtree
                update(node.right, index, val);
            }

            // Recalculate sum
            node.sum = node.left.sum + node.right.sum;
        }
    }

    /**
     * Query the range sum [i, j]
     */
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    private int sumRange(Node node, int start, int end) {
        if (node == null || start > node.end || end < node.start) {
            return 0; // Out of range or null node
        }
        if (start <= node.start && end >= node.end) {
            return node.sum; // Fully covered by this node
        }
        return sumRange(node.left, start, end) + sumRange(node.right, start, end);
    }
}
