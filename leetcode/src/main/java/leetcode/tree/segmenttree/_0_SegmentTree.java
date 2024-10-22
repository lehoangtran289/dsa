package leetcode.tree.segmenttree;

import java.util.Arrays;

public class _0_SegmentTree {

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

        // --------------------------------

        int[] arr3 = {1, 3, 5, 7, 9, 11};
        LazySegmentTreeSumRange tree3 = new LazySegmentTreeSumRange(arr3);
        tree3.printTree();

        System.out.println("(1, 4): " + tree3.getSum(2, 4)); // 21

        tree3.updateRange(1, 4, 2);

        System.out.println("(1, 4): " + tree3.getSum(2, 4)); // 27
    }
}

class LazySegmentTreeSumRange {
    int[] a;
    int[] st;
    int[] lazy;
    int size;

    public LazySegmentTreeSumRange(int[] arr) {
        size = arr.length;
        a = arr;
        st = new int[2 * (int) Math.pow(2, (int) (Math.ceil(Math.log(size) / Math.log(2)))) - 1]; // Memory allocation
        lazy = new int[st.length];
        build(0, size - 1, 0);
    }

    int midId(int s, int e) {
        return s + (e - s) / 2;
    }

    void printTree() {
        System.out.println(Arrays.toString(st));
    }

    void build(int ss, int se, int si) {
        // out of range as ss can never be greater than se
        if (ss > se)
            return;

        if (ss == se) {
            st[si] = a[ss];
            return;
        }

        int mid = (ss + se) / 2;
        build(ss, mid, si * 2 + 1);
        build(mid + 1, se, si * 2 + 2);

        st[si] = st[si * 2 + 1] + st[si * 2 + 2];
    }

    void updateRange(int us, int ue, int diff) {
        updateRangeUtil(0, 0, size - 1, us, ue, diff);
    }

    /**
     * @param si   index of current node in segment tree
     * @param ss   Starting indexes of elements for which current nodes stores sum.
     * @param se   ending indexes of elements for which current nodes stores sum.
     * @param us   starting and ending indexes of update query
     * @param ue   starting and ending indexes of update query
     * @param diff which we need to add in the range us to ue
     */
    void updateRangeUtil(int si, int ss, int se, int us, int ue, int diff) {
        // If lazy value is non-zero for current node of segment
        // tree, then there are some pending updates. So we need
        // to make sure that the pending updates are done before
        // making new updates. Because this value may be used by
        // parent after recursive calls (See last line of this
        // function)
        if (lazy[si] != 0) {
            // Make pending updates using value stored in lazy
            // nodes
            st[si] += (se - ss + 1) * lazy[si];

            // checking if it is not leaf node because if
            // it is leaf node then we cannot go further
            if (ss != se) {
                // We can postpone updating children we don't
                // need their new values now.
                // Since we are not yet updating children of si,
                // we need to set lazy flags for the children
                lazy[si * 2 + 1] += lazy[si];
                lazy[si * 2 + 2] += lazy[si];
            }

            // Set the lazy value for current node as 0 as it
            // has been updated
            lazy[si] = 0;
        }

        // out of range
        if (ss > se || ss > ue || se < us)
            return;

        // Current segment is fully in range
        if (ss >= us && se <= ue) {
            // Add the difference to current node
            st[si] += (se - ss + 1) * diff;

            // same logic for checking leaf node or not
            if (ss != se) {
                // This is where we store values in lazy nodes,
                // rather than updating the segment tree itself
                // Since we don't need these updated values now
                // we postpone updates by storing values in lazy[]
                lazy[si * 2 + 1] += diff;
                lazy[si * 2 + 2] += diff;
            }
            return;
        }

        // If not completely in rang, but overlaps, recur for
        // children,
        int mid = midId(ss, se);
        updateRangeUtil(si * 2 + 1, ss, mid, us, ue, diff);
        updateRangeUtil(si * 2 + 2, mid + 1, se, us, ue, diff);

        // And use the result of children calls to update this
        // node
        st[si] = st[si * 2 + 1] + st[si * 2 + 2];
    }

    int getSumUtil(int ss, int se, int qs, int qe, int si) {
        // If lazy flag is set for current node of segment tree,
        // then there are some pending updates. So we need to
        // make sure that the pending updates are done before
        // processing the sub sum query
        if (lazy[si] != 0) {
            // Make pending updates to this node. Note that this
            // node represents sum of elements in arr[ss..se] and
            // all these elements must be increased by lazy[si]
            st[si] += (se - ss + 1) * lazy[si];

            // checking if it is not leaf node because if
            // it is leaf node then we cannot go further
            if (ss != se) {
                // Since we are not yet updating children os si,
                // we need to set lazy values for the children
                lazy[si * 2 + 1] += lazy[si];
                lazy[si * 2 + 2] += lazy[si];
            }

            // unset the lazy value for current node as it has
            // been updated
            lazy[si] = 0;
        }

        // Out of range
        if (ss > se || ss > qe || se < qs)
            return 0;

        // At this point sure, pending lazy updates are done
        // for current node. So we can return value (same as
        // was for query in our previous post)

        // If this segment lies in range
        if (ss >= qs && se <= qe)
            return st[si];

        // If a part of this segment overlaps with the given
        // range
        int mid = (ss + se) / 2;
        return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    // Return sum of elements in range from index qs (query
    // start) to qe (query end). It mainly uses getSumUtil()
    int getSum(int qs, int qe) {
        // Check for erroneous input values
        if (qs < 0 || qe > size - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return -1;
        }

        return getSumUtil(0, size - 1, qs, qe, 0);
    }
}

class SegmentTreeSumRange {
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
        if (updateId < sL || updateId > sR) return;

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

class SegmentTreeRMQ {
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
        if (updateId < sL || updateId > sR) return;

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



