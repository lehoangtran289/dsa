def fileNaming(arr):
    flag = [0] * len(arr)
    for i in range(len(arr)):
        if flag[i] == 0:
            flag[i] = 1
            k = 1
            for j in range(i+1, len(arr)):
                if arr[j] == arr[i]:
                    temp = arr[j] + '(' + str(k) + ')'
                    while temp in arr[:j+1]:
                            k += 1
                            temp = arr[j] + '(' + str(k) + ')'
                    arr[j] = temp
                    k += 1
    return arr


names = ["a(1)","a(6)","a","a","a","a","a","a","a","a","a","a"]
print(fileNaming(names))
