package leetcode.segmenttree;

import java.util.Arrays;

// implement segment tree with lazy update propagation
public class SegmentTree {
    private static final int INF = (int) 1e9 + 7;
    private static final int MAXN = (int) 1e5 + 7;

    private static int n, q;
    private static int[] a = new int[MAXN];
    private static int[] st = new int[4 * MAXN];

    // Build the segment tree
    private static void build(int id, int l, int r) {
        // If the segment consists of a single element
        if (l == r) {
            st[id] = a[l];
            return;
        }

        // Recursively build the left and right children
        int mid = (l + r) >> 1; // (l + r) / 2
        build(2 * id, l, mid);
        build(2 * id + 1, mid + 1, r);

        // Update the current node with the minimum value of its children
        st[id] = Math.min(st[2 * id], st[2 * id + 1]);
    }

    // Update the segment tree
    private static void update(int id, int l, int r, int i, int val) {
        // If the index is out of bounds for the current segment
        if (l > i || r < i) return;

        // If the segment consists of a single element
        if (l == r) {
            st[id] = val;
            return;
        }

        // Recursively update the left and right children
        int mid = (l + r) >> 1; // (l + r) / 2
        update(2 * id, l, mid, i, val);
        update(2 * id + 1, mid + 1, r, i, val);

        // Update the current node with the minimum value of its children
        st[id] = Math.min(st[2 * id], st[2 * id + 1]);
    }

    // Get the minimum value in the range [u, v]
    private static int get(int id, int l, int r, int u, int v) {
        // If the segment [l, r] is completely outside the query range [u, v]
        if (l > v || r < u) return INF;

        // If the segment [l, r] is completely within the query range [u, v]
        if (l >= u && r <= v) return st[id];

        // Recursively get the minimum value from the left and right children
        int mid = (l + r) >> 1; // (l + r) / 2
        int get1 = get(2 * id, l, mid, u, v);
        int get2 = get(2 * id + 1, mid + 1, r, u, v);

        // Return the minimum of the two values
        return Math.min(get1, get2);
    }

    public static void main(String[] args) {
        n = 5;
        q = 5;
        Arrays.fill(a, INF);

        // Build the segment tree
        build(1, 1, n);

        // Update the segment tree
        update(1, 1, n, 1, 2);
        update(1, 1, n, 2, 3);
        update(1, 1, n, 3, 4);
        update(1, 1, n, 4, 5);
        update(1, 1, n, 5, 6);

        // Get the minimum value in the range [2, 4]
        System.out.println(get(1, 1, n, 2, 4)); // 3

    }
}
