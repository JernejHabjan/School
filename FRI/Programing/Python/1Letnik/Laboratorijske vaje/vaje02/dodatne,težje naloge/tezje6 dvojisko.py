__author__ = 'Roglan'
stevilo = int(input("vpisi stevilo: "))
ostarray = []
while 1:
    ostanek = stevilo % 2
    stevilo //= 2
    ostarray.append(ostanek)
    if stevilo == 0:
        break
for i in reversed(ostarray):
    print(i)
