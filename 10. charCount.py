def commonCharacterCount(s1, s2):
    i, check = 0, 0
    while i < len(s1):
        if s1[i] in s2:
            check += 1
            s2 = s2.replace(s1[i], '', 1)
            print(s2)
        i += 1
    return check

s1 = "zzzz"
s2 = "zzzzzzz"

s1= "aabcc"
s2= "adcaa"
print(commonCharacterCount(s1,s2))