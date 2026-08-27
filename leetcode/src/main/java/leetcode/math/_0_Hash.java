package leetcode.math;

/**
 * công thức để tính mã hash của một xâu S = s1 s2 ... sk như sau:
 *      hash(s1 s2 ... sk) = s1*BASE^0 + s2*BASE^1 + s3*BASE^2 + ... + sk*BASE^(k-1)
 *      (BASE = 256)
 *
 *
 * The division (/) by mod P, with P = large prime number.
 * e.g: a / b (mod P) = a * b^(P-2) (mod P)
 */
public class _0_Hash {

    private static final int BASE = 256;
    private static final int MOD = (int) 1e9 + 7;

    static void main() {
        _0_Hash solve = new _0_Hash();
        System.out.println(solve.prob1(new String[]{"abc", "cde", "def", "cde", "abc", "abc"}, 3)); // 4
    }

    /**
     * Problem: Find number of equal string pairs.
     * Given n strings s_0, s_1, ..., s_(n-1) of same length k. Find number of s_i = s_j
     */
    public int prob1(String[] strings, int k) {
        int n = strings.length;
        long[] hash = new long[n]; // n
        long[] power = new long[k]; // k

        // calculate MOD power array
        power[0] = 1;
        for (int i = 1; i < k; ++i) power[i] = power[i - 1] * BASE % MOD;

        // calculate HASH array
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < k; ++j) {
                char c = strings[i].charAt(j);
                hash[i] = (hash[i] + c * power[j]) % MOD;
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (hash[i] == hash[j]) res++;
        return res;
    }

    /**
     * Given string s of length n, we have q queries to check if s[i...i+k-1] == s[j...j+k-1] ?
     * ---
     * Idea: Use prefix sum to calculate hash of segment [0...i]
     *      hash[i] = hash(s[0...i-1]) = sum (s[j] * 256^(j)) với j = 0 -> i-1
     * => getHash(s[l...r]) = (hash[r] - hash[l - 1]) / 256^(l - 1)
     */
    public void prob2(String s, int n) {
        long[] pw = new long[n]; // pw[i] = 256 ^ i
        long[] invPw = new long[n]; // invPw[i] = (256 ^ i) ^ (MOD - 2) = (256 ^ (MOD - 2)) ^ i

        // prepare pw, invPw
        long invBase = power(BASE, MOD - 2); // 256 ^ (MOD - 2)
        pw[0] = invPw[0] = 1;

        for (int i = 1; i < n; ++i) {
            pw[i] = pw[i - 1] * BASE % MOD;
            invPw[i] = invPw[i - 1] * invBase % MOD;
        }

        // calculate prefix hash array S
        long[] hash = new long[n + 1];
        for (int i = 1; i <= n; ++i)
            hash[i] = (hash[i - 1] + s.charAt(i - 1) * pw[i - 1]) % MOD;

        // process queries
        // ...
    }

    // get hash in [l..r]
    public long getHash(long[] hash, long[] invPw, int l, int r) {
        long tmp = hash[r] - hash[l - 1];
        if (tmp < 0) tmp += MOD;
        return tmp * invPw[l - 1] % MOD; // divide by 256 ^ (l-1)
    }


    // fast pow
    public long power(int x, int k) {
        long res = 1, mul = x;
        while (k > 0) {
            if ((k & 1) == 1) res = res * mul % MOD;
            mul = mul * mul % MOD;
            k >>= 1;
        }
        return res;
    }
}
