def growingPlant(up, down, h, n, day):
    if n >= h:
        return day
    day += 1
    return growingPlant(up, down, h, n + up - down, day)

print(growingPlant(100, 10, 910, 100, 1))
