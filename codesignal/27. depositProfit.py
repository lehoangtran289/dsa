def depositProfit(n, r, max, count):
    if n >= max:
        return count
    else:
        n = n + r*n/100
        count += 1
        return depositProfit(n, r, max, count)

print(depositProfit(100, 20, 170, 0))