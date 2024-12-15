package leetcode.array.heap;

import java.util.PriorityQueue;

public class M_1792_MaximumAveragePassRatio {
    public static void main(String[] args) {
        int[][] classes = {{2, 4}, {3, 9}, {4, 5}, {2, 10}};
        System.out.println(maxAverageRatio(classes, 4));
    }

    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(calculateGain(b[0], b[1]), calculateGain(a[0], a[1]))
        );

        for (int[] clazz : classes) {
            pq.add(new int[]{clazz[0], clazz[1]});
        }

        // distributed extra students
        while (extraStudents-- > 0 && !pq.isEmpty()) {
            int[] clazz = pq.poll();
            pq.add(new int[]{clazz[0] + 1, clazz[1] + 1});
        }

        // calculate final pass ratio
        double res = 0;
        while (!pq.isEmpty()) {
            int[] clazz = pq.poll();
            res += (double) clazz[0] / clazz[1];
        }

        return res / classes.length;
    }

    private static double calculateGain(int passes, int total) {
        return (double) (passes + 1) / (total + 1) - (double) passes / total;
    }
}
