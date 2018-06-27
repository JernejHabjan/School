__author__ = 'Roglan'

kolokvij = "dsizpit.txt"

f = open(kolokvij)
i = 0
next(f)
vsota = 0

for x in f:
    a = x.split("\t")
    if len(a) == 14:
        b = int(a[13][0])
        if b == 1:
            b == 10
        vsota += b
        i += 1
print("povprecje je:", round((int(vsota) / int(i)), 2), "%")
