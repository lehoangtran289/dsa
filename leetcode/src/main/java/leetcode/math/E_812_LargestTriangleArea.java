package leetcode.math;

public class E_812_LargestTriangleArea {

    /**
     * Brute Force
     * -----------------------
     * TC: O(N^3)
     * SC: O(1)
     */
    public static double largestTriangleArea(int[][] points) {
        double res = 0;
        for (int i = 0; i < points.length - 2; ++i) {
            for (int j = i + 1; j < points.length - 1; ++j) {
                for (int k = j + 1; k < points.length; ++k) {
                    res = Math.max(res, area(points[i], points[j], points[k]));
                }
            }
        }
        return res;
    }

    /**
     * Formula to calculate triangle area given 3 points:
     * 1/2|(𝑥1(𝑦2−𝑦3)+𝑥2(𝑦3−𝑦1)+𝑥3(𝑦1−𝑦2))|
     */
    private static double area(int[] p1, int[] p2, int[] p3) {
        return (double) 1/2 * Math.abs(p1[0] * (p2[1] - p3[1]) + p2[0] * (p3[1] - p1[1]) + p3[0] * (p1[1] - p2[1]));
    }
}
