def differentSymbolsNaive(s):
    return len("".join(set(s)))

s = "bcaba"
print(differentSymbolsNaive(s))
