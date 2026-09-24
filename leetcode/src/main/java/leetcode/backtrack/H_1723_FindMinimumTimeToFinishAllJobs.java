package leetcode.backtrack;

import java.util.Arrays;

public class H_1723_FindMinimumTimeToFinishAllJobs {

    private int res;
    private int[] jobs;
    private int k;

    /**
     * Idea: backtrack, assign each job to each worker, prune to avoid TLE
     */
    public int minimumTimeRequired(int[] jobs, int k) {
        this.res = 1 << 30;
        this.jobs = jobs;
        this.k = k;
        Arrays.sort(jobs);

        backtrack(jobs.length - 1, new int[k]); // Array to store the total time assigned to each worker
        return res;
    }

    private void backtrack(int index, int[] workerLoads) {
        if (index < 0) {
            int maxLoad = 0;
            for (int time : workerLoads) maxLoad = Math.max(maxLoad, time);
            res = Math.min(res, maxLoad);
            return;
        }

        for (int i = 0; i < k; ++i) {
            // Prune 1: skip if the current worker has the same load as the previous worker
            // This does not cover all duplicate work load assignment case
            if (i > 0 && workerLoads[i] == workerLoads[i - 1]) continue;

            // Prune 2: if the current worker's load exceeds minMaxTime
            if (workerLoads[i] + jobs[index] >= res) continue;

            // backtrack
            workerLoads[i] += jobs[index];
            backtrack(index - 1, workerLoads);
            workerLoads[i] -= jobs[index];
        }
    }
}
