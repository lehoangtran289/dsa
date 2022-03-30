def sortByHeight(a):
    for i in range(len(a)):
        if a[i] != -1:
            for j in range(i, len(a)):
                if a[j] != -1:        
                    if a[i] > a[j]:
                        temp = a[i]
                        a[i] = a[j]
                        a[j] = temp
    return a


a = [-1, 150, 190, 170, -1, -1, 160, 180]
print(sortByHeight(a))