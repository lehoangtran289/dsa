package leetcode.math;

public class M_7_ReverseInteger {
    public int reverse(int x) {
        int res = 0;

        while (x != 0) {
            int last = x % 10;
            int temp = res * 10 + last;
            if ((temp - last) / 10 != res) return 0;

            res = temp;
            x /= 10;
        }

        return res;
    }
}
