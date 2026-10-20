package leetcode.array.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class M_1146_SnapshotArray {

    private int snapId;
    private final List<int[]>[] history;

    /**
     * Idea: Store history as array of bucket, where each bucket store history changes of an element
     */
    public M_1146_SnapshotArray(int length) {
        this.history = new List[length];
        this.snapId = 0;

        for (int i = 0; i < length; ++i) {
            history[i] = new ArrayList<>();
            history[i].add(new int[]{0, 0}); // init element with 0
        }
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    public void set(int index, int val) {
        List<int[]> snapValues = history[index];

        if (snapValues.getLast()[0] != snapId) {
            history[index].add(new int[]{snapId, val});
        } else {
            snapValues.getLast()[1] = val;
        }
    }

    public int snap() {
        return snapId++;
    }

    /**
     * TC: O(logN)
     * SC: O(1)
     */
    public int get(int index, int snap_id) {
        int res = 0;
        List<int[]> snapValues = history[index];
        int l = 0, r = snapValues.size() - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (snapValues.get(mid)[0] <= snap_id) {
                res = snapValues.get(mid)[1];
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }
}
