package leetcode.segmenttree;

import java.util.Arrays;

// implement segment tree with lazy update propagation
public class _0_SegmentTree {
    static class SegmentTreeSumRange {
        int[] a;
        int[] st;
        int size;

        public SegmentTreeSumRange(int[] arr) {
            size = arr.length;
            a = arr;
            st = new int[2 * (int) Math.pow(2, (int) (Math.ceil(Math.log(size) / Math.log(2)))) - 1]; // Memory allocation
            build(0, 0, size - 1);
        }

        int midId(int s, int e) {
            return s + (e - s) / 2;
        }

        void printTree() {
            System.out.println(Arrays.toString(st));
        }

        void build(int curId, int l, int r) {
            if (l == r) {
                st[curId] = a[l];
                return;
            }

            int mid = l + (r - l) / 2;
            build(2 * curId + 1, l, mid);
            build(2 * curId + 2, mid + 1, r);

            // Update the current node with the <<TASK>> of its children
            st[curId] = st[2 * curId + 1] + st[2 * curId + 2];
        }

        void update(int idx, int val) {
            update(0, 0, size - 1, idx, val);
        }

        // Update the segment tree
        private void update(int curId, int sL, int sR, int updateId, int newVal) {
            // If the index is out of bounds for the current segment
            if (updateId < sL  || updateId > sR) return;

            // If the segment consists of a single element
            if (sL == sR) {
                st[curId] = newVal;
                return;
            }

            // Recursively update the left and right children
            int mid = midId(sL, sR);
            update(2 * curId + 1, sL, mid, updateId, newVal); // left
            update(2 * curId + 2, mid + 1, sR, updateId, newVal); // right

            // Update the current node with the <<TASK>> value of its children
            st[curId] = st[2 * curId + 1] + st[2 * curId + 2];
        }

        int sumRange(int left, int right) {
            return sumRange(0, 0, size - 1, left, right);
        }

        private int sumRange(int curId, int sL, int sR, int qL, int qR) {
            // If the segment [l, r] is completely outside the query range [u, v]
            if (qL > sR || qR < sL) return 0;

            // If the segment [l, r] is completely within the query range [u, v]
            if (qL <= sL && qR >= sR) return st[curId];

            // Recursively get the minimum value from the left and right children
            int mid = midId(sL, sR);
            int queryLeft = sumRange(2 * curId + 1, sL, mid, qL, qR);
            int queryRight = sumRange(2 * curId + 2, mid + 1, sR, qL, qR);

            // Return the <<TASK>> of the two values
            return queryLeft + queryRight;
        }
    }

    static class SegmentTreeRMQ {
        private static final int INF = (int) 1e9 + 7;
        int[] a;
        int[] st;
        int size;

        public SegmentTreeRMQ(int[] arr) {
            size = arr.length;
            a = arr;
            st = new int[2 * (int) Math.pow(2, (int) (Math.ceil(Math.log(size) / Math.log(2)))) - 1]; // Memory allocation
            build(0, 0, size - 1);
        }

        int midId(int s, int e) {
            return s + (e - s) / 2;
        }

        void printTree() {
            System.out.println(Arrays.toString(st));
        }

        void build(int curId, int l, int r) {
            if (l == r) {
                st[curId] = a[l];
                return;
            }

            int mid = l + (r - l) / 2;
            build(2 * curId + 1, l, mid);
            build(2 * curId + 2, mid + 1, r);

            // Update the current node with the <<TASK>> of its children
            st[curId] = Math.min(st[2 * curId + 1], st[2 * curId + 2]);
        }

        void update(int idx, int val) {
            update(0, 0, size - 1, idx, val);
        }

        // Update the segment tree
        private void update(int curId, int sL, int sR, int updateId, int newVal) {
            // If the index is out of bounds for the current segment
            if (updateId < sL  || updateId > sR) return;

            // If the segment consists of a single element
            if (sL == sR) {
                st[curId] = newVal;
                return;
            }

            // Recursively update the left and right children
            int mid = midId(sL, sR);
            update(2 * curId + 1, sL, mid, updateId, newVal); // left
            update(2 * curId + 2, mid + 1, sR, updateId, newVal); // right

            // Update the current node with the <<TASK>> value of its children
            st[curId] = Math.min(st[2 * curId + 1], st[2 * curId + 2]);
        }

        int sumRange(int left, int right) {
            return sumRange(0, 0, size - 1, left, right);
        }

        private int sumRange(int curId, int sL, int sR, int qL, int qR) {
            // If the segment [l, r] is completely outside the query range [u, v]
            if (qL > sR || qR < sL) return INF;

            // If the segment [l, r] is completely within the query range [u, v]
            if (qL <= sL && qR >= sR) return st[curId];

            // Recursively get the minimum value from the left and right children
            int mid = midId(sL, sR);
            int queryLeft = sumRange(2 * curId + 1, sL, mid, qL, qR);
            int queryRight = sumRange(2 * curId + 2, mid + 1, sR, qL, qR);

            // Return the <<TASK>> of the two values
            return Math.min(queryLeft, queryRight);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTreeSumRange tree = new SegmentTreeSumRange(arr);

        tree.printTree();

        System.out.println("(0, 2): " + tree.sumRange(0, 2)); // 9
        System.out.println("(1, 4): " + tree.sumRange(1, 4)); // 24

        tree.update(2, 2);

        System.out.println("(0, 2): " + tree.sumRange(0, 2)); // 6
        System.out.println("(1, 4): " + tree.sumRange(1, 4)); // 21

        // --------------------------------

        int[] arr2 = {1, 5, 2, 9, 7, 11};
        SegmentTreeRMQ tree2 = new SegmentTreeRMQ(arr2);
        tree2.printTree();

        System.out.println("(0, 2): " + tree2.sumRange(1, 4)); // 2
        System.out.println("(2, 5): " + tree2.sumRange(2, 5)); // 2

        tree2.update(2, 12);

        System.out.println("(0, 2): " + tree2.sumRange(1, 4)); // 5
        System.out.println("(2, 5): " + tree2.sumRange(2, 5)); // 7
    }
}



