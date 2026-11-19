package leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class M_15_3Sum {
    static void main() {
        System.out.println(threeSum(new int[]{-2, 0, 1, 1, 2})); // [[-2,0,2],[-2,1,1]]
        System.out.println(threeSum(new int[]{0, 0, 0, 0}));
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    /**
     * Idea: fix 1 element -> narrow down the problem to 2sum
     * To solve 2 sum, we can sort the array beforehand and do 2 pointers
     * To check for duplicate triplets, just check if first element value has been processed or not.
     * ---
     * TC: O(N^2)
     * SC: O(N)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; ++i) {
            if (i != 0 && nums[i] == nums[i - 1]) continue; // skip processed value
            if (nums[i] > 0) break; // smallest number > 0 -> 3sum > 0

            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum == 0) {
                    res.add(List.of(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return new ArrayList<>(res);
    }
}
