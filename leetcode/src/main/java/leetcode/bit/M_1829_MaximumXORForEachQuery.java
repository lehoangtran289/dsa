package leetcode.bit;

import java.util.Arrays;

public class M_1829_MaximumXORForEachQuery {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getMaximumXor(new int[]{0, 1, 1, 3}, 2)));
    }

    public static int[] getMaximumXor(int[] nums, int maximumBit) {
        int[] prefixXor = new int[nums.length];
        prefixXor[0] = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            prefixXor[i] = prefixXor[i - 1] ^ nums[i];
        }

        int[] res = new int[nums.length];
        int idx = 0;
        int mask = (1 << maximumBit) - 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            res[idx++] = mask ^ prefixXor[i]; // or mask - prefixXor[i]
        }

        return res;
    }
}
