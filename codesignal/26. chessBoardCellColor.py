def chessBoardCellColor(cell1, cell2):
    num1 = ord(cell1[0]) + ord(cell2[0])
    num2 = int(cell1[1]) + int(cell2[1])

    if (num1 % 2 != 0 and num2 % 2 == 0) or (num1 % 2 == 0 and num2 % 2 != 0):
        return False
    return True

cell1 = "A1"
cell2 = "C3"
print(chessBoardCellColor(cell1, cell2))