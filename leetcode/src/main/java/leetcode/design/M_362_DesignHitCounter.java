package leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * TC: O(1) for hit() and O(log n) for getHits() due to binary search.
 */
public class M_362_DesignHitCounter {

    private final List<Integer> timestamps;

    public M_362_DesignHitCounter() {
        this.timestamps = new ArrayList<>();
    }

    public void hit(int timestamp) {
        timestamps.add(timestamp);
    }

    public int getHits(int timestamp) {
        int lastIndex = binarySearch(timestamp - 300);

        // if no valid timestamp found -> return array size ~ hitCount = 0
        if (lastIndex == -1) lastIndex = timestamps.size();

        return timestamps.size() - lastIndex;
    }

    /**
     * Find smallest timestamp that > target
     */
    private int binarySearch(int target) {
        int res = -1;
        int l = 0, r = timestamps.size() - 1;

        while (l <= r) {
            int mid = r - (r - l) / 2;

            if (timestamps.get(mid) > target) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }
}
