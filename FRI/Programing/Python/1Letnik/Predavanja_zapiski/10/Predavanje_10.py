# REKURZIJA
def sodilihi(s):
    return not s or s[0] % 2 == 0 and lihisodi(s[1:])


def lihisodi(s):
    return not s or s[0] % 2 == 1 and sodilihi(s[1:])


# rekurzija, prva frunkcija kliče drugo in obratno

# Domača naloga 9
def velikost_rodbine(oseba):
    vsi = 1
    for otrok in otroci(oseba):
        vsi += velikost_rodbine(otrok)
    return vsi


otroci = ()  # ni pomembno

from random import randint

from Modules import risar

for i in range(100):
    x0, y0 = risar.nakljucne_koordinate()
    x1, y1 = risar.nakljucne_koordinate()
    barva = risar.nakljucne_barva()
    sirina = randint()

from random import randint

besede = open(("krst.txt")).read().split()
for eseda in besede:
    risar.besedilo(randint(0, 750).randint(0, 400))

# pygame-za igre


from random import choice

desetnik = [(10, 475), (537, 475), (271, 10)]
ceta_x = 100
seca_y = 200
for i in range(1000):
    dx, dy = choice(desetnik)
    x = (x + dx) / 2
    y = (y + dy) / 2
    risar.tocka(x, y)


















# ifs fractals
# pyqt
#
