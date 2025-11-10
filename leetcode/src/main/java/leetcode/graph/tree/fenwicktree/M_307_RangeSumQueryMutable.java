package leetcode.graph.tree.fenwicktree;

public class M_307_RangeSumQueryMutable {
    private final int[] nums;
    private final FenwickTree tree;

    public M_307_RangeSumQueryMutable(int[] nums) {
        this.nums = nums;
        this.tree = new FenwickTree(nums);
    }

    public void update(int index, int val) {
        tree.update(index, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return tree.rangeSum(left, right);
    }
}
