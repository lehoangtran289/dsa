package leetcode.design;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class M_3433_CountMentionsPerUser {

    /**
     * Simulation
     * ----------------------------------
     * TC: O(m * n) - m is number of events, n is number of users
     * SC: O(n) - arrays to track mention counts and offline status
     */
    public int[] countMentions(int n, List<List<String>> events) {
        final String MSG = "MESSAGE";
        final String ALL = "ALL";
        final String HERE = "HERE";

        int curTime;
        int[] mentionCounts = new int[n];
        boolean[] isOffline = new boolean[n];
        Queue<int[]> offlineCounter = new ArrayDeque<>();

        // sort by timestamp and message type (process OFFLINE first)
        events.sort((a, b) -> {
            int timeA = Integer.parseInt(a.get(1));
            int timeB = Integer.parseInt(b.get(1));
            return timeA != timeB ? timeA - timeB : -a.get(0).compareTo(b.get(0));
        });

        for (List<String> event : events) {
            String type = event.get(0);
            curTime = Integer.parseInt(event.get(1));

            while (!offlineCounter.isEmpty() && offlineCounter.peek()[1] <= curTime) {
                isOffline[offlineCounter.peek()[0]] = false;
                offlineCounter.poll();
            }

            if (type.equals(MSG)) {
                String cmd = event.get(2);

                if (cmd.equals(ALL)) {
                    for (int i = 0; i < n; ++i) {
                        mentionCounts[i]++;
                    }
                } else if (cmd.equals(HERE)) {
                    for (int i = 0; i < n; ++i) {
                        if (!isOffline[i]) mentionCounts[i]++;
                    }
                } else {
                    String[] idStrings = cmd.split(" ");
                    for (String idString : idStrings) {
                        int id = Integer.parseInt(idString.substring(2));
                        mentionCounts[id]++;
                    }
                }
            } else {
                int id = Integer.parseInt(event.get(2));
                isOffline[id] = true;
                offlineCounter.add(new int[]{id, curTime + 60});
            }
        }

        return mentionCounts;
    }
}
