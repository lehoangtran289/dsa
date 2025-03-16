package leetcode.array.binarysearch;

public class M_2594_MinimumTimeToRepairCars {
    /**
     If constraints are large (e.g: 10^5) then DP is not suitable.
     */
    public long repairCars(int[] ranks, int cars) {
        long l = 1, r = (long) cars * cars * maxRank(ranks);

        long res = 1;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (isValid(ranks, cars, mid)) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    /**
     Since cars are repaired simultaneously, we can count number of cars that each mechanic (rank ith) repaired
     # cars = Math.sqrt(time / rank);
     */
    private boolean isValid(int[] ranks, int cars, long minTime) {
        long totalCars = 0;
        for (int rank : ranks) {
            totalCars += Math.sqrt(minTime / rank);
            if (totalCars >= cars) return true;
        }
        return false;
    }

    private int maxRank(int[] ranks) {
        int maxRank = 0;
        for (int rank : ranks) {
            maxRank = Math.max(maxRank, rank);
        }
        return maxRank;
    }
}
