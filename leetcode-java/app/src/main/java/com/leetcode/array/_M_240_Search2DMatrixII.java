package com.leetcode.array;

public class _M_240_Search2DMatrixII {
    public static void main(String[] args) {
        _M_240_Search2DMatrixII search2DMatrixII = new _M_240_Search2DMatrixII();
        System.out.println(search2DMatrixII.searchMatrix(new int[][]{
                new int[]{1, 4, 7, 11, 15},
                new int[]{2, 5, 8, 12, 19},
                new int[]{3, 6, 9, 16, 22},
                new int[]{10, 13, 14, 17, 24},
                new int[]{18, 21, 23, 26, 30}
        }, 5)); // true
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; ++i) {
            if (binarySearch(matrix[i], target)) return true;
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
}
