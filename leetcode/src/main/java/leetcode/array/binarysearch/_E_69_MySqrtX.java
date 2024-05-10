package leetcode.array.binarysearch;

public class _E_69_MySqrtX {
    public static void main(String[] args) {
        System.out.println(mySqrt(4)); // 2
        System.out.println(mySqrt(8)); // 2
    }

    public static int mySqrt(int x) {
        if (x == 0) return 0;

        int lo = 0;
        int hi = Integer.MAX_VALUE;
        while (lo < hi - 1) {
            int mid = hi - (hi - lo) / 2;
            if (mid <= x / mid) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
