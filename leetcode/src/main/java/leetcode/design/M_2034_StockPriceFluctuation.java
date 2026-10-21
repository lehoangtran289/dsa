package leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class M_2034_StockPriceFluctuation {

    private int latestTimestamp;
    private final Map<Integer, Integer> priceHistory;
    private final TreeMap<Integer, Integer> priceCounts;

    /**
     * SC: O(N), N current prices
     */
    public M_2034_StockPriceFluctuation() {
        this.priceHistory = new HashMap<>();
        this.priceCounts = new TreeMap<>();
    }

    /**
     * TC: O(log N), N current prices
     */
    public void update(int timestamp, int price) {
        int prevPrice = priceHistory.getOrDefault(timestamp, -1);
        priceHistory.put(timestamp, price);

        // handle previous price
        if (priceCounts.containsKey(prevPrice)) {
            priceCounts.put(prevPrice, priceCounts.get(prevPrice) - 1);
            if (priceCounts.get(prevPrice) == 0) priceCounts.remove(prevPrice);
        }

        priceCounts.put(price, priceCounts.getOrDefault(price, 0) + 1);

        // update latest timestamp
        latestTimestamp = Math.max(latestTimestamp, timestamp);
    }

    /**
     * TC: O(1)
     */
    public int current() {
        return priceHistory.get(latestTimestamp);
    }

    /**
     * TC: O(1)
     */
    public int maximum() {
        return priceCounts.lastKey();
    }

    /**
     * TC: O(1)
     */
    public int minimum() {
        return priceCounts.firstKey();
    }
}
