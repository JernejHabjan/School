__author__ = 'Roglan'
a = int(input("vpisi a: "))
sestevalec = 0
for x in range(1, a):
    if (a % x == 0):
        sestevalec += x
if (sestevalec == a):
    print(True)
else:
    print(False)
