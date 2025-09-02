package leetcode.array.array2d;

public class M_3025_FindTheNumberOfWaysToPlacePeopleI {

    /**
     * Simulation - Brute Force
     * -------------
     * Time: O(n^3)
     * Space: O(1)
     */
    public int numberOfPairs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                int[] left = points[i];
                int[] right = points[j];

                // check if A is on the upper left side of B
                if (
                        (left[0] > right[0] && left[1] > right[1])
                        || (left[0] < right[0] && left[1] < right[1])
                ) continue;

                // check if exists any points between A & B rectangle
                boolean isValidPair = true;
                for (int k = 0; k < points.length; ++k) {
                    if (k == i || k == j) continue;
                    int x = points[k][0];
                    int y = points[k][1];

                    if (
                            x >= Math.min(left[0], right[0]) && x <= Math.max(left[0], right[0])
                            && y >= Math.min(left[1], right[1]) && y <= Math.max(left[1], right[1])
                    ) {
                        isValidPair = false;
                        break;
                    }
                }

                if (isValidPair) res++;
            }
        }

        return res;
    }
}
