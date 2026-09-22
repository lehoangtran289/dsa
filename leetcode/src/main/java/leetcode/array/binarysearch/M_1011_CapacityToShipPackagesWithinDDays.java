package leetcode.array.binarysearch;

public class M_1011_CapacityToShipPackagesWithinDDays {

    /**
     * Idea: Binary search on result
     * ---
     * TC: O(n logn)
     * SC: O(1)
     */
    public int shipWithinDays(int[] weights, int days) {
        int res = 0;
        int maxWeight = 0, totalWeight = 0;

        for (int weight : weights) {
            maxWeight = Math.max(maxWeight, weight);
            totalWeight += weight;
        }

        int l = maxWeight, r = totalWeight;
        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (isValid(weights, mid, days)) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }

    private boolean isValid(int[] weights, int capacity, int days) {
        int targetDays = 0;
        int curCapacity = 0;

        for (int weight : weights) {
            curCapacity += weight;

            if (curCapacity > capacity) {
                curCapacity = weight;
                targetDays++;
            }
        }
        targetDays++;

        return targetDays <= days;
    }
}
