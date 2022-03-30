def sum3(arr):
    sum = 0
    for i in range(len(arr)):
        for j in range(len(arr[0])):
            sum += arr[i][j]
    return sum


def boxBlur(matrix):
    res = []
    for i in range(0, len(matrix) - 2):
        resRow = []
        for j in range(0, len(matrix[0]) - 2):
            # temp = [arr[i:i+3] for arr in matrix[j:j+3]]
            temp = []
            for arr in matrix[i:i+3]:
                temp.append(arr[j:j+3])
            sum = sum3(temp) // 9
            resRow.append(sum)
            # print(resRow, sum, temp)
        res.append(resRow)
    return res


image = [[7, 4, 0, 1],
         [5, 6, 2, 2],
         [6, 10, 7, 8],
         [1, 4, 2, 0]]
image = [[1, 1, 1],
         [1, 7, 1],
         [1, 1, 1]]

print(boxBlur(image))
