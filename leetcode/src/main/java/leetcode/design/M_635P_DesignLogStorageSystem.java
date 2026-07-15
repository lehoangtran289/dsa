package leetcode.design;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class M_635P_DesignLogStorageSystem {

    private final List<String> units = List.of("Year", "Month", "Day", "Hour", "Minute", "Second");
    private final String[] lowValues = {"0000", "01", "01", "00", "00", "00"};
    private final String[] highValues = {"9999", "12", "31", "23", "59", "59"};

    private final TreeMap<String, List<Integer>> timestampToIds;

    public M_635P_DesignLogStorageSystem() {
        this.timestampToIds = new TreeMap<>();
    }

    /**
     * TC: O(log(n)) ~ n = # of timestamps
     * SC: O(1)
     */
    public void put(int id, String timestamp) {
        timestampToIds.putIfAbsent(timestamp, new ArrayList<>());
        timestampToIds.get(timestamp).add(id);
    }

    /**
     * TC: O(log(n) + k) ~ n = # of timestamps, k = # of ids in the range
     * SC: O(k)
     */
    public List<Integer> retrieve(String start, String end, String granularity) {
        List<Integer> res = new ArrayList<>();

        int configIdx = units.indexOf(granularity);
        if (configIdx == -1) return res;

        String[] startParts = start.split(":");
        String[] endParts = end.split(":");

        for (int i = configIdx + 1; i < startParts.length; ++i) {
            startParts[i] = lowValues[i];
            endParts[i] = highValues[i];
        }

        String startTime = String.join(":", startParts);
        String endTime = String.join(":", endParts);

        for (var entry : timestampToIds.subMap(startTime, true, endTime, true).entrySet()) {
            res.addAll(entry.getValue());
        }
        return res;
    }
}
