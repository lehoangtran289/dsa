package leetcode.array.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class M_973_KClosestPointsToOrigin {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(kClosest(new int[][]{{1, 3}, {-2, 2}}, 1)));
    }

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[0], b[0]));
        for (int i = 0; i < points.length; ++i) {
            pq.add(new double[]{distance(points[i][0], points[i][1]), i});
        }

        int[][] res = new int[k][];
        for (int i = 0; i < k; ++i) {
            double[] poll = pq.poll();
            res[i] = points[(int) poll[1]];
        }
        return res;
    }

    private static double distance(int x, int y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
