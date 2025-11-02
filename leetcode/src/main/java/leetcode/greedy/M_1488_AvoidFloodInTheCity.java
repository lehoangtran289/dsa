package leetcode.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class M_1488_AvoidFloodInTheCity {

    /**
     * Greedy + TreeSet + HashMap
     * -----------------------
     * TC: O(N log N) - N is the length of rains
     * SC: O(N)
     */
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);

        TreeSet<Integer> noRainDays = new TreeSet<>(); // for binary search + ceiling()
        Map<Integer, Integer> lastRainMap = new HashMap<>(); // <lake, index>

        for (int i = 0; i < n; ++i) {
            if (rains[i] == 0) {
                noRainDays.add(i);
                continue;
            }

            if (!lastRainMap.containsKey(rains[i])) {
                res[i] = -1;
                lastRainMap.put(rains[i], i);
                continue;
            }

            int prevIndex = lastRainMap.get(rains[i]);
            Integer noRainIndex = noRainDays.ceiling(prevIndex);

            if (noRainIndex == null) return new int[]{};

            res[i] = -1;
            res[noRainIndex] = rains[i];
            lastRainMap.put(rains[i], i);
            noRainDays.remove(noRainIndex);
        }

        return res;
    }
}
