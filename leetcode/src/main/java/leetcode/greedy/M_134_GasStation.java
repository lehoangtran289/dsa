package leetcode.greedy;

public class M_134_GasStation {
    static void main() {
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }

    /**
     * Idea: if you start from station i and stuck at j, then you can't get to j from any station in [i, j]
     * => then you try to start at station j + 1
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGain = 0;
        int curGain = 0;
        int res = 0;

        for (int i = 0; i < gas.length; ++i) {
            curGain += gas[i] - cost[i];
            totalGain += gas[i] - cost[i];

            // if we cannot get to this station, skip and start at next station
            // since a solution is guaranteed in case it is valid -> i + 1 < n, else the whole segment is invalid
            if (curGain < 0) {
                res = i + 1;
                curGain = 0;
            }
        }

        return totalGain < 0 ? -1 : res;
    }
}
