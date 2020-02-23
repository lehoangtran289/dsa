def isMAC48Address(s):
    lst = s.split('-')
    if len(s) != 17:
        return False
    
    for i in lst:
        if len(i) != 2:
            return False
        for j in range(len(i)):
            if ord('9') < ord(i[j]) < ord('A') or ord(i[j]) > ord('F') or ord(i[j]) < ord('0'):
                return False
    return True
