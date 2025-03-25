package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class M_3394_CheckIfGridCanBeCutIntoSections {

    public boolean checkValidCuts(int n, int[][] rectangles) {
        return isAxisValid(true, rectangles)
               || isAxisValid(false, rectangles);
    }

    /**
     * TC: O(nlogn) ~ sort
     * SC: O(m) ~ m = rectangles larger dim
     */
    public static boolean isAxisValid(
            boolean isVertical,
            int[][] rectangles
    ) {
        int[][] axis = new int[rectangles.length][2];
        for (int i = 0; i < rectangles.length; ++i) {
            if (isVertical) {
                axis[i] = new int[]{rectangles[i][0], rectangles[i][2]};
            } else {
                axis[i] = new int[]{rectangles[i][1], rectangles[i][3]};
            }
        }
        Arrays.sort(axis, (a, b) -> a[0] - b[0]);

        // merge intervals
        List<int[]> merged = new ArrayList<>();
        merged.add(axis[0]);

        for (int i = 1; i < axis.length; ++i) {
            int[] cur = merged.get(merged.size() - 1);
            if (cur[1] > axis[i][0]) {
                cur[1] = Math.max(cur[1], axis[i][1]);
            } else {
                merged.add(axis[i]);
            }
        }

        return merged.size() >= 3;
    }
}
