package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * How to avoid triple ranges overlap
 */
public class M_731_MyCalendarII {

    private final List<int[]> singleBookings;
    private final List<int[]> doubleBookings;

    public M_731_MyCalendarII() {
        this.singleBookings = new ArrayList<>();
        this.doubleBookings = new ArrayList<>();
    }

    /**
     * Idea: maintain 2 list, single bookings for storing valid bookings
     * and double bookings for checking 2 overlap ranges
     * ---
     * TC: O(N)
     * SC: O(2N)
     */
    public boolean book(int startTime, int endTime) {
        for (int[] booking : doubleBookings) {
            int lo = Math.max(startTime, booking[0]);
            int hi = Math.min(endTime, booking[1]);
            if (lo < hi) return false;
        }

        for (int[] booking : singleBookings) {
            int lo = Math.max(startTime, booking[0]);
            int hi = Math.min(endTime, booking[1]);

            if (lo < hi) doubleBookings.add(new int[]{lo, hi});
        }
        singleBookings.add(new int[]{startTime, endTime});
        return true;
    }
}
