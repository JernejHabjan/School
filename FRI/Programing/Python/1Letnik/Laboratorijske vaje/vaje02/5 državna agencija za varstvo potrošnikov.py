__author__ = 'jh0228'
denar = int(input("vpisi denar :"))
while denar >= -100:
    cena = int(input("vpisi prejemek oz. izdatek :"))
    print("sprememba je", cena)
    if cena > 0:
        denar += cena
    if cena < 0:
        denar += cena
    else:
        print("to ni strošek/izdatek")
    print("imate :", denar)
print("zagonili ste vse")
