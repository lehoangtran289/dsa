def isIPv4Address(str):
    
    tmp = str.split(".")
    if len(tmp) != 4:
        return False
    
    for i in tmp:
        if i.isdigit() == False:
            return False
        else:
            if int(i) < 0 or int(i) >= 256:
                return False
    return True

str = "a0.1.1.1"
print(isIPv4Address(str))

