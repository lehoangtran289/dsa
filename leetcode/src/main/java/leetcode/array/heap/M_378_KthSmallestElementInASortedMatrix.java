package leetcode.array.heap;

import java.util.PriorityQueue;

public class M_378_KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        System.out.println(kthSmallest(matrix, k)); // 13
    }
    
    /**
     * Min-heap approach
     * -------------------------- 
     * Keep n pointers -> Use min heap 
     */
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, m = matrix[0].length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);

        // init min(n, k) pointers
        for (int i = 0; i < Math.min(n, k); ++i) {
            minHeap.add(new int[]{i, 0});
        }

        while (!minHeap.isEmpty() && k-- > 1) {
            int[] cur = minHeap.poll();

            if (cur[1] + 1 < m) {
                minHeap.add(new int[]{cur[0], cur[1] + 1});
            }
        }

        int[] res = minHeap.poll();
        return matrix[res[0]][res[1]];
    }
}
