a = 2
print(a)
b = input("vnesi stevilo\n")
print(b + a)
if (b + a) > 4:
    print("tvoje stevilo je preveliko")
else:
    print("ti si napisal ok")
for a in range(2, 20):
    a = a + 1
    if a >= 20:
        print("cool je")
        break

c = float(2.2)
print(c)

while c < 3:
    c += 1

    print(c)

if bool(4 < 5) and (5 < 6):
    print("ok")
