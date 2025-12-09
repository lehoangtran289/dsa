package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_3583_CountSpecialTriplets {
    public static void main(String[] args) {
        M_3583_CountSpecialTriplets solver = new M_3583_CountSpecialTriplets();

        int[] nums1 = {8, 4, 2, 8, 4};
        System.out.println(solver.specialTriplets(nums1)); // 2
        System.out.println(solver.specialTriplets2(nums1)); // 2

        int[] nums2 = {28, 52, 14, 28, 34, 26, 14, 52};
        System.out.println(solver.specialTriplets(nums2)); // 2
        System.out.println(solver.specialTriplets2(nums2)); // 2
    }

    /**
     * Prefix and Suffix count
     * ------------------------------
     * TC: O(N)
     * SC: O(M) where M is the range of numbers in nums
     */
    public int specialTriplets(int[] nums) {
        final int MOD = 1_000_000_007;
        long res = 0;

        int[] prefix = new int[100_001];
        int[] suffix = new int[100_001];

        // Initialize rear counts
        for (int num : nums) suffix[num]++;

        for (int num : nums) {
            suffix[num]--;
            int target = num * 2;

            if (target < prefix.length) {
                res = (res + (long) prefix[target] * suffix[target]) % MOD;
            }
            prefix[num]++;
        }

        return (int) res;
    }

    /**
     * Binary Search
     * Idea: maintain a map of number to its indexes in the array and binary search using j
     * ------------------------------
     * TC: O(N log N)
     * SC: O(N)
     */
    public int specialTriplets2(int[] nums) {
        final int MOD = (int) 1e9 + 7;
        int n = nums.length;

        // build num - indexes map
        Map<Integer, List<Integer>> numIndexMap = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            numIndexMap.putIfAbsent(nums[i], new ArrayList<>());
            numIndexMap.get(nums[i]).add(i);
        }

        long res = 0;
        for (int j = 1; j < n - 1; ++j) {
            int target = nums[j] * 2;
            List<Integer> targetIds = numIndexMap.getOrDefault(target, new ArrayList<>());

            if (targetIds.size() <= 1) continue;

            res = (res + (long) upper(targetIds, j) * lower(targetIds, j)) % MOD;
        }

        return (int) res;
    }

    private int upper(List<Integer> ids, int target) {
        int res = -1;
        int l = 0, r = ids.size() - 1;

        while (l <= r) {
            int mid = r - (r - l) / 2;

            if (ids.get(mid) > target) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res == -1 ? 0 : ids.size() - res;
    }

    private int lower(List<Integer> ids, int target) {
        int res = -1;
        int l = 0, r = ids.size() - 1;

        while (l <= r) {
            int mid = r - (r - l) / 2;

            if (ids.get(mid) < target) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res == -1 ? 0 : res + 1;
    }
}
