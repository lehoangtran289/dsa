package leetcode.graph.tree.fenwicktree;

public class FenwickTree {
    private final int[] tree;

    public FenwickTree(int size) {
        tree = new int[size + 1];
    }

    public FenwickTree(int[] nums) {
        int size = nums.length;
        tree = new int[size + 1];

        for (int i = 0; i < size; ++i) {
            update(i, nums[i]);
        }
    }

    public void update(int index, int delta) {
        index++;
        while (index < tree.length) {
            tree[index] += delta;
            index += (index & -index);
        }
    }

    public int query(int index) {
        index++;
        int res = 0;
        while (index > 0) {
            res += tree[index];
            index -= (index & -index);
        }
        return res;
    }
}
