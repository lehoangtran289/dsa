package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class _E_1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(target - nums[i]))
                return new int[]{map.get(target - nums[i]), i};
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        _E_1_TwoSum twoSum = new _E_1_TwoSum();
        int[] result = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(result[0] + " " + result[1]);
    }
}
