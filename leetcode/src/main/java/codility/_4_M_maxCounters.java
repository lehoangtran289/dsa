package codility;

import java.util.Arrays;

public class _4_M_maxCounters {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{3, 4, 4, 6, 1, 4, 4}))); // [3, 2, 2, 4, 2]
    }

    public static int[] solution(int N, int[] A) {
        // Implement your solution here
        int n = A.length;
        int[] res = new int[N];
        int floor = 0;
        int curMax = 0;

        for (int i = 0; i < n; ++i) {
            int index = A[i] - 1;

            if (1 <= A[i] && A[i] <= N) {
                res[index] = Math.max(res[index], floor); // Ensure value is at least the floor
                res[index]++; // do the increment

                curMax = Math.max(curMax, res[index]); // maintain current max
            } else if (A[i] == N + 1) {
                floor = curMax; // update floor to curMax when op = N + 1
            }
        }

        // Ensure all values are at least the floor
        for (int i = 0; i < N; ++i) {
            if (res[i] < floor) res[i] = floor;
        }

        return res;
    }
}
