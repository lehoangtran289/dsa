package leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Since nums1,length << nums2.length,
 * we can use a hashmap to store the frequency of each number in nums2.
 */
public class M_1865_FindingPairsWithACertainSum {

    private final int[] nums1;
    private final int[] nums2;
    private final Map<Integer, Integer> freq2;

    public M_1865_FindingPairsWithACertainSum(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.freq2 = new HashMap<>();

        for (int num : nums2) {
            freq2.put(num, freq2.getOrDefault(num, 0) + 1);
        }
    }

    /**
     * Add val to nums2[index]
     * -------------------------
     * TC: O(1)
     * SC: O(1)
     */
    public void add(int index, int val) {
        int prev = nums2[index];
        int next = prev + val;

        // update nums2[index]
        nums2[index] = next;

        // update freq map in nums2
        // -1 prev && +1 next
        freq2.put(prev, freq2.get(prev) - 1);
        if (freq2.get(prev) == 0) freq2.remove(prev);
        freq2.put(next, freq2.getOrDefault(next, 0) + 1);
    }

    /**
     * Count the number of pairs (i, j) such that nums1[i] + nums2[j] == tot
     * -------------------------
     * TC: O(n) where n = nums1.length
     * SC: O(n) for freq2 map
     */
    public int count(int tot) {
        int res = 0;

        for (int num : nums1) {
            res += freq2.getOrDefault(tot - num, 0);
        }

        return res;
    }
}
