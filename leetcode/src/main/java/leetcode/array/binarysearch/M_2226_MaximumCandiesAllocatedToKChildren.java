package leetcode.array.binarysearch;

public class M_2226_MaximumCandiesAllocatedToKChildren {
    public static void main(String[] args) {
        System.out.println(maximumCandies(new int[]{2, 5}, 11));
    }

    public static int maximumCandies(int[] candies, long k) {
        int res = 0;
        int l = 0, r = 0;
        for (int n : candies) r = Math.max(r, n);

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(candies, k, mid)) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return res;
    }

    private static boolean isValid(int[] candies, long k, int mid) {
        if (mid == 0) return true;

        for (int candy : candies) {
            k -= candy / mid;
            if (k <= 0) return true;
        }
        return false;
    }
}
