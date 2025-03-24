package leetcode.array.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class M_3169_CountDaysWithoutMeetings {
    public static void main(String[] args) {
        System.out.println(countDays(14, new int[][]{{6, 11}, {7, 13}, {8, 9}, {5, 8}, {3, 13}, {11, 13}, {1, 3}, {5, 10}, {8, 13}, {3, 9}}));
        System.out.println(countDays(8, new int[][]{{3, 4}, {4, 8}, {2, 5}, {3, 8}})); // 1
    }

    /**
     * Merge Intervals => AC
     */
    public static int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        merged.add(meetings[0]);
        for (int[] meeting : meetings) {
            int[] cur = merged.get(merged.size() - 1);
            if (meeting[0] <= cur[1]) {
                cur[1] = Math.max(cur[1], meeting[1]);
            } else {
                merged.add(meeting);
            }
        }

        int meetingDays = 0;
        for (int[] meeting : merged) {
            meetingDays += meeting[1] - meeting[0] + 1;
        }

        return days - meetingDays;
    }

    /**
     * Line sweep. => MLE + TLE
     */
    public static int countDays2(int days, int[][] meetings) {
        int[] diff = new int[days + 1];
        for (int[] m : meetings) {
            diff[m[0]]++;
            if (m[1] < days)
                diff[m[1] + 1]--;
        }

        int res = 0;
        int[] prefixSum = new int[days + 1];
        for (int i = 1; i <= days; i++) {
            prefixSum[i] = prefixSum[i - 1] + diff[i];
            if (prefixSum[i] == 0) {
                res++;
            }
        }

        return res;
    }

    /**
     * Line sweep, optimize space + time
     */
    public static int countDays3(int days, int[][] meetings) {
        int res = 0;
        int prefixSum = 0;
        int prevDay = days + 1;

        TreeMap<Integer, Integer> diff = new TreeMap<>();
        for (int[] m : meetings) {
            prevDay = Math.min(prevDay, m[0]);

            diff.put(m[0], diff.getOrDefault(m[0], 0) + 1);
            diff.put(m[1] + 1, diff.getOrDefault(m[1] + 1, 0) - 1);
        }

        // add dates till first day of meeting
        res += prevDay - 1;

        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            if (prefixSum == 0) {
                res += entry.getKey() - prevDay;
            }
            prefixSum += entry.getValue();
            prevDay = entry.getKey();
        }

        // add remaining date till end
        res += days - prevDay + 1;
        return res;
    }
}
