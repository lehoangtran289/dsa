def arrayMaxConsecutiveSum(arr, k):
    prevSum, max = 0, 0
    for i in range(len(arr) - k + 1):
        if i == 0:
            for j in range(0, k):
                prevSum += arr[j]
            max = prevSum
        else:
            cur = prevSum - arr[i-1] + arr[i+k-1]
            if cur > max:
                max = cur
            prevSum = cur

    return max

inputArray = [2, 3, 5, 1, 6]
arr = [1, 3, 4, 2, 4, 2, 4]
print(arrayMaxConsecutiveSum(arr, 4))

