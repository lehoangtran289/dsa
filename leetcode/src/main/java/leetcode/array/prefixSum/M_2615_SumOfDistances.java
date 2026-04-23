package leetcode.array.prefixSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_2615_SumOfDistances {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(distance(new int[]{1, 3, 1, 1, 2}))); // [5, 0, 3, 4, 0]
    }

    /**
     * Group indices by value, then use prefix sum to calculate distance for each group.
     * ----
     * TC: O(n) to group indices + O(n) to calculate distances = O(n)
     * SC: O(n) for grouping and prefix sum
     */
    public static long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();

        // group indices
        for (int i = 0; i < n; i++) {
            valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
            valueToIndices.get(nums[i]).add(i);
        }

        // process each group
        for (var indices : valueToIndices.values()) {
            int size = indices.size();

            // prefix sum array
            long[] prefix = new long[size + 1];
            for (int i = 0; i < size; i++) {
                prefix[i + 1] = prefix[i] + indices.get(i);
            }

            for (int i = 0; i < size; ++i) {
                int index = indices.get(i);

                long left = (long) index * i - prefix[i];
                long right = (long) (prefix[size] - prefix[i + 1]) - (long) index * (size - 1 - i);

                res[index] = left + right;
            }
        }
        return res;
    }
}
