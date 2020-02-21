def sumBom(arr):
    sum = 0
    for i in range(len(arr)):
        for j in range(len(arr[0])):
            sum += arr[i][j]
    return sum


def minesweeper(matrix):
    res = [[0] * (len(matrix[0]) + 2) for i in range(len(matrix) + 2)]
    print(res)
    for i in range(1, len(res) - 1):
        for j in range(1, len(res[0]) - 1):
            res[i][j] = int(matrix[i-1][j-1])
    # print(res)

    result = []
    for i in range(1, len(res) - 1):
        resRow = []
        for j in range(1, len(res[0]) - 1):
            temp = [arr[j-1:j+2] for arr in res[i-1:i+2]]
            # print('here: ', temp)
            if res[i][j] == 1:
                resRow.append(sumBom(temp) - 1)
            else:
                resRow.append(sumBom(temp))
        result.append(resRow)

    return result

matrix1 = [[True, False, False],
           [False, True, False],
           [False, False, False]]
matrix2 = [[True, False],
           [True, False],
           [False, True],
           [False, False],
           [False, False]]
print(minesweeper(matrix2))
