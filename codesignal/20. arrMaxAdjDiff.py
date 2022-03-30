def arrayMaximalAdjacentDifference(arr):
    max = -9999999
    for i in range(1, len(arr)):
        if (abs(arr[i] - arr[i-1]) > max):
            max = abs(arr[i] - arr[i-1])
    return max
