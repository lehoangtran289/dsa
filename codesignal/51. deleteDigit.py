def deleteDigit(n):
    max = -9999
    for i in range(len(list(str(n)))):
        if int(''.join(list(str(n))[:i] + list(str(n))[i+1:])) > max:
            max =  int(''.join(list(str(n))[:i] + list(str(n))[i+1:]))
    return max

n = 1001
print(deleteDigit(n))