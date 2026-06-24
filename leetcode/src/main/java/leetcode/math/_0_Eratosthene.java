package leetcode.math;

public class _0_Eratosthene {

    /**
     * Sieve of Eratosthenes
     * -> Find all prime numbers up to MAX
     */
    public static boolean[] eratosthene(int MAX) {
        boolean[] notPrime = new boolean[MAX + 1];
        for (int i = 0; i < 2; ++i) notPrime[i] = true;

        for (int i = 2; i * i <= MAX; i++) {
            if (!notPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        return notPrime;
    }
}
