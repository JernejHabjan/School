import os
from random import randint, random

from Modules import risar

face = []
vx = []
vy = []
for fn in os.listdir("slike"):
    faca = risar.slika(randint(0, risar.maxX - 100), randint(0, risar.maxY - 100),
                       "slike/" + fn)
    face.append(faca)

    vx.append(2 + random() * 3)
    vy.append(2 + random() * 3)

for i in range(500):
    for i in range(len(face)):
        faca = face[i]
        faca.setPos(faca.x() + vx[i], faca.y() + vy[i])
        if not (0 < faca.x() < risar.maxX - 35):
            vx[i] = -vx[i]
        if not (0 < faca.y() < risar.maxY - 35):
            vy[i] = -vy[i]
    risar.cakaj(0.01)
