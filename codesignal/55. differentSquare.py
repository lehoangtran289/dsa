def differentSquares(m):
    i, j = 0, 0
    st = {1}
    while i < len(m) - 1:
        j = 0
        while j < len(m[0]) - 1:
            # temp = []
            # temp.append(tuple(m[i][j:j+2]))
            # temp.append(tuple(m[i+1][j:j+2]))
            # print(tuple(temp))
            # st.add(tuple(temp))
            s = ''
            s += str(m[i][j:j+2]) + str(m[i+1][j:j+2])
            st.add(s)
            j += 1
        i += 1
    return len(st) - 1
    
matrix = [[1, 2, 1],
          [2, 2, 2],
          [2, 2, 2],
          [1, 2, 3],
          [2, 2, 1]]
print(differentSquares(matrix))