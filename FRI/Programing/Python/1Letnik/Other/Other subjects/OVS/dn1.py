a = open("download");
seznam = []
for x in a:
    seznam.append(x.strip("\n"))
seznam = sorted(seznam)

centil = 0.8
z = round(len(seznam) * centil)
print(z)
print(centil * 100, "i centil je", seznam[z])
