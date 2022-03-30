def messageFromBinaryCode(code):
    print(chr(int(code[:8], 2)))
    res = ''
    for i in range(0, len(code), 8):
        res += chr(int(code[i:i+8], 2))
    return res
    

code = "010010000110010101101100011011000110111100100001"
print(messageFromBinaryCode(code))