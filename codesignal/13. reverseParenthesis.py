def reverseInParentheses(str):
    stack = []
    for i in range(len(str)):
        if str[i] == "(":
            stack.append(i)
            print(i)
    print(str)

    i = 0
    while len(stack) != 0:
        left = stack.pop()
        print(left)
        for i in range(left + 1, len(str)):
            if str[i] == ")":
                txt = str[left+1:i]
                str = str[:left] + txt[::-1] + str[i+1:]
                break

    return str

inputString = "foo(bar(baz))blim"
str2 = "foo(bar)baz(blim)"
# print(reverseInParentheses(inputString))
print(reverseInParentheses(str2))