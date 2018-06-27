__author__ = 'jh0228'
cena = None
vsota = 0
while cena != 0:
    print("za izhod vpisi 0")
    cena = int(input("vpisi ceno :"))
    vsota += cena
print(vsota)
