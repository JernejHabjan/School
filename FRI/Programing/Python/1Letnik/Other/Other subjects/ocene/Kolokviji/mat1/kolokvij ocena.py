__author__ = 'Roglan'

# kolokvij="ds1.txt"
kolokvij = "ww.txt"

f = open(kolokvij)
vsota2 = 0
vsota3 = 0
vsota4 = 0
vsota5 = 0
vsota6 = 0
vsota7 = 0
vsota8 = 0
for j in f:
    a = j.split()
    if len(a) == 6:
        if int(a[5]) < 50:
            vsota5 += 1
        if int(a[5]) >= 50:
            vsota2 += 1
        if int(a[5]) >= 60:
            vsota7 += 1
        if int(a[5]) >= 70:
            vsota3 += 1
        if int(a[5]) >= 80:
            vsota4 += 1
        if int(a[5]) >= 90:
            vsota8 += 1
    if len(a) == 2:
        vsota6 += 1

print("manj kot 50 jih je pisalo:", vsota5)
print("50 in vec jih je pisalo:", vsota2)
print("60 in vec jih je pisalo:", vsota7)
print("70 in vec jih je pisalo:", vsota3)
print("80 in vec jih je pisalo:", vsota4)
print("90 in vec jih je pisalo:", vsota8)
print("pisalo jih ni:", vsota6)

a = open(kolokvij)
seznam = []
for x in a:
    seznam.append(x.split())
vsota = 0
counter = 0
for y in seznam:
    if len(y) == 6:
        vsota += int(y[5])
        counter += 1
print("povprecje je:", round((int(vsota) / int(counter)), 2), "%")
