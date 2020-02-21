def variableName(name):
    if name[0].isdigit():
        return False
    for i in range(len(name)):
        if (name[i] != '_'):
            if not name[i].isalpha() and not name[i].isdigit():
                print(i)
                return False
    return True

def alphabeticShift(str):
    for i in range(len(str)):
        if str[i] != 'z':
            str = str[:i] + chr(ord(str[i]) + 1) + str[i+1:len(str)]
        else:
            str = str[:i] + 'a' + str[i+1:len(str)]
    return str


name = 'var_1__Int'
print(variableName(name))

str = 'crazy'
print(alphabeticShift(str))