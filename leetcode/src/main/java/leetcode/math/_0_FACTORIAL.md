# Factorial — `factorial[]`, `inverseFactorial[]`, `modInverse()`

This note captures the standard combinatorics toolkit used in problems like `M_4002_CountValidSequences`.

Core idea:

- precompute `factorial[i]`
- precompute `inverseFactorial[i]`
- compute `modInverse(x)` with fast exponentiation
- answer `nCr` queries in `O(1)` after an `O(n)` setup

## 1) Factorial

Precompute factorial array so that query `factorial[i]` is O(1):

```java
long[] precomputeFactorial(int n) {
    long[] factorial = new long[n + 1];

    factorial[0] = 1;
    for (int i = 1; i <= n; i++) {
        factorial[i] = factorial[i - 1] * i % MOD;
    }

    return factorial;
}
```

## 3) Inverse factorial

`inverseFactorial[i]` means the modular inverse of `factorial[i]`:

`inverseFactorial[i] = (factorial[i] ^ (-1) % MOD`

It lets us divide by factorials under modulo arithmetic. **Division is not valid directly under modulo arithmetic**, so
we use the modular inverse.

If `MOD` is prime, Fermat's Little Theorem gives:

- `inv(x) = x^(MOD - 2) mod MOD`

Example:

- `5! = 120`
- so `inverseFactorial[5] = 120^(-1) mod MOD`
- under `MOD = 1_000_000_007`, this value is `808333339`
- because `120 * 808333339 % MOD = 1`

So `inverseFactorial[i]` can be computed from `modInverse(factorial[i])`.

```java
long[] inverseFactorial() {
    long[] inverseFactorial = new long[n + 1];

    inverseFactorial[n] = modInverse(factorial[n]);
    for (int i = n - 1; i >= 0; i--) {
        inverseFactorial[i] = inverseFactorial[i + 1] * (i + 1) % MOD;
    }

    return inverseFactorial;
}

long modInverse(long n) {
    return power(n, MOD - 2);
}
```

## 4) Combination

```java
long nCr(int n, int r) {
    if (r < 0 || r > n)
        return 0;

    return factorial[n]
           * inverseFactorial[r] % MOD
           * inverseFactorial[n - r] % MOD;
}
```

Fast exponentiation is implemented as follows:

```java
long power(long base, long exp) {
    long res = 1;
    base %= MOD;
    while (exp > 0) {
        if ((exp & 1) == 1) res = res * base % MOD;
        base = base * base % MOD;
        exp >>= 1;
    }
    return res;
}
```