package leetcode.dp;

import java.util.Arrays;

public class H_1671_MinimumNumberOfRemovalsToMakeMountainArray {
    public static void main(String[] args) {
//        System.out.println(minimumMountainRemovals(new int[]{1, 3, 1})); // 0
//        System.out.println(minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1})); // 3
        System.out.println(minimumMountainRemovals(new int[]{100, 92, 89, 77, 74, 66, 64, 66, 64})); // 6
    }

    public static int minimumMountainRemovals(int[] nums) {
        int[] lis = new int[nums.length];
        int[] lds = new int[nums.length];
        Arrays.fill(lis, 1);
        Arrays.fill(lds, 1);

        // 1st pass: find LIS (Longest increasing sub)
        for (int i = 1; i < nums.length; ++i)
            for (int j = 0; j < i; ++j)
                if (nums[j] < nums[i])
                    lis[i] = Math.max(lis[i], lis[j] + 1);
//        System.out.println(Arrays.toString(lis));

        // 2nd pass: find LDS (Longest decreasing sub)
        for (int i = nums.length - 2; i >= 0; --i)
            for (int j = nums.length - 1; j >= i; --j)
                if (nums[j] < nums[i])
                    lds[i] = Math.max(lds[i], lds[j] + 1);
//        System.out.println(Arrays.toString(lds));

        int res = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length - 1; ++i)
            if (lis[i] != 1 && lds[i] != 1)
                res = Math.min(res, nums.length - lis[i] - lds[i] + 1);

        return res;
    }
}
