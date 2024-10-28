package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class M_2501_LongestSquareStreakInAnArray {
    public static void main(String[] args) {
        System.out.println(new M_2501_LongestSquareStreakInAnArray().longestSquareStreak(new int[]{4, 3, 6, 16, 8, 2})); // 3
        System.out.println(new M_2501_LongestSquareStreakInAnArray().longestSquareStreak(new int[]{2, 3, 5, 6, 7})); // -1
    }

    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);
        int result = 0;

        Map<Long, Integer> map = new HashMap<>();
        for (int num : nums) {
            // If num is already in map, it means it's the square of a previous number in the streak, so curStreak is set to the stored streak length + 1
            int curStreak = map.containsKey((long) num) ? map.get((long) num) + 1 : 1;

            // put new curStreak and update max
            map.put((long) num * num, curStreak);
            result = Math.max(result, curStreak);
        }
        return result < 2 ? -1 : result;
    }
}
