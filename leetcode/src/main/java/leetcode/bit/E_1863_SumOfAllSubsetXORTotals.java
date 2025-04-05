package leetcode.bit;

public class E_1863_SumOfAllSubsetXORTotals {

    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int mask = 1 << n;

        int res = 0;

        // Generate all subsets using bitmask
        for (int i = 1; i <= mask; ++i) {
            int xor = 0;
            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) > 0) {
                    xor ^= nums[j];
                }
            }
            res += xor;
        }

        return res;
    }
}
