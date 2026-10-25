package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class M_2365_TaskSchedulerII {

    /**
     * Idea: Maintain a day counter, when we encounter a task that
     * - Hasn't been processed before -> process it and store its next available day
     * - Has been processed before -> check if current day reach its stored next available day
     *      - If yes -> process
     *      - If no -> wait until its next available day
     * ---
     * TC: O(N)
     * SC: O(N)
     */
    public long taskSchedulerII(int[] tasks, int space) {
        long days = 0;
        Map<Integer, Long> nextValidDayMap = new HashMap<>();

        for (int task : tasks) {
            // update current day
            days++;

            // If this task type was executed before, we might need to wait
            days = Math.max(days, nextValidDayMap.getOrDefault(task, 0L));

            // Update when this task type can be executed next
            nextValidDayMap.put(task, days + space + 1);
        }

        return days;
    }
}
