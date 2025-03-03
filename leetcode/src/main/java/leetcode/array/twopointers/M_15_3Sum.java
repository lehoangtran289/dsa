package leetcode.array.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Neetcode two pointers 3
public class M_15_3Sum {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-2, 0, 1, 1, 2})); // [[-2,0,2],[-2,1,1]]
        System.out.println(threeSum(new int[]{0, 0, 0, 0}));
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) break;
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            int curNum = nums[i];
            int target = -curNum;

            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] < target) {
                    l++;
                } else if (nums[l] + nums[r] > target) {
                    r--;
                } else {
                    result.add(Arrays.asList(curNum, nums[l], nums[r]));
                    l++;
                    r--;
                }
            }
        }

        return new ArrayList<>(result);
    }
}
