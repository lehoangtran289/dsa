from itertools import permutations 

def countDif(s1, s2):
    count = 0
    i, j = 0, 0
    while i < len(s1) and j < len(s2):
        if s1[i] != s2[j]:
            count += 1
        i += 1
        j += 1
    return count

def check(arr):
    for i in range(1, len(arr)):
        if countDif(arr[i-1], arr[i]) != 1:
            return False
    return True

def stringsRearrangement(arr):
    perm = permutations(arr)
    temp = list(perm)
    for i in range(len(temp)):
        lst = list(temp[i]) 
        if check(lst):
            return True
    return False

def stringsRearrangement2(arr, preStr):
    if len(arr) < 1:
        return True
    
    for i in range(len(arr)):
        curStr = arr[i]
        if preStr != '' and countDif(preStr, curStr) != 1:
            continue
        newArr = arr[:i] + arr[i+1:]
        print(i, 'pre:', preStr, '---cur:',curStr, '->',  newArr)
        isValid =  stringsRearrangement2(newArr, curStr)
        if isValid: # ElseIf False -> continue other valid element
            return True

    return False


inputArray = ["aba", "bbb", "bab"]
inputArray2 = ["ab", "bb", "aa"]
inputArray3 = ["abc", "bef", "bcc", "bec", "bbc", "bdc"]

print(stringsRearrangement2(inputArray, ''))