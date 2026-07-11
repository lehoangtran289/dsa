package leetcode.array.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class M_3532_PathExistenceQueriesInAGraphI {

    /**
     * binary search
     * ---
     * TC: O(n + q log n) where n is the number of nodes and q is the number of queries
     * SC: O(n)
     */
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        List<Integer> intervalStarts = new ArrayList<>();
        intervalStarts.add(0);

        for (int i = 1; i < n; ++i) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                intervalStarts.add(i);
            }
        }

        boolean[] res = new boolean[queries.length];
        int index = 0;

        for (int[] q : queries) {
            int interval0 = findInterval(nums, intervalStarts, q[0]);
            int interval1 = findInterval(nums, intervalStarts, q[1]);

            res[index++] = interval0 == interval1;
        }

        return res;
    }

    private int findInterval(int[] nums, List<Integer> starts, int target) {
        int res = 0;
        int l = 0, r = starts.size() - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // Check if the start of the interval at mid is less than or equal to the target
            if (nums[starts.get(mid)] <= nums[target]) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }
}
