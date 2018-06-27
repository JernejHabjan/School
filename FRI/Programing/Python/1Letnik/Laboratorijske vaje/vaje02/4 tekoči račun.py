__author__ = 'jh0228'
i = 0
vsota = 0
while (vsota <= 100 and i <= 10):
    cena = float(input("vpisi ceno"))
    vsota += cena
    i += 1
print("porabili ste ", vsota, "in kupili ", i, "izdelkov")
