def isBeautifulString(s):
    s2 = ''.join(sorted(set(s)))
    print(s2)

    prev = -1
    flag = 0
    for c in range(ord('a'), ord('z') + 1):
        count = s.count(chr(c))
        print(chr(c), count)
        if count > prev:
            if flag == 0:
                max = count
                flag = 1
            else:
                return False
        prev = count
    return True


s1 = "bbbaacdafe"
s = "bbc"
print(isBeautifulString(s))
