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

