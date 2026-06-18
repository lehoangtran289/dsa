package leetcode.array.array2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_311_SparseMatrixMultiplication {
    public static void main(String[] args) {
        M_311_SparseMatrixMultiplication sol = new M_311_SparseMatrixMultiplication();

        int[][] mat1 = {{1, 0}, {0, 0}, {4, 0}};
        int[][] mat2 = {{1, 0, 1}, {2, 0, 0}};
        int[][] res = sol.multiply2(mat1, mat2);
        for (int[] row : res) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    /**
     * ---------------------------------------------------------------
     * Naive approach: matrix multiplication
     * ---------------------------------------------------------------
     * TC: O(m * n * k)
     */
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;
        int[][] res = new int[m][n];

        for (int row = 0; row < m; ++row) {
            for (int index = 0; index < k; ++index) {
                if (mat1[row][index] == 0) continue;

                for (int col = 0; col < n; ++col) {
                    if (mat2[index][col] == 0) continue;
                    res[row][col] += mat1[row][index] * mat2[index][col];
                }
            }
        }

        return res;
    }

    /**
     * ---------------------------------------------------------------
     * Using hashmap to store non-empty rows
     * ---------------------------------------------------------------
     * TC:
     */
    public int[][] multiply2(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int n = mat2[0].length;

        // get non-empty rows
        // TC: O(m * k + k * n)
        Map<Integer, List<Integer>> nonEmptyRows1 = getNonEmptyRows(mat1);
        Map<Integer, List<Integer>> nonEmptyRows2 = getNonEmptyRows(mat2);

        int[][] res = new int[m][n];
        for (int row1 : nonEmptyRows1.keySet()) { // O(m)
            for (int col1 : nonEmptyRows1.get(row1)) { // O(k)
                for (int col2 : nonEmptyRows2.get(col1)) { // O(n)
                    res[row1][col2] += mat1[row1][col1] * mat2[col1][col2];
                }
            }
        }

        return res;
    }

    private Map<Integer, List<Integer>> getNonEmptyRows(int[][] mat) {
        Map<Integer, List<Integer>> res = new HashMap<>();

        for (int i = 0; i < mat.length; ++i) {
            res.put(i, new ArrayList<>());
            for (int j = 0; j < mat[0].length; ++j) {
                if (mat[i][j] != 0) {
                    res.get(i).add(j);
                }
            }
        }

        return res;
    }
}
