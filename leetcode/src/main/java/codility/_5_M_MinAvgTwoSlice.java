package codility;

public class _5_M_MinAvgTwoSlice {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {-3, -5, -8, -4, -10}));
    }

    /**
     * Idea: Iterate through the array and calculate the average of two and three consecutive elements.
     * why? Because the minimum average slice can only be of size 2 or 3.
     * -------------------
     * TC: O(N)
     * SC: O(1)
     */
    public static int solution(int[] A) {
        // Implement your solution here
        int n = A.length;
        int minAvgIndex = 0;
        double minAvg = Integer.MAX_VALUE;

        for (int i = 0; i < n - 2; ++i) {
            double curAvg2 = (double) (A[i] + A[i + 1]) / 2;
            double curAvg3 = (double) (A[i] + A[i + 1] + A[i + 2]) / 3;
            double curAvg = Math.min(curAvg2, curAvg3);

            if (curAvg < minAvg) {
                minAvg = curAvg;
                minAvgIndex = i;
            }
        }

        if ((double) (A[n - 2] + A[n - 1]) / 2 < minAvg) {
            minAvgIndex = n - 2;
        }

        return minAvgIndex;
    }
}
