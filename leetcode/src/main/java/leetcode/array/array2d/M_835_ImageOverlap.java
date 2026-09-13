package leetcode.array.array2d;

import leetcode.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class M_835_ImageOverlap {

    /**
     * Idea: count the translation (r1 - r2, c1 - c2) for each pair of 1s.
     * ---
     * TC: O(n^4)
     * SC: O(n^2)
     */
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int res = 0;

        // map translation (r1 - r2, c1 - c2) to its count, meaning that there are <count> number of 1s overlapping between 2 img after translation.
        Map<Pair<Integer, Integer>, Integer> translationCounts = new HashMap<>();

        // match all 1s in img1 with all 1s in img2, and count the translation (r1 - r2, c1 - c2) for each pair of 1s.
        for (int r1 = 0; r1 < n; ++r1) {
            for (int c1 = 0; c1 < n; ++c1) {
                if (img1[r1][c1] != 1) continue;

                for (int r2 = 0; r2 < n; ++r2) {
                    for (int c2 = 0; c2 < n; ++c2) {
                        if (img2[r2][c2] != 1) continue;

                        Pair<Integer, Integer> translation = new Pair<>(r1 - r2, c1 - c2);
                        translationCounts.put(translation, translationCounts.getOrDefault(translation, 0) + 1);
                        res = Math.max(res, translationCounts.get(translation));
                    }
                }
            }
        }
        return res;
    }
}
