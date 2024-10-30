package leetcode.dp;

import java.util.Arrays;

public class H_1671_MinimumNumberOfRemovalsToMakeMountainArray {
    public static void main(String[] args) {
//        System.out.println(new H_1671_MinimumNumberOfRemovalsToMakeMountainArray().minimumMountainRemovals(new int[]{1, 3, 1})); // 0
        System.out.println(new H_1671_MinimumNumberOfRemovalsToMakeMountainArray().minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1})); // 3
    }

    public int minimumMountainRemovals(int[] nums) {
        int[] lis = new int[nums.length];
        Arrays.fill(lis, 1);

        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(lis));

        return 0;
    }
}
