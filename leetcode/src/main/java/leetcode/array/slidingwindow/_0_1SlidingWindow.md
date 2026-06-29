### EN

- Sliding window idea:
    - Observation: If the segment **[l, r + 1]** is valid, then the segment **[l, r]** must be valid. But if the segment
      **[l, r]** is valid, it does not necessarily mean that the segment **[l, r + 1]** is valid.
    - Therefore, for each l = 1, 2, ..., n; we need to find the largest r such that the segment **[l, r]** is valid.
    - Then the number of valid segments starting from l is **r_max - l + 1**.

### VI

- Ý tưởng Sliding window:
    - Nhận xét: Nếu đoạn **[l, r + 1]** hợp lệ thì chắc chắn đoạn **[l, r]** hợp lệ. Nhưng nếu đoạn **[l, r]** hợp lệ
      thì chưa chắc đoạn **[l, r + 1]** hợp lệ.
    - Do đó, với mỗi l = 1, 2, ..., n; Ta cần tìm r lớn nhất sao cho đoạn **[l, r]** hợp lệ.
    - Khi đó số đoạn hợp lệ bắt đầu từ l là **r_max - l + 1**.

Great observation! Let me break down the **core difference** and **when each pattern applies**.

---

## Two Sliding Window Patterns

### Pattern 1 — `while` shrink (common pattern)

```java
for (int r = 0; r < n; r++) {// expand with r
    while (isInvalid(l, r)) {
        l++;  // shrink until valid
    }
    res = Math.min(res, r - l + 1); // minimum window / count
}
```

✅ Window is **always valid** after shrinking
✅ Used for: **minimum** window size, counting valid windows

---

### Pattern 2 — `if` shrink (this problem's pattern)

```java
for (int r = 0; r < n; r++) {// expand with r
    if (isInvalid(l, r)) {
        l++;  // shrink by exactly 1
    }
    res = Math.max(res, r - l + 1); // maximum window
}
```

⚠️ Window might **still be invalid** after shrinking
✅ Used for: **maximum** window size only

---

## Why Does `if` Work Here?

The key insight is the window size **never decreases**:

```
r=0: window size = 1  (valid)   → res=1
r=1: window size = 2  (valid)   → res=2
r=2: window size = 3  (invalid) → shrink → size=2, res stays 2
r=3: window size = 3  (valid)   → res=3  ← grew again!
```

> Since we only want the **maximum**, we never need to shrink below our current best. Shrinking by 1 simply "holds" the
> window size steady.

---

## Summary — When to Use Each

| Condition        | Use `while`                  | Use `if`                              |
|------------------|------------------------------|---------------------------------------|
| **Goal**         | Min window / Count           | Max window                            |
| **After shrink** | Window must be valid         | Window can stay invalid               |
| **Window size**  | Can decrease                 | Never decreases                       |
| **Key question** | "Find exact valid boundary?" | "Can we do better than current best?" |

---

**One-line rule:**
> Use `if` when you're **maximizing** window size — you never need to shrink below your current best answer.

