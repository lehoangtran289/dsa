def palindromeRearranging(str):
    tmp = [0] * len(str)
    checkMid = 0
    for i in range(len(str)):
        if (tmp[i] == 0):
            count = 0
            for j in range(len(str)):
                if str[j] == str[i]:
                    count += 1
                    tmp[j] = 1
            print(tmp)
            if count % 2 == 1:
                if checkMid == 0:
                    checkMid = 1
                else:
                    return False
    return True

str = 'aaabbcc'
print(palindromeRearranging(str))

