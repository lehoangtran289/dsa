package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_2845_CountOfInterestingSubarrays {

    /**
     * PREFIX SUM + COUNTING
     * TC: O(n)
     * SC: O(k)
     */
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long res = 0;
        int n = nums.size();

        int curCnt = 0;
        Map<Integer, Integer> freq = new HashMap<>(); // map[prefix_cnt] = #numbers of occurrences
        freq.put(0, 1); // base: empty prefix cnt with mod = 0

        for (int num : nums) {
            curCnt += (num % modulo == k) ? 1 : 0;
            curCnt %= modulo;

            // process count % modulo == k using prefix sum
            // ~ (sum[r] − sum[l−1]) mod modulo = k
            // ~ (sum[r] − k) mod modulo = sum[l−1] mod modulo

            int prev = (curCnt - k + modulo) % modulo; // + modulo to handle negative numbers
            res += freq.getOrDefault(prev, 0);
            freq.put(curCnt, freq.getOrDefault(curCnt, 0) + 1);
        }

        return res;
    }
}
