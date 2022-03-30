def digitDegree(n, count):
    if n < 10:
        return count
    return digitDegree(sum([int(x) for x in str(n)]), count + 1)

n = 91
lst = [int(x) for x in str(n)]
print(lst, digitDegree(n, 0))