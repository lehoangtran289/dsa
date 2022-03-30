def spiralNumbers(num):
    k = 0
    m = num
    l = 0
    n = num
    i = 0
    temp = 1
    res = [[0] * num for _ in range(num)]
    while k < m and l < n:
        for i in range(l, n):
            res[k][i] = temp
            temp += 1
        k += 1
        for i in range(k, m):
            res[i][n-1] = temp
            temp += 1
        n -= 1
        if k < m:
            for i in range(n - 1, (l - 1), -1) : 
                res[m - 1][i] = temp
                temp += 1
            m -= 1
        if (l < n) : 
            for i in range(m - 1, k - 1, -1) : 
                res[i][l] = temp
                temp += 1
            l += 1

    return res


n = 3
print(spiralNumbers(n))