package codility;

import java.util.ArrayList;
import java.util.List;

public class _10_M_Flags {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2})); // 3
    }

    /**
     * Find the maximum number of flags that can be set on peaks.
     * Idea: Binary search on the number of flags.
     * ---
     * TC: O(n)
     * SC: O(n)
     */
    public static int solution(int[] A) {
        int n = A.length;
        if (n <= 2) return 0;

        // find peaks
        List<Integer> peaks = new ArrayList<>();
        for (int i = 1; i < n - 1; ++i) {
            if (A[i - 1] < A[i] && A[i] > A[i + 1]) peaks.add(i);
        }
        if (peaks.size() <= 1) return peaks.size();

        // binary search on the number of flags
        int res = 0;
        int l = 0, r = n / 2;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(peaks, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    private static boolean isValid(List<Integer> peaks, int flags) {
        int dist = flags;
        int count = 1;
        int prevPeak = peaks.get(0);

        for (int i = 1; i < peaks.size(); ++i) {
            int curPeak = peaks.get(i);

            if (curPeak - prevPeak >= dist) {
                count++;
                prevPeak = curPeak;
            }

            if (count == flags) return true;
        }

        return false;
    }
}
