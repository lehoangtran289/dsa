package leetcode.array.prefixSum;

public class M_1094_CarPooling {

    /**
     * Difference Array
     * Idea:
     * - Use a difference array to track the number of passengers getting on and off at each location
     * - Iterate through the difference array to check if the capacity is exceeded at any point
     * ----------------------------------
     * TC: O(n + m) where n is the number of trips and m is the range of locations (up to 1000)
     * SC: O(m) for the difference array
     */
    public boolean carPooling(int[][] trips, int capacity) {
        final int MAX = 1002;
        int[] diff = new int[MAX];

        for (int[] trip : trips) {
            int from = trip[1], to = trip[2], numPassengers = trip[0];
            diff[from] += numPassengers;
            diff[to] -= numPassengers;
        }

        int curCapacity = 0;
        for (int num : diff) {
            curCapacity += num;

            if (curCapacity > capacity) return false;
        }

        return true;
    }
}
