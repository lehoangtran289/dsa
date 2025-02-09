package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class M_2364_CountNumberOfBadPairs {
    public static void main(String[] args) {
        System.out.println(countBadPairs(new int[]{1, 2, 3, 4, 5}));
    }

    public static long countBadPairs(int[] nums) {
        long n = nums.length;
        if (n == 1) return 0;

        Map<Long, Integer> map = new HashMap<>(); // <num[i] - i, freq>
        long totalGoodPairs = 0;

        for (int i = 0; i < n; ++i) {
            long diff = nums[i] - i;
            map.put(diff, map.getOrDefault(diff, 0) + 1);

            if (map.get(diff) >= 2) {
                long frevDiff = map.get(diff) - 1;
                totalGoodPairs -= frevDiff * (frevDiff - 1) / 2;
                totalGoodPairs += (frevDiff + 1) * frevDiff / 2;
            }
        }

        long totalPairs = n * (n - 1) / 2;
        return totalPairs - totalGoodPairs;
    }
}
