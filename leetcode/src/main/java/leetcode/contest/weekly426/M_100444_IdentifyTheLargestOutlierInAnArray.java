package leetcode.contest.weekly426;

import java.util.HashMap;
import java.util.Map;

public class M_100444_IdentifyTheLargestOutlierInAnArray {
    public static void main(String[] args) {
        System.out.println(getLargestOutlier(new int[]{6, -31, 50, -35, 41, 37, -42, 13}));
        System.out.println(getLargestOutlier(new int[]{-108, -108, -517}));
        System.out.println(getLargestOutlier(new int[]{2, 3, 5, 10}));
        System.out.println(getLargestOutlier(new int[]{-2, -1, -3, -6, 4}));
        System.out.println(getLargestOutlier(new int[]{1, 1, 1, 1, 1, 5, 5}));
    }

    public static int getLargestOutlier(int[] nums) {
        int totalSum = 0;
        for (int n : nums) totalSum += n;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int outlier = nums[i];
            map.put(totalSum - outlier, i);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int specialSum = nums[i] * 2;

            if (map.containsKey(specialSum) && map.get(specialSum) != i) {
                int outlier = nums[map.get(specialSum)];
                res = Math.max(res, outlier);
            }
        }
        return res;
    }
}
