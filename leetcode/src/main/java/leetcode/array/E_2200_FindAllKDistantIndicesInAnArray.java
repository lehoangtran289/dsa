package leetcode.array;

import java.util.*;

public class E_2200_FindAllKDistantIndicesInAnArray {
    public static void main(String[] args) {
        System.out.println(findKDistantIndices(new int[]{3, 4, 9, 1, 3, 9, 5}, 9, 1)); // [1, 2, 3, 4, 5, 6]
    }

    /**
     * 1 pass
     * Idea: If nums[i] == key, then all indices in the range [i-k, i+k] are valid.
     * --------------------
     * TC: O(N)
     * SC: O(N)
     */
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            if (nums[i] == key) {
                for (int j = Math.max(0, i - k); j <= Math.min(i + k, n - 1); ++j) {
                    if (!seen.contains(j)) {
                        seen.add(j);
                        res.add(j);
                    }
                }
            }
        }

        return res;
    }
}
