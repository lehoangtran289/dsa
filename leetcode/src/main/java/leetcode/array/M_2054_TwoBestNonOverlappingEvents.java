package leetcode.array;

import java.util.Arrays;

public class M_2054_TwoBestNonOverlappingEvents {
    public static void main(String[] args) {
        int[][] events = {{1, 3, 2}, {4, 5, 2}, {2, 4, 3}};
        System.out.println(maxTwoEvents(events));
    }

    public static int maxTwoEvents(int[][] events) {
        int n = events.length;

        //Sort the array in ascending start time order -----------------------------------------------------------------
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        // create suffix array of value --------------------------------------------------------------------------------
        int[] suffixValue = new int[n];
        suffixValue[n - 1] = events[n - 1][2];
        for (int i = n - 2; i >= 0; --i) {
            suffixValue[i] = Math.max(suffixValue[i + 1], events[i][2]);
        }

        int max = 0;
        for (int i = 0; i < n; ++i) {
            int curEnd = events[i][1];
            int curValue = events[i][2];

            // find event that has start time after curEnd using Binary search
            int l = i + 1, r = n - 1;
            int nextEventIdx = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (events[mid][0] > curEnd) {
                    nextEventIdx = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            if (nextEventIdx != -1) {
                max = Math.max(max, suffixValue[nextEventIdx] + curValue);
            }
            max = Math.max(max, curValue);
        }

        return max;
    }
}
