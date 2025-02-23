package contest.weekly438;

import java.util.ArrayList;
import java.util.List;

// TODO
public class H_Q4_MaximizeTheDistanceBetweenPointsOnASquare {
    public static void main(String[] args) {
        System.out.println(maxDistance(2, new int[][]{{1, 2}, {0, 0}, {2, 0}, {2, 2}, {2, 1}}, 4));
    }

    public static int maxDistance(int side, int[][] points, int k) {
        int l = 0;
        int r = side * 2;
        int minDist = -1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (isValid(points, k, mid)) {
                minDist = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return minDist;
    }

    private static boolean isValid(int[][] points, int k, int minDist) {
        // Greedily select points with at least minDist distance
        List<int[]> selected = new ArrayList<>();
        selected.add(points[0]); // Start with the first point

        for (int i = 1; i < points.length; i++) {
            int[] currentPoint = points[i];
            int[] lastSelectedPoint = selected.get(selected.size() - 1);

            // Check if the Manhattan distance is at least minDist
            if (distance(currentPoint, lastSelectedPoint) >= minDist) {
                selected.add(currentPoint);

                // If we have selected k points, return true
                if (selected.size() == k) {
                    return true;
                }
            }
        }

        // Return true if we have at least k points selected
        return selected.size() >= k;
    }

    private static int distance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
