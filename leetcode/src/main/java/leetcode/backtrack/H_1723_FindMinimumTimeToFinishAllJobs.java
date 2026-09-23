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

        backtrack(0, new int[k]); // Array to store the total time assigned to each worker
        return res;
    }

    private void backtrack(int index, int[] workerLoads) {
        if (index == jobs.length) {
            int maxWorkerTime = 0;
            for (int time : workerLoads)
                maxWorkerTime = Math.max(maxWorkerTime, time);
            res = Math.min(res, maxWorkerTime);
            return;
        }

        for (int i = 0; i < k; ++i) {
            // Prune: skip if the current worker has the same load as the previous worker
            if (i > 0 && workerLoads[i] == workerLoads[i - 1]) continue;
            // Early termination if the current worker's load exceeds minMaxTime
            if (workerLoads[i] + jobs[index] >= res) continue;

            workerLoads[i] += jobs[index];
            backtrack(index + 1, workerLoads);
            workerLoads[i] -= jobs[index];
        }
    }
}
