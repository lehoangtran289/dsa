package leetcode.array.binarysearch;

public class E_69_MySqrtX {
    public static void main(String[] args) {
        System.out.println(mySqrt(4)); // 2
        System.out.println(mySqrt(8)); // 2
    }

    public static int mySqrt(int x) {
        if (x == 0 || x == 1) return x;

        int res = 0;
        int l = 0, r = x;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (mid > x / mid) {
                r = mid - 1;
            } else {
                res = mid;
                l = mid + 1;
            }
        }

        return res;
    }
}
