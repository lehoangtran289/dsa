package leetcode.array.array2d;

import java.util.Arrays;

public class M_1727_LargestSubmatrixWithRearrangements {
    public static void main(String[] args) {
        System.out.println(largestSubmatrix(new int[][]{{0, 0, 1}, {1, 1, 1}, {1, 0, 1}})); // 4
    }

    /**
     * Prefix Sum
     * ---
     * TC: O(m * n * log(n)), where m is the number of rows and n is the number of columns in the matrix
     * SC: O(m * n)
     */
    public static int largestSubmatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] ones = new int[rows][cols];

        for (int j = 0; j < cols; ++j) {
            int oneCount = 0;
            for (int i = 0; i < rows; ++i) {
                if (matrix[i][j] == 1) {
                    oneCount++;
                    ones[i][j] = oneCount;
                } else {
                    oneCount = 0;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < rows; ++i) {
            int[] onesRow = ones[i];
            Arrays.sort(onesRow);

            // Traverse from low -> high
            // All cols in [j, cols - 1] have height >= onesRow[j]
            // -> width = cols - j, area = height * width = onesRow[j] * (cols - j)
            for (int j = 0; j < cols; ++j) {
                res = Math.max(res, onesRow[j] * (cols - j));
            }
        }

        return res;
    }
}
