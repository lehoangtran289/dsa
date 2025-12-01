package leetcode.array.binarysearch;

public class H_2141_MaximumRunningTimeOfNComputers {

    /**
     * Binary Search
     * Idea: check mid is valid running time by summing up min(battery, mid) for all batteries
     * Then compare with mid * n (total required power for n computers)
     * <p>
     * Note: we cannot use extra capacity of a large battery beyond mid for another computer
     * ------------------------
     * TC: O(m log(sum of batteries)), m = batteries.length
     * SC: O(1)
     * ------------------------
     */
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for (int battery : batteries) sum += battery;

        long l = 1, r = sum;
        long res = 0;

        while (l <= r) {
            long mid = r - (r - l) / 2;

            if (isValid(n, batteries, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    private boolean isValid(int n, int[] batteries, long target) {
        long usage = 0;
        for (int battery : batteries) {
            usage += Math.min(battery, target);
        }
        return usage >= target * n;
    }
}
