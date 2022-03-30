def allLongestStrings(arr):
    res = []
    max = 0
    for i in arr:
        if len(i) >= max:
            max = len(i)
    for i in arr:
        if len(i) == max:
            res.append(i)
    return res

inputArray = ["aba", "aa", "ad", "vcd", "aba"]

print(allLongestStrings(inputArray))