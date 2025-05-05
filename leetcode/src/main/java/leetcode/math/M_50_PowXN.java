package leetcode.math;

public class M_50_PowXN {
    public double myPow(double x, int n) {
        return n < 0 ? (1 / exponent(x, - (long) n)) : exponent(x, n);
    }

    private double exponent(double x, long n) {
        double res = 1;

        while (n > 0) {
            if (n % 2 == 1) {
                res *= x;
            }
            x *= x;
            n /= 2;
        }

        return res;
    }
}
