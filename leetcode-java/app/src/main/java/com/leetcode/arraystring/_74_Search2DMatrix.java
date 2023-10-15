package com.leetcode.arraystring;

public class _74_Search2DMatrix {

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, 3));
        System.out.println(searchMatrix(new int[][]{{1}}, 1));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        int low = 0;
        int high = row * col - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midElement = matrix[mid / col][mid % col];
            if (midElement == target)
                return true;
            else if (midElement < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }
}
