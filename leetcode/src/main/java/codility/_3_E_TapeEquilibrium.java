package codility;

public class _3_E_TapeEquilibrium {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 2, 4, 3})); // 1
    }

    /**
     * Problem: Find the minimal difference between the sum of two parts when splitting an array.
     * Idea: Calculate total sum
     * ------
     * TC: O(N)
     * SC: O(1)
     */
    public static int solution(int[] A) {
        int total = 0;
        for (int num : A) total += num;

        int res = Integer.MAX_VALUE;
        int curSum = 0;

        for (int i = 0; i < A.length - 1; ++i) {
            curSum += A[i];

            int diff = Math.abs(total - 2 * curSum);
            res = Math.min(res, diff);
        }

        return res;
    }
}
