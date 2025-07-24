package leetcode.array.twopointers;

import java.util.*;

public class M_18_4Sum {
    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    }

    /**
     * Wrap 3 Sum and 2 Sum with two pointers
     * -----------------
     * TC: O(n^3)
     * SC: O(1)
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;

        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                long complement = (long) target - nums[i] - nums[j];

                int l = j + 1, r = n - 1;
                while (l < r) {
                    long twoSum = nums[l] + nums[r];

                    if (twoSum > complement) {
                        r--;
                    } else if (twoSum < complement) {
                        l++;
                    } else {
                        res.add(List.of(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                        r--;
                    }
                }
            }
        }

        return new ArrayList<>(res);
    }

    /**
     * Wrap 3 Sum and 2 Sum with HashSet
     * -----------------
     * TC: O(n^3)
     * SC: O(n)
     */
    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                long complement = (long) target - nums[i] - nums[j];

                Set<Long> seen = new HashSet<>();
                for (int k = j + 1; k < n; ++k) {
                    if (seen.contains(complement - nums[k])) {
                        List<Integer> quad = Arrays.asList(nums[i], nums[j], nums[k], (int) complement - nums[k]);
                        Collections.sort(quad);
                        res.add(quad);
                    }

                    seen.add((long) nums[k]);
                }
            }
        }

        return new ArrayList<>(res);
    }
}
