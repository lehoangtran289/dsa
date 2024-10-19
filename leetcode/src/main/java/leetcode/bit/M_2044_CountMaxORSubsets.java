package leetcode.bit;

import java.util.HashMap;
import java.util.Map;

public class M_2044_CountMaxORSubsets {
    public static void main(String[] args) {
        System.out.println(new M_2044_CountMaxORSubsets().countMaxOrSubsets(new int[]{3, 2, 1, 5}));
    }

    public int countMaxOrSubsets(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int mask = 0; mask < (1 << nums.length); ++mask) {
            int cur = 0;

            for (int i = 0; i < nums.length; ++i) {
                if ( ((mask >> i) & 1) == 1 ) {
                    cur |= nums[i];
                }
            }

            max = Math.max(max, cur);
            if (max == cur) {
                freq.put(max, freq.getOrDefault(max, 0) + 1);
            }
        }

        return freq.get(max);
    }
}
