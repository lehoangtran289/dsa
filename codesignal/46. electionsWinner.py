def electionsWinners(arr, k):
    count = 0
    temp = max(arr)
    if k == 0:
        if arr.count(temp) > 1:
            return 0
        return 1
    for i in arr:
        if i + k > temp:
            count += 1
    return count
    

arr = [2, 3, 5, 2]
arr2 = [3, 1, 1, 3, 1]
arr3 = [1, 1, 1, 1]
print(electionsWinners(arr2, 0))