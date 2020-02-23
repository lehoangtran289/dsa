def isPalindrome(st:str):
    return st == st[::-1]

def buildPalindrome(st):
    if isPalindrome(st):
        return st
    stack = []
    for i in st:
        stack.append(i)
        temp = ''.join(stack)[::-1]
        if isPalindrome(st + temp):
            return st + temp

st = "ababab"
print(buildPalindrome(st))