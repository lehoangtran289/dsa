package leetcode.array.array2d;

public class M_1975_MaximumMatrixSum {
    public static void main(String[] args) {
        System.out.println(maxMatrixSum(new int[][]{
                {2,9,3},
                {5,4,-4},
                {1,7,1}}
        )); // 34
        System.out.println(maxMatrixSum(new int[][]{{1,2,3},{-1,-2,-3},{1,2,3}})); // 16
    }

    public static long maxMatrixSum(int[][] matrix) {
        long total = 0;
        int minVal = Integer.MAX_VALUE;
        int countNeg = 0;
        boolean is0Exist = false;

        for (int[] row : matrix) {
            for (int val : row) {
                total += Math.abs(val);
                minVal = Math.min(minVal, Math.abs(val));

                if (val == 0) is0Exist = true;
                if (!is0Exist && val < 0) countNeg++;
            }
        }

        return is0Exist || countNeg % 2 == 0 ? total : total - minVal * 2L;
    }
}
