package leetcode.math;

public class M_4002_CountValidSequences {

    private static final long MOD = (long) 1e9 + 7;
    private static long[] factorial;
    private static long[] inverseFactorial;

    public int countValidSequences(int n, int k) {
        // precompute factorial and inverse factorial
        factorial = new long[n + 1];
        inverseFactorial = new long[n + 1];

        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        inverseFactorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            inverseFactorial[i] = modInverse(factorial[i]);
        }

        // process
        long total = nCr(n - 1, k - 1);

        long allOdd = 0;
        if ((n - k) % 2 == 0) {
            allOdd = nCr((n - k) / 2 + k - 1, k - 1);
        }

        return (int) (((total - allOdd) % MOD + MOD) % MOD);

    }

    private long modInverse(long n) {
        return power(n, MOD - 2);
    }

    private long nCr(int n, int r) {
        if (r < 0 || r > n)
            return 0;

        return factorial[n]
               * inverseFactorial[r] % MOD
               * inverseFactorial[n - r] % MOD;
    }

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = res * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return res;
    }
}
