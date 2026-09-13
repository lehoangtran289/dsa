package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class H_3414_MaximumScoreOfNonOverlappingIntervals {

    private static final int MAX_INTERVALS = 4;

    /**
     * Idea: Use dp[i][j] to find maximum score using first i sorted intervals, picking at most j intervals
     * Keep original Ids in states to reconstruct result path later.
     * ---
     * TC: O(n logn + n * 5), n = number of intervals
     */
    public int[] maximumWeight(List<List<Integer>> intervalList) {
        int n = intervalList.size();

        // init interval array and sort by end time ASC
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; ++i) {
            List<Integer> interval = intervalList.get(i);
            intervals[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.right, b.right));

        // init dp states, dp[i][j] = max weights for considering first i intervals, with maximum j intervals chosen
        long[][] dp = new long[n + 1][MAX_INTERVALS + 1];

        // store indices of chosen intervals at certain state
        List<Integer>[][] chosen = new List[n + 1][MAX_INTERVALS + 1];

        for (int j = 0; j <= MAX_INTERVALS; ++j) {
            chosen[0][j] = new ArrayList<>();
        }

        for (int i = 1; i <= n; ++i) {
            Interval curInterval = intervals[i - 1];
            int prevIntervalId = getPreviousInterval(intervals, curInterval.left);
            chosen[i][0] = new ArrayList<>();

            for (int j = 1; j <= MAX_INTERVALS; ++j) {
                // option 1: skip
                long skip = dp[i - 1][j];
                List<Integer> skipList = chosen[i - 1][j];

                // option 2: take
                long take = dp[prevIntervalId + 1][j - 1] + curInterval.weight;
                List<Integer> takeList = insertSorted(chosen[prevIntervalId + 1][j - 1], curInterval.originalId);

                // update dp states & chosen path
                if (skip > take) {
                    dp[i][j] = skip;
                    chosen[i][j] = skipList;
                } else if (skip < take) {
                    dp[i][j] = take;
                    chosen[i][j] = takeList;
                } else { // in tie case -> chose lexico smaller list
                    dp[i][j] = take;
                    chosen[i][j] = isLexicoSmaller(skipList, takeList) ? skipList : takeList;
                }
            }
        }

        return toArray(chosen[n][MAX_INTERVALS]);
    }

    /**
     * Get index of latest interval with its right strictly < right Boundary
     */
    private int getPreviousInterval(Interval[] intervals, int rightBoundary) {
        int res = -1;
        int l = 0, r = intervals.length - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (intervals[mid].right < rightBoundary) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    /**
     * Insert an element into sorted list
     */
    private List<Integer> insertSorted(List<Integer> list, int element) {
        List<Integer> res = new ArrayList<>();

        int i = 0;
        while (i < list.size() && list.get(i) < element) res.add(list.get(i++));
        res.add(element);
        while (i < list.size()) res.add(list.get(i++));

        return res;
    }

    /**
     * Compare if a is lexicographically smaller than b
     */
    private boolean isLexicoSmaller(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i < Math.min(list1.size(), list2.size()); ++i) {
            if (list1.get(i) != list2.get(i)) {
                return list1.get(i) < list2.get(i);
            }
        }
        return list1.size() < list2.size();
    }

    private int[] toArray(List<Integer> list) {
        return list.stream().mapToInt(e -> e).toArray();
    }

    static class Interval {
        int left;
        int right;
        int weight;
        int originalId;

        Interval(int left, int right, int weight, int originalId) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.originalId = originalId;
        }
    }
}
