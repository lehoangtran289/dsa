package leetcode.array.binarysearch;

import java.util.Arrays;

public class M_2064_MinimizedMaximumOfProductsDistributedToAnyStore {
    public static void main(String[] args) {
        System.out.println(minimizedMaximum(1, new int[]{100000}));
    }

    public static int minimizedMaximum(int n, int[] quantities) {
        int res = Integer.MAX_VALUE;

        int l = 1, h = -1;
        for (int quantity : quantities) {
            h = Math.max(h, quantity);
        }

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (isDistributed(n, quantities, mid)) {
                res = Math.min(res, mid);
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return res;
    }

    public static boolean isDistributed(int n, int[] nums, int mid) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < arr.length; ++i) {
            if (n == 0) return false;
            while (arr[i] > 0 && n > 0) {
                arr[i] -= arr[i] >= mid ? mid : arr[i] % mid;
                n--;
            }
        }

        for (int i = arr.length - 1; i >= 0; --i) {
            if (arr[i] != 0) return false;
        }

        return true;
    }
}
