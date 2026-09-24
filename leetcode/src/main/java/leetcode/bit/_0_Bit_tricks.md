### Get the right-most set bit

```
int lowBitVal = mask & (-mask); // e.g: 0001000
int i = Integer.numberOfTrailingZeros(lowBitVal); // position of right-most set bit
```

### Unset the right-most set bit

```
n = n & (n - 1); // turns off the rightmost set bit 
```

### Set bit i-th

```
mask |= 1 << i;
```

### Unset bit i-th

```
mask &= ~(1 << i);
```

### Check set bit i-th

```
if (((n >> i) & 1) == 1) {}
```

### enumerate all non-empty sub-masks of mask

The expression `(subset - 1) & mask` makes sure the subsetMask must fall under mask. Using `(subset + 1) & mask` will
not work, since `+` carries to the left -> which can cause infinite loop. While `-` changes right-most set bit (1) to a
0 and flips all trailing 0s to its right to 1s, then masking with `& mask` would give valid next downward step.

```
for (int subset = mask; subset > 0; subset = (subset - 1) & mask) {}
```