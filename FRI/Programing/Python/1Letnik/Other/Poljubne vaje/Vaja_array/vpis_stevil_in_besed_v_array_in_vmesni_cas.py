__author__ = 'Roglan'
from time import *

stevila = []
time_array = []
zacetni_cas = time()

while (1):
    y = input("vpisi nekaj: ")
    if (y == "" or y == "0"):
        break
    cas = time()
    cas -= zacetni_cas
    if (type(y) is int):
        stevila.append(y)
        stevila.sort()
    if (type(y) is str):
        y = y[0]
        stevila.append(y)
    time_array.append(cas)
print(stevila)
print(time_array)

# vmesni cas med vpisi, izpis vseh stevil
