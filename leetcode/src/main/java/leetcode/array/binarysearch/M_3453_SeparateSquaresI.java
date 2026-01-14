package leetcode.array.binarysearch;

public class M_3453_SeparateSquaresI {

    /**
     * Binary Search
     * Idea:
     * - Use binary search on the y-coordinate to find the height that divides the squares into two equal areas
     * ----------------------------------
     * TC: O(n log m) where n is the number of squares and m is the maximum possible height
     * SC: O(1)
     */
    public double separateSquares(int[][] squares) {
        double maxY = 0;
        double totalArea = 0;

        for (int[] square : squares) {
            totalArea += (double) square[2] * square[2];
            maxY = Math.max(maxY, (double) square[1] + square[2]);
        }
        double targetArea = totalArea / 2;

        double res = 0;
        double l = 0, r = maxY;

        while (Math.abs(l - r) > 1e-5) {
            double mid = r - (r - l) / 2;

            if (isValid(squares, mid, targetArea)) {
                res = mid;
                r = mid;
            } else {
                l = mid;
            }
        }

        return res;
    }

    private boolean isValid(int[][] squares, double targetY, double targetArea) {
        double area = 0;

        for (int[] square : squares) {
            if (square[1] < targetY) {
                area += (double) square[2] * Math.min((double) square[2], targetY - square[1]);
            }
        }

        return area >= targetArea;
    }
}
