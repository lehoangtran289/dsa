def digitsProduct(n):
    if n == 0:
        return 10
    if n == 1:
        return 1

    res = 0
    for i in range(9, 1, -1):
        while n % i == 0:
            n //= i
            res = res + i*(10**(len(str(res))))

    if n > 1:
        return -1
    print(n)
    return res//10


n = 243
print(digitsProduct(n))
