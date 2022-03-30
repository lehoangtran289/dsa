def addBorder(pic):
    res = ['*' * (len(pic[0]) + 2)] * (len(pic) + 2)
    print(len(res), len(res) + 2)
    for i in range(1, len(pic) + 1):
            res[i] = '*' + pic[i-1] + '*'
    return res

picture = ["abc",
           "def"]

print(addBorder(picture))