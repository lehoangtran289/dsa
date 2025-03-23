package contest.weekly442;

public class Q4 {
    public static void main(String[] args) {
        System.out.println(minOperations(new int[][]{{1, 2}, {3, 4}})); // 3
    }

    /**
     * we can group numbers based on the no. of operations they need.
     * All numbers in [1, 3] (i.e., from 4⁰ to 4¹ - 1) require 1 division.
     * All numbers in [4, 15] (i.e., from 4¹ to 4² - 1) require 2 divisions.
     * All numbers in [16, 63] (i.e., from 4² to 4³ - 1) require 3 divisions.
     * <p>
     * 4^17 > Integer.MAX_VALUE
     */
    public static long minOperations(int[][] queries) {
        long res = 0;

        for (int[] q : queries) {
            int start = q[0];
            int end = q[1];
            long ops = 0;

            // find interval of power of 4 that [start, end] intersect
            int divisions = 1;
            int step = 0;
            while (step <= 16) {
                // check current interval of power of 4 [curLow, curHigh]
                int curLow = (int) Math.pow(4, step);
                int curHigh = curLow * 4 - 1;

                // check if [start, end] intersect with this interval
                int low = Math.max(start, curLow);
                int high = Math.min(end, curHigh);
                if (high >= low) {
                    ops += (long) (high - low + 1) * divisions;
                }

                divisions++;
                step++;
            }
            res += (ops + 1) / 2;
        }

        return res;
    }
}
