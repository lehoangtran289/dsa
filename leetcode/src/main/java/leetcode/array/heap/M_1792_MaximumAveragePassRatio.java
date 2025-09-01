package leetcode.array.heap;

import java.util.*;

public class M_1792_MaximumAveragePassRatio {
    public static void main(String[] args) {
        int[][] classes = {{2, 4}, {3, 9}, {4, 5}, {2, 10}};
        System.out.println(maxAverageRatio(classes, 4));
    }

    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        Queue<double[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b[2], a[2]));

        double res = 0;
        for (int[] c : classes) {
            maxHeap.add(new double[]{c[0], c[1], calculateGain(c[0], c[1])});
            res += (double) c[0] / c[1];
        }

        while (extraStudents-- > 0) {
            double[] c = maxHeap.poll();

            // update res after adding student
            double gain = calculateGain((int) c[0], (int) c[1]);
            res += gain;

            // update new class
            c[0]++;
            c[1]++;
            c[2] = calculateGain((int) c[0], (int) c[1]);
            maxHeap.add(c);
        }

        return res / classes.length;
    }

    private static double calculateGain(int pass, int total) {
        return (double) (pass + 1) / (total + 1) - (double) pass / total;
    }
}
