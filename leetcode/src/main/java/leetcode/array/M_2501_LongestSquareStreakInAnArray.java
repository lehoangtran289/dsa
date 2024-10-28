package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_2501_LongestSquareStreakInAnArray {
    public static void main(String[] args) {
        System.out.println(new M_2501_LongestSquareStreakInAnArray().longestSquareStreak(new int[]{4, 3, 6, 16, 8, 2})); // 3
//        System.out.println(new M_2501_LongestSquareStreakInAnArray().longestSquareStreak(new int[]{2, 3, 5, 6, 7})); // -1
    }

    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);
        int result = 0;

        Map<Long, Integer> map = new HashMap<>();
        for (int num : nums) {
            int len = map.containsKey((long) num) ? map.get((long) num) + 1 : 1;
            map.put((long) num * num, len);

            result = Math.max(result, len);
        }
        return result < 2 ? -1 : result;
    }
}
