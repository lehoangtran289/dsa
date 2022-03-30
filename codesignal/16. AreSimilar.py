def areSimilar(a, b):
    i, j = 0, 0
    check = 0

    if len(a) != len(b):
        return False
    adif = []
    bdif = []
    i, j = 0, 0
    while(i < len(a) and j < len(b)):
        if a[i] != b[j]:
            adif.append(a[i])
            bdif.append(b[j])
        i+=1
        j+=1
    if len(adif) > 2 and len(bdif) > 2:
        return False
    else:
        return adif == bdif[::-1]


a = [832, 998, 148, 570, 533, 561, 894, 147, 455, 279]
b = [832, 998, 148, 570, 533, 561, 455, 147, 894, 279]

a = [2, 3, 9]
b = [10, 3, 2]

a = [4, 6, 3]
b = [3, 4, 6]

a = [832, 998, 148, 570, 533, 561, 894, 147, 455, 279]
b = [832, 570, 148, 998, 533, 561, 455, 147, 894, 279]

a = [3, 1, 2, 1, 4]
b = [1, 1, 2, 3, 4]

print(areSimilar(a, b))
