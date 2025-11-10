package leetcode.graph.tree.fenwicktree;

/**
 * Fenwick Tree (Binary Indexed Tree)
 * <a href="https://www.hackerearth.com/practice/notes/binary-indexed-tree-or-fenwick-tree/">ref</a>
 * <p>
 *      BIT[i] = a[i], if i is odd
 *      BIT[i] = a[1] + ... + a[i], if i is power of 2
 * <p>
 * example:
 *      Sum a[1,12] = BIT[12] + BIT[8] = (a[12] + … + a[9]) + (a[8] + … + a[1])
 *      Sum a[1,6] = BIT[6] + BIT[4] = (a[6] + a[5]) + (a[4] + … + a[1])
 *      Sum a[1,8] = BIT[8] = a[8] + … + a[1]
 * ----------------------------------
 * <p>
 * Supports point updates and prefix sum queries in O(log n) time.
 * Tricks:
 * - To get the last set bit: i & -i (e.g: 101001000  ->  000001000)
 * - To get the parent index: i -= (i & -i)
 * - To get the next index to update: i += (i & -i)
 * ----------------------------------
 * <p>
 * TC:
 * - Construction: O(n)
 * - Update: O(log n)
 * - Query: O(log n)
 * SC: O(n)
 */
public class FenwickTree {
    private final int[] tree;

    public FenwickTree(int size) {
        tree = new int[size + 1]; // 1-indexed
    }

    public FenwickTree(int[] nums) {
        tree = new int[nums.length + 1];

        for (int i = 0; i < nums.length; ++i) {
            update(i, nums[i]);
        }
    }

    /**
     * add delta to index i
     * Idea: Update all relevant nodes that cover index i
     * ----------------------------------
     * Example: update(13, delta) ~ assume 1-indexed
     *      BIT[13] += delta
     *          13(1101) -> last set bit = 1 -> next index = 13 + 1 = 14 (1110)
     *      BIT[14] += delta
     *          14(1110) -> last set bit = 2 -> next index = 14 + 2 = 16 (10000)
     *      BIT[16] += delta
     */
    public void update(int i, int delta) {
        i++; // convert to 1-indexed
        while (i < tree.length) {
            tree[i] += delta;
            i += (i & -i);
        }
    }

    /**
     * get prefix sum from [0..i]
     * Idea: Sum all relevant nodes that cover [0..i]
     * ----------------------------------
     * Example: query(13) ~ assume 1-indexed
     *      res += BIT[13]
     *          13(1101) -> last set bit = 1 -> parent index = 13 - 1 = 12 (1100)
     *      res += BIT[12]
     *          12(1100) -> last set bit = 4 -> parent index = 12 - 4 = 8 (1000)
     *      res += BIT[8]
     *          8(1000) -> last set bit = 8 -> parent index = 8 - 8 = 0 (0000)
     */
    public int query(int i) {
        i++; // convert to 1-indexed

        int res = 0;
        while (i > 0) {
            res += tree[i];
            i -= (i & -i);
        }
        return res;
    }

    public int rangeSum(int l, int r) {
        if (l > r) return 0;
        return query(r) - query(l - 1);
    }
}
