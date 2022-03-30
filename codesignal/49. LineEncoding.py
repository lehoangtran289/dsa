def lineEncoding(s):
    res = ''
    check = [0] * len(s)
    for i in range(len(s)):
        if check[i] == 0:
            count = 1
            for j in range(i + 1, len(s)):
                if s[j] != s[i]:
                    count = j-i
                    break
                elif j == len(s) - 1:
                    count = j - i + 1
                    print(s[i], s[j], count)
                    check[j] = 1
                    break
                check[j] = 1
            if count != 1:
                res = res[:] + str(count) + s[i]
            else:
                res = res[:] + s[i]
    return res
            


s = "aabbabcc"
print(lineEncoding(s))