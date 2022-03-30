def sumUpNumbers(s):
    sumx = 0
    temp = ''
    arr = []
    i = 0
    while i < len(s):
        temp = ''
        if s[i].isdigit():
            temp += s[i]
            for j in range(i+1, len(s)):
                if s[j].isdigit():
                    temp += s[j]
                    i = j
                else:
                    break
            arr.append(int(temp))
        i += 1 
    print(arr)
    return sum(arr)

s = "42abcsd781"
print(sumUpNumbers(s))
