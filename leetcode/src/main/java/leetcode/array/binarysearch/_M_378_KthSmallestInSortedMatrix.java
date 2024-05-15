package leetcode.array.binarysearch;

import java.util.PriorityQueue;

public class _M_378_KthSmallestInSortedMatrix {
    public static void main(String[] args) {
        _M_378_KthSmallestInSortedMatrix obj = new _M_378_KthSmallestInSortedMatrix();
        int[][] matrix = new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        System.out.println(obj.kthSmallest_BinarySearch(matrix, k)); // 13
    }

    public int kthSmallest_MaxHeap(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; ++j) {
                maxHeap.offer(ints[j]);
                if (maxHeap.size() > k) maxHeap.poll();
            }
        }
        return maxHeap.poll();
    }

    /**
     * Binary Search approach.
     * Find mid value in matrix, count the number of elements less than or equal to mid.
     */
    public int kthSmallest_BinarySearch(int[][] matrix, int k) {
        int lo = matrix[0][0];
        int hi = matrix[matrix.length - 1][matrix[0].length - 1];

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = countLessEqual(matrix, mid);
            if (count >= k) {
                hi = mid - 1;    // try to looking for a smaller value in the left side
            } else {
                lo = mid + 1;    // try to looking for a bigger value in the right side
            }
        }
        return lo;
    }

    public int countLessEqual(int[][] matrix, int x) {
        int cnt = 0, c = matrix.length - 1;             // start with the rightmost column

        for (int r = 0; r < matrix[0].length; ++r) {    // traverse each row to count the number of elements less than or equal to x
            while (c >= 0 && matrix[r][c] > x) --c;     // decrease column until matrix[r][c] <= x
            cnt += (c + 1);                             // + 1 because c is 0-based index
        }
        return cnt;
    }
}
