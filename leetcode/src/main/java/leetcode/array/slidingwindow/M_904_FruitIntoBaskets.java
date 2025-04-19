package leetcode.array.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class M_904_FruitIntoBaskets {

    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> freq = new HashMap<>();
        int curCount = 0;
        int l = 0;
        int res = 0;

        for (int r = 0; r < n; ++r) {
            freq.put(fruits[r], freq.getOrDefault(fruits[r], 0) + 1);
            curCount++;

            while (l <= r && freq.size() > 2) {
                freq.put(fruits[l], freq.get(fruits[l]) - 1);
                if (freq.get(fruits[l]) == 0) {
                    freq.remove(fruits[l]);
                }
                curCount--;
                l++;
            }

            res = Math.max(res, curCount);
        }

        return res;
    }
}
