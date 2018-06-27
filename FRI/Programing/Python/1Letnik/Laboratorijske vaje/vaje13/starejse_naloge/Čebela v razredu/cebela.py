import random

import risar


class Cvet:
    def __init__(self, color):
        self.color = color
        self.flower = risar.slika(-1000, -1000, self.color + "_flower.svg")
        self.nov_cvet(False)

    def nov_cvet(self, zgoraj):
        self.x = random.randint(20, risar.maxX - 20)
        if zgoraj:
            self.y = -20
        else:
            self.y = random.randint(0, 300)
        self.flower.setPos(self.x, self.y)

    def premik(self):
        self.y += 5
        self.flower.setPos(self.x, self.y)
        if self.y > risar.maxY + 30:
            self.nov_cvet(True)

    def oberi_ce(self, x, y):
        if abs(self.x - x) < 40 and abs(self.y - y) < 10:
            if self.color == "blue":
                return True
            self.nov_cvet(True)


class Cebela:
    def __init__(self):
        self.x = risar.maxX / 2
        self.bee = risar.slika(-1000, -1000, "bee_forestgreen.svg")
        self.bee.setPos(self.x, risar.maxY - 100)
        self.v = 0

    def premik(self):
        if risar.desno:
            self.v += 0.2
        elif risar.levo:
            self.v -= 0.2
        self.x += self.v
        self.bee.setX(self.x)

    def zunaj(self):
        return not 0 < self.x < risar.maxX


risar.barvaOzadja(risar.zelena)
cvetovi = [Cvet(color)
           for color in ["black", "blue", "brown", "green", "purple"] * 4]
cebela = Cebela()
strup = False
while not cebela.zunaj() and not strup:
    for cvet in cvetovi:
        cvet.premik()
        strup = strup or cvet.oberi_ce(cebela.x, risar.maxY - 100)
    cebela.premik()
    risar.cakaj(0.001)
