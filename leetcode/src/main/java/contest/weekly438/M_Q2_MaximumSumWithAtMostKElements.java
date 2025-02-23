package contest.weekly438;

import java.util.PriorityQueue;

public class M_Q2_MaximumSumWithAtMostKElements {
    public static void main(String[] args) {
        System.out.println(maxSum(new int[][]{{5, 1}, {3, 4}}, new int[]{2, 2}, 2));
        System.out.println(maxSum(new int[][]{{5, 3, 7}, {8, 2, 6}}, new int[]{2, 2}, 3));
    }

    public static long maxSum(int[][] grid, int[] limits, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        PriorityQueue<Integer> finalPQ = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < rows; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            for (int j = 0; j < cols; j++) {
                pq.add(grid[i][j]);
            }

            for (int cnt = 0; cnt < limits[i]; cnt++) {
                finalPQ.add(pq.poll());
            }
        }

        long maxSum = 0;
        for (int i = 0; i < k; i++) {
            if (!finalPQ.isEmpty()) maxSum += finalPQ.poll();
        }

        return maxSum;
    }
}
