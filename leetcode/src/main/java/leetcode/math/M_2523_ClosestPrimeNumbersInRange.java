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
        int[] res = new int[2];
        boolean[] isPrime = isPrime(right);
        int minLength = Integer.MAX_VALUE;
        int prev = 0;

        for (int i = right; i >= left; --i) {
            if (!isPrime[i]) continue;

            if (prev != 0 && prev - i <= minLength) {
                minLength = prev - i;
                res[0] = i;
                res[1] = prev;
            }
            prev = i;
        }

        if (res[0] == 0) {
            return new int[]{-1, -1};
        }
        return res;
    }

    private static boolean[] isPrime(int n) {
        if (n <= 1) return new boolean[2];

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
