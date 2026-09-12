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

        // init intervals array and sort by right ASC
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < intervalList.size(); ++i) {
            List<Integer> interval = intervalList.get(i);
            intervals[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.right, b.right));

        // dp[i][j] = maximum score using the first i sorted intervals, picking at most j of them
        // dp[0][j] = 0 for all j (no intervals considered yet)
        long[][] dp = new long[n + 1][MAX_INTERVALS + 1];
        List<Integer>[][] chosen = new List[n + 1][MAX_INTERVALS + 1];

        for (int j = 0; j <= MAX_INTERVALS; ++j) {
            chosen[0][j] = new ArrayList<>();
        }

        for (int i = 1; i <= n; ++i) {
            Interval interval = intervals[i - 1];
            int prevInterval = getPreviousInterval(intervals, interval.left);
            int p = prevInterval + 1;

            chosen[i][0] = new ArrayList<>();
            for (int j = 1; j <= MAX_INTERVALS; ++j) {
                // option1 : skip interval i - 1
                long skip = dp[i - 1][j];
                List<Integer> skipList = chosen[i - 1][j];

                // option2: take interval i - 1
                long take = dp[p][j - 1] + interval.weight;

                List<Integer> takeList = insertSorted(chosen[p][j - 1], interval.originalId);
                boolean useTake = take > skip || (take == skip && isLexSmaller(takeList, skipList));

                if (useTake) {
                    dp[i][j] = take;
                    chosen[i][j] = takeList;
                } else {
                    dp[i][j] = skip;
                    chosen[i][j] = skipList;
                }
            }
        }

        return toArray(chosen[n][MAX_INTERVALS]);
    }

    /**
     * Get index of latest interval with its right strictly < endBoundary
     */
    private int getPreviousInterval(Interval[] intervals, int endBoundary) {
        int res = -1;
        int l = 0, r = intervals.length - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (intervals[mid].right < endBoundary) {
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
    private List<Integer> insertSorted(List<Integer> base, int value) {
        List<Integer> res = new ArrayList<>();

        int i = 0;
        while (i < base.size() && base.get(i) <= value) res.add(base.get(i++));
        res.add(value);
        while (i < base.size()) res.add(base.get(i++));

        return res;
    }

    /**
     * Compare if a is lexicographically smaller than b
     */
    private boolean isLexSmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (a.get(i) != b.get(i)) return a.get(i) < b.get(i);
        }
        return a.size() < b.size();
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
