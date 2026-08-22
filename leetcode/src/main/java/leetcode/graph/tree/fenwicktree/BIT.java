package leetcode.graph.tree.fenwicktree;

/**
 * Fenwick Tree (Binary Indexed Tree - BIT)
 */
public class BIT {
    private final int[] bit;
    private final int n;

    public BIT(int size) {
        bit = new int[size + 1];
        n = size;
    }

    /**
     * Construction: O(n)
     */
    public BIT(int[] nums) {
        bit = new int[nums.length + 1];
        n = nums.length;

        for (int i = 0; i < nums.length; ++i) {
            update(i, nums[i]);
        }
    }

    /**
     * add v to index x. O(logn)
     */
    public void update(int x, int v) {
        for (; x <= n; x += x & -x) bit[x] += v;
    }

    /**
     * get prefix sum from [1..x]. O(logn)
     */
    public int get(int x) {
        int res = 0;
        for (; x >= 1; x &= x - 1) res += bit[x];
        return res;
    }
}
