package leetcode.graph.tree.fenwicktree;

public class M_307_RangeSumQueryMutable {
    private final int[] nums;
    private final BIT bit;

    public M_307_RangeSumQueryMutable(int[] nums) {
        this.nums = nums;
        this.bit = new BIT(nums.length);

        for (int i = 0; i < nums.length; ++i) {
            bit.update(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        bit.update(index + 1, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return bit.get(right + 1) - bit.get(left);
    }

    static class BIT {
        int[] bit; // 1-indexed array
        int n;

        BIT(int size) {
            this.n = size;
            bit = new int[n + 1];
        }

        void update(int x, int v) {
            for (; x <= n; x += (x & -x)) bit[x] += v;
        }

        int get(int x) {
            int res = 0;
            for (; x >= 1; x &= (x - 1)) res += bit[x];
            return res;
        }
    }
}
