def sudoku(grid):
    check = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    for i in grid:
        temp = sorted(i)
        if temp != check:
            return False

    for i in range(len(grid[0])):
        temp = []
        for j in range(len(grid)):
            temp.append(grid[j][i])
        if sorted(temp) != check:
            return False
    i = 0
    while i < 9:
        j = 0
        while j < 9:
            row = []
            row.append(grid[i][j])
            row.append(grid[i][j+1])
            row.append(grid[i][j+2])
            row.append(grid[i+1][j])
            row.append(grid[i+1][j+1])
            row.append(grid[i+1][j+2])
            row.append(grid[i+2][j])
            row.append(grid[i+2][j+1])
            row.append(grid[i+2][j+2])
            j += 3
            print(row)
            if sorted(row) != check:
                return False
        i += 3
    return True


grid = [[1, 3, 4, 2, 5, 6, 9, 8, 7],
        [4, 6, 8, 5, 7, 9, 3, 2, 1],
        [7, 9, 2, 8, 1, 3, 6, 5, 4],
        [9, 2, 3, 1, 4, 5, 8, 7, 6],
        [3, 5, 7, 4, 6, 8, 2, 1, 9],
        [6, 8, 1, 7, 9, 2, 5, 4, 3],
        [5, 7, 6, 9, 8, 1, 4, 3, 2],
        [2, 4, 5, 6, 3, 7, 1, 9, 8],
        [8, 1, 9, 3, 2, 4, 7, 6, 5]]

print(sudoku(grid))
