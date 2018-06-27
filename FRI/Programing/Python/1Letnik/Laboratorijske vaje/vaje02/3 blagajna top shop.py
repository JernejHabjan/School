__author__ = 'jh0228'
cena = None
vsota = 0
j = 0
while cena != 0:
    print("za izhod vpisi 0")
    cena = int(input("vpisi ceno :"))
    vsota += cena
    if (cena != 0):
        j = j + 1
print("vsota je", vsota)

print("povprecna cena je ", vsota // j)
