def chessKnight(chess):
    row = chess[0]
    col = chess[1]
    count = 0
    if 'a' <= chr(ord(row) -2) <= 'h'  and 0 < int(col) + 1 <= 8:
        count += 1
    if 'a' <= chr(ord(row) -2) <= 'h'  and 0 < int(col) - 1 <= 8:
        count += 1
    if 'a' <= chr(ord(row) -1) <= 'h'  and 0 < int(col) + 2 <= 8:
        count += 1
    if 'a' <= chr(ord(row) -1) <= 'h'  and 0 < int(col) - 2 <= 8:
        count += 1
    if 'a' <= chr(ord(row) +1) <= 'h'  and 0 < int(col) -2 <= 8:
        count += 1
    if 'a' <= chr(ord(row) +1) <= 'h'  and 0 < int(col) +2 <= 8:
        count += 1
    if 'a' <= chr(ord(row) +2) <= 'h'  and 0 < int(col) -1 <= 8:
        count += 1
    if 'a' <= chr(ord(row) +2) <= 'h'  and 0 < int(col) + 1 <= 8:
        count += 1
    return count

chess = "c2"
print(chessKnight(chess))

