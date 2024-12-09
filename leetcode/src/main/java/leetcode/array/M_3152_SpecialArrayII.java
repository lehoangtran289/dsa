package leetcode.array;

import java.util.Arrays;

public class M_3152_SpecialArrayII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(isArraySpecial(new int[]{3, 4, 1, 2, 6}, new int[][]{{0, 4}})));
        System.out.println(Arrays.toString(isArraySpecial(new int[]{4, 3, 1, 6}, new int[][]{{0, 2}, {2, 3}})));
    }

    public static boolean[] isArraySpecial(int[] nums, int[][] queries) {
        boolean[] res = new boolean[queries.length];

        int[] prefix = new int[nums.length];
        prefix[0] = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (isParity(nums[i], nums[i - 1])) {
                prefix[i] = prefix[i - 1];
            } else {
                prefix[i] = prefix[i - 1] + 1;
            }
        }

        for (int i = 0; i < queries.length; ++i) {
            int start = queries[i][0];
            int end = queries[i][1];
            res[i] = prefix[end] - prefix[start] == 0;
        }

        return res;
    }

    private static boolean isParity(int n1, int n2) {
        return n1 % 2 != n2 % 2;
    }
}
