def arrayChange(arr):
    count = 0
    for i in range(1, len(arr)):
        if (arr[i] <= arr[i-1]):
            count += arr[i-1] - arr[i] + 1
            arr[i] = arr[i-1] + 1
            print(arr[i-1], arr[i], count)
    return count

arr = [2, 3, 3, 5, 5, 5, 4, 12, 12, 10, 15]

print(arrayChange(arr))