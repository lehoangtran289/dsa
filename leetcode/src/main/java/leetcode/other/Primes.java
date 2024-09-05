package leetcode.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Primes {

    public static boolean isPrime(long n) {
        if (n <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if ((n % i) == 0)
                return false;
        }
        return true;
    }

    public static List<Long> buildPrimes(long n) {
        boolean[] primes = new boolean[(int) n + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        for (int i = 2; (long) i * i <= n; ++i) {
            if (primes[i]) {
                for (int j = i * i; j <= n; j += i) {
                    primes[j] = false;
                }
            }
        }
        List<Long> result = new ArrayList<>();
        for (int i = 0; (long) i * i <= n; ++i) {
            if (primes[i]) {
                result.add((long) i);
            }
        }
        return result;
    }

    public static int primeCount(long n) {
        int cnt = 0;
        long prod = 1;
        for (int i = 2; i < 60; ++i) {
            if (isPrime(i)) {
                prod *= i;
                if (prod >= 0 && prod <= n) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(primeCount(1));
    }
}
