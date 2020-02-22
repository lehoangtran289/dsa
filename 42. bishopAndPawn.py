def bishopAndPawn(b, p):
    row = b[0]
    col = b[1]
    lst = []
    for i in range(1, 9):
        if 'a' <= chr(ord(row) + i) <= 'h'  and 0 < int(col) + i <= 8:
            lst.append(chr(ord(row) + i) + str(int(col) + i))
        if 'a' <= chr(ord(row) - i) <= 'h' and 0 < int(col) + i <= 8:
            lst.append(chr(ord(row) - i) + str(int(col) + i))
        if 'a' <= chr(ord(row) - i) <= 'h' and 0 < int(col) - i <= 8:
            lst.append(chr(ord(row) - i) + str(int(col) - i))
        if 'a' <= chr(ord(row) + i) <= 'h' and 0 < int(col) - i <= 8:    
            lst.append(chr(ord(row) + i) + str(int(col) - i))
    print(lst)
    return p in lst
        

bishop = "a1"
pawn = "c3"
print(bishopAndPawn(bishop, pawn))