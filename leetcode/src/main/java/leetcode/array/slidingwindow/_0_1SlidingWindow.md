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

---

## Sliding window with deque

```C++
#include <bits/stdc++.h>
using namespace std;

vector<int> slidingWindowMax(vector<int>& a, int k) {
    deque<int> dq;          // deque lưu chỉ số, duy trì giảm dần
    vector<int> result;     // mảng kết quả

    for (int i = 0; i < a.size(); i++) {
        while (!dq.empty() && dq.front() <= i - k)
            dq.pop_front();         // loại phần tử ngoài cửa sổ

        while (!dq.empty() && a[dq.back()] <= a[i])
            dq.pop_back();          // loại phần tử nhỏ hơn a[i]

        dq.push_back(i);            // thêm chỉ số i vào deque

        if (i >= k - 1)
            result.push_back(a[dq.front()]);  // ghi nhận max của cửa sổ
    }
    return result;
}

vector<int> slidingWindowMin(vector<int>& a, int k) {
    deque<int> dq;          // deque lưu chỉ số, duy trì tăng dần
    vector<int> result;     // mảng kết quả

    for (int i = 0; i < a.size(); i++) {
        while (!dq.empty() && dq.front() <= i - k)
            dq.pop_front();         // loại phần tử ngoài cửa sổ

        while (!dq.empty() && a[dq.back()] >= a[i])
            dq.pop_back();          // loại phần tử lớn hơn a[i]

        dq.push_back(i);            // thêm chỉ số i vào deque

        if (i >= k - 1)
            result.push_back(a[dq.front()]);  // ghi nhận min của cửa sổ
    }
    return result;
}

vector<int> nextGreaterElement(vector<int>& a) {
    int n = a.size();
    vector<int> nge(n, -1);     // mảng kết quả, khởi tạo bằng -1
    deque<int> dq;              // deque lưu chỉ số, duy trì giảm dần

    for (int i = n - 1; i >= 0; i--) {       // duyệt từ phải sang trái
        while (!dq.empty() && a[dq.back()] <= a[i])
            dq.pop_back();                   // loại phần tử nhỏ hơn hoặc bằng a[i]

        if (!dq.empty())
            nge[i] = dq.back();              // phần tử đầu deque là next greater

        dq.push_back(i);                     // thêm chỉ số i vào deque
    }
    return nge;
}

int main() {
    vector<int> a = {1, 3, -1, -3, 5, 3, 6, 7};
    auto res = slidingWindowMax(a, 3);
    for (int x : res) cout << x << " ";  // in kết quả
    cout << endl;

    auto resMin = slidingWindowMin(a, 3);
    for (int x : resMin) cout << x << " ";  // in kết quả
}
```