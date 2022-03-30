def absoluteValuesSumMinimization(arr):
    min = 99999
    index = 0
    for i in range(len(arr)):
        sum = 0
        for j in range(len(arr)):
            sum += abs(arr[i] - arr[j])
        if sum < min:
            index = i
            min = sum
    print(min)
    return arr[index]

a = [1, 1, 3, 4]
a = [2, 4, 7]
print(absoluteValuesSumMinimization(a))