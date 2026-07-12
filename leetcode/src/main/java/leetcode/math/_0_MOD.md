## Modular Arithmetic (MOD) — Properties & CP Tricks

### Core Properties (with `MOD` prime, e.g. `10^9+7`)

```
(a + b) % MOD = ((a % MOD) + (b % MOD)) % MOD
(a - b) % MOD = ((a % MOD) - (b % MOD) + MOD) % MOD   // handle negatives!
(a * b) % MOD = ((a % MOD) * (b % MOD)) % MOD
```

Division is **not** distributive — you need modular inverse (see below).

---

### Key Tricks

**1. Avoid overflow:** Always use `long` (Java/C++) for intermediate products before taking `% MOD`, especially when
`MOD ~ 10^9` and values multiply — products can reach `~10^18`.

**2. Negative results:** Java/C++ `%` can return negative values. Always normalize:

```java
result =((a -b)%MOD +MOD)%MOD;
```

**3. Modular Exponentiation** (fast pow, `O(log n)`):

```java
long power(long base, long exp, long mod) {
    long res = 1;
    base %= mod;
    while (exp > 0) {
        if ((exp & 1) == 1) res = res * base % mod;
        base = base * base % mod;
        exp >>= 1;
    }
    return res;
}
```

**4. Modular Inverse** (for division), when `MOD` is prime — via Fermat's Little Theorem:

```
a^(-1) mod MOD = a^(MOD-2) mod MOD
```

**5. Precompute factorials + inverse factorials** for combinatorics (`nCr mod MOD`) in `O(n)` setup, `O(1)` per query.

**6. Precompute repeated powers** (like your `pow10[]`) instead of recomputing per query — turns `O(log n)` or worse
into `O(1)` lookups.

**7. Cast to smaller type (`int`) only at the very end**, after all `% MOD` reductions — never truncate before reducing.
