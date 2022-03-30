def avoidObstacles(arr):
    i = 1
    while(i > 0):  
        count = 0
        for j in arr:
            if j % i == 0:
                break
            count += 1
        if count == len(arr):
            return i
        i += 1
        

arr = [1, 4, 10, 6, 2]
arr = [5, 3, 6, 7, 9]
print(avoidObstacles(arr))



