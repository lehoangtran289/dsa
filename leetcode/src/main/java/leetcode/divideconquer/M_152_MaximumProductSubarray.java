package leetcode.divideconquer;

public class M_152_MaximumProductSubarray {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-2, 3, -4})); // 24
    }

    public static int maxProduct(int[] nums) {
        return maxProduct(nums, 0, nums.length - 1);
    }

    public static int maxProduct(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo];
        int mid = (hi + lo) / 2;
        int lProd = maxProduct(nums, lo, mid);
        int rProd = maxProduct(nums, mid + 1, hi);
        int cross = crossProduct(nums, lo, hi, mid);
        return Math.max(Math.max(lProd, rProd), cross);
    }

    private static int crossProduct(int[] nums, int lo, int hi, int mid) {
        // since product of two negative numbers is positive, we need to keep track of both max and min

        int lProd = 1;
        int lmax = Integer.MIN_VALUE;
        int lmin = Integer.MAX_VALUE;
        for (int i = mid; i >= lo; i--) {
            lProd *= nums[i];
            lmax = Math.max(lmax, lProd);
            lmin = Math.min(lmin, lProd);
        }

        int rProd = 1;
        int rmax = Integer.MIN_VALUE;
        int rmin = Integer.MAX_VALUE;
        for (int i = mid + 1; i <= hi; i++) {
            rProd *= nums[i];
            rmax = Math.max(rProd, rmax);
            rmin = Math.min(rmin, rProd);
        }
        return Math.max(Math.max(lmax * rmax, lmin * rmin), Math.max(lmax * rmin, lmin * rmax));
    }

}
