package leetcode.array.binarysearch;

public class M_875_KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int res = 0;
        int l = 1, r = 0;
        for (int p : piles) {
            r = Math.max(r, p);
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(piles, h, mid)) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    private boolean isValid(int[] piles, int h, int mid) {
        long sumH = 0;
        for (int n : piles) {
            sumH += n / mid;
            if (n % mid != 0) sumH++;
        }
        return sumH <= h;
    }
}
