package leetcode.dp;

import java.util.Arrays;


public class M_1066_CampusBikesII {

    /**
     * Idea: DP + Bitmask (for checking if bike is assigned)
     * -----------------------
     * DP complexity analysis:
     * N is the number of workers, and M is the number of bikes
     * TC: O(M⋅2^M)
     *      Time complexity = # unique states in memo * average time that the dp function takes.
     *          - states = 2^M
     *          - DP function takes O(M)
     * SC: O(2^M)
     */
    private int[] memo = new int[1024]; // maximum 10 bikes -> 10 bits needed -> 1024 'mask' values
    private int[][] workers;
    private int[][] bikes;

    public int assignBikes(int[][] workers, int[][] bikes) {
        this.workers = workers;
        this.bikes = bikes;
        Arrays.fill(memo, -1);

        return dp(0, 0); // workerIndex, mask
    }

    private int dp(
            int workerIndex,
            int mask
    ) {
        // base case
        if (memo[mask] != -1) return memo[mask];
        if (workerIndex >= workers.length) return 0;

        // process each bike with this worker
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < bikes.length; ++i) {
            // check with mask if bike is assigned
            if ((mask & (1 << i)) != 0) continue;

            int curDist = getDist(workers[workerIndex], bikes[i]);
            int nextMask = mask | (1 << i); // assign bike to workerIndex

            minDist = Math.min(
                    minDist,
                    curDist + dp(workerIndex + 1, nextMask)
            );
        }

        return memo[mask] = minDist;
    }

    private int getDist(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}
