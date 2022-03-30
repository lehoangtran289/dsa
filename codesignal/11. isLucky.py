def isLucky(n):
    sumleft, sumright, check = 0, 0, 0
    half = len(str(n)) / 2
    while n > 0 and check < half:
        temp = n % 10
        sumleft += temp
        n //= 10
        check += 1
    while n > 0:
        temp = n % 10
        sumright += temp
        n //= 10
    if sumleft == sumright:
        return True
    return False
    

n1 = 1230
n2 = 239017
print(isLucky(n1), isLucky(n2))
