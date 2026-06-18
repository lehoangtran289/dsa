package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * Returns the list of free time intervals common to all employees.
 */
public class H_759P_EmployeeFreeTime {

    /**
     * Merge intervals
     * ---
     * <p>TC:  O(N log N) — sorting N total intervals dominates.<br>
     * SC: O(N) — for the flattened and merged interval lists.
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // Flattens a per-employee schedule into a single interval list.
        List<Interval> allIntervals = new ArrayList<>();
        for (List<Interval> employeeSchedule : schedule) {
            allIntervals.addAll(employeeSchedule);
        }
        if (allIntervals.isEmpty()) return new ArrayList<>();

        allIntervals.sort((a, b) -> Integer.compare(a.start, b.start));

        // Merges overlapping intervals
        List<Interval> merged = new ArrayList<>();
        merged.add(allIntervals.get(0));

        for (Interval current : allIntervals) {
            Interval last = merged.get(merged.size() - 1);
            if (current.start <= last.end) {
                last.end = Math.max(last.end, current.end);
            } else {
                merged.add(current);
            }
        }

        // Extracts the gaps between consecutive merged intervals as free-time slots.
        List<Interval> freeTime = new ArrayList<>();
        for (int i = 1; i < merged.size(); i++) {
            freeTime.add(new Interval(merged.get(i - 1).end, merged.get(i).start));
        }
        return freeTime;
    }

    static class Interval {
        int start;
        int end;

        Interval() {
        }

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
