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

The most common dynamic sliding-window pattern starts with a valid window, expands r, and only shrinks l when expansion
makes the window invalid.

### Pattern 1 : maximum window size / counting all valid windows

```java
for(int r = 0;
r<n;r++){

// 1. Expand window by including nums[r]
add(nums[r]);

// 2. If expansion made the window invalid, shrink until it becomes valid again.
    while(!

isValid(l, r)){

remove(nums[l]);

l++;
    }

// 3. [l, r] is guaranteed valid here
ans =Math.

max(ans, r -l+1); // OR ans += r - l + 1, if counting all valid windows
}
```

Use cases:

- longest substring without duplicates
- longest substring with at most K distinct elements
- longest subarray sum ≤ K, when all numbers are non-negative
- maximum window satisfying some monotonic constraint

### Pattern 2 : minimum window size

```java
for(int r = 0;
r<n;r++){

// 1. Expand window by including nums[r]
add(nums[r]);
    
    while(

isValid(l, r)){
minLen =Math.

min(minLen, r -l+1);

remove(nums[l]);

l++;
    }
}
```

Use cases:

- Minimum Size Subarray Sum >= target
- Minimum Window Substring

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