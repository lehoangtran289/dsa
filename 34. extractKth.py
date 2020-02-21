def extractEachKth(arr, k):
    res = []
    if k == 1:
        return res
    else:
        for i in range(len(arr)):
            if i % k != k-1:
                res.append(arr[i])
    return res

arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
#[1, 2, 4, 5, 7, 8, 10] 
print(extractEachKth(arr, 3))