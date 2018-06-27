__author__ = 'Roglan'

velikost = float(input("vpisi telesno tezo v centimetrih"))
masa = float(input("vpisi tezo v kilogramih"))
velikost = velikost / 100
indeks = (masa / (velikost) ** 2)
print(indeks)
