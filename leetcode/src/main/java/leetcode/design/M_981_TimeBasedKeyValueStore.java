package leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_981_TimeBasedKeyValueStore {

    private final Map<String, List<Item>> timeMap;

    public M_981_TimeBasedKeyValueStore() {
        this.timeMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        timeMap.putIfAbsent(key, new ArrayList<>());
        timeMap.get(key).add(new Item(timestamp, value));
    }

    public String get(String key, int timestamp) {
        if (!timeMap.containsKey(key)) return "";

        return binarySearch(timeMap.get(key), timestamp);
    }

    private String binarySearch(List<Item> list, int target) {
        String result = "";

        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (list.get(mid).timestamp() <= target) {
                result = list.get(mid).value();
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return result;
    }

    public record Item(int timestamp, String value) {
    }
}
