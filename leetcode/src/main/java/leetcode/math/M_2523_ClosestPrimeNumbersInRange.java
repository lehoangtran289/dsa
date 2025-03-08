package leetcode.math;

import java.util.Arrays;

public class M_2523_ClosestPrimeNumbersInRange {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(closestPrimes(1, 1))); // -1, -1
        System.out.println(Arrays.toString(closestPrimes(10, 19))); // 11, 13
        System.out.println(Arrays.toString(closestPrimes(19, 31))); // 29, 31
        System.out.println(Arrays.toString(closestPrimes(4, 6))); // -1, -1
    }

    public static int[] closestPrimes(int left, int right) {
        boolean[] isPrime = isPrime(right);

        int[] res = new int[]{-1, -1};
        int minLength = 1000_000;
        int prev = -1000_000;

        for (int i = left; i <= right; ++i) {
            if (!isPrime[i]) continue;

            int diff = i - prev;
            if (diff < minLength) {
                minLength = diff;
                res[0] = prev;
                res[1] = i;
            }

            prev = i;
        }

        return res;
    }

    private static boolean[] isPrime(int n) {
        if (n <= 1) return new boolean[]{false, false};

        boolean[] isPrimes = new boolean[n + 1];
        Arrays.fill(isPrimes, true);
        isPrimes[0] = false;
        isPrimes[1] = false;

        for (int i = 2; i <= Math.sqrt(n); ++i) {
            if (isPrimes[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrimes[j] = false;
                }
            }
        }

        return isPrimes;
    }
}
