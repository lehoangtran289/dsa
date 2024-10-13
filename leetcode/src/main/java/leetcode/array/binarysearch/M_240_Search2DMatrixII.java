package leetcode.array.binarysearch;

public class M_240_Search2DMatrixII {
    public static void main(String[] args) {
        M_240_Search2DMatrixII search2DMatrixII = new M_240_Search2DMatrixII();
        System.out.println(search2DMatrixII.searchMatrix(new int[][]{
                new int[]{1, 4, 7, 11, 15},
                new int[]{2, 5, 8, 12, 19},
                new int[]{3, 6, 9, 16, 22},
                new int[]{10, 13, 14, 17, 24},
                new int[]{18, 21, 23, 26, 30}
        }, 5)); // true
    }

    /**
     * Brute force each row solution - O(m * log(n))
     * For each row, do binary search.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] arr : matrix) {
            if (binarySearch(arr, target)) return true;
        }
        return false;
    }

    public boolean binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi - 1) {
            int mid = hi - (hi - lo) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] > target) hi = mid;
            else lo = mid;
        }
        return arr[lo] == target;
    }

    /**
     * Optimal solution - O(m + n)
     * Start from top right corner.
     * If target is greater than current element, move down.
     * If target is less than current element, move left.
     */
    public boolean searchMatrixOptimal(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else row++;
        }
        return false;
    }
}
