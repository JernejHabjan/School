from math import sqrt
from random import randint

from Modules import risar


class Circle:
    def __init__(self, n=True):
        self.width = 3
        self.diameter = 20 if n else 50
        self.x = randint(self.diameter + self.width, risar.maxX - (self.diameter + self.width))
        self.y = randint(self.diameter + self.width, risar.maxY - (self.diameter + self.width))
        self.timer = 5
        self.color = risar.barva(randint(100, 255), randint(100, 255), randint(100, 255))
        self.body = risar.krog(self.x, self.y, self.diameter, self.color, self.width)
        self.movement_x = randint(-5, 5)
        self.movement_y = sqrt(5 ** 2 - self.movement_x ** 2)


class Launcher():
    def __init__(self):
        risar.barvaOzadja(risar.barva(16, 36, 27))
        self.poped = []
        self.circles = []
        self.moj_krogec = Circle(False)

    def load_circles(self, n):
        for i in range(n):
            self.circles.append(Circle())

    def move(self):
        for element in self.circles:
            if not element.diameter < element.x < risar.maxX - element.diameter:
                element.movement_x = -element.movement_x
            if not element.diameter < element.y < risar.maxY - element.diameter:
                element.movement_y = -element.movement_y
            element.x += element.movement_x
            element.y += element.movement_y
            element.body.setPos(element.x, element.y)

    def mouse(self):
        if not risar.klik:
            self.moj_krogec.body.setPos(risar.miska[0], risar.miska[1])
        elif not len(self.poped):
            self.poped.append(self.moj_krogec)
        risar.klik = False

    def test_collision(self):
        for element1 in self.circles:
            for element2 in self.poped:

                if sqrt((element2.body.x() - element1.body.x()) ** 2 + (
                    element2.body.y() - element1.body.y()) ** 2) <= element1.diameter + element2.diameter:
                    r = 50
                    element1.movement_x = element1.movement_y = 0
                    element1.body.setRect(-r, -r, 2 * r, 2 * r)
                    element1.diameter = r
                    if element1 not in self.poped:
                        self.poped.append(element1)
                    if element1.timer > 0:
                        element1.timer -= 0.02
                    if element1.timer <= 0:
                        element1.body.hide()

                    if element2.timer > 0:
                        element2.timer -= 0.02
                    if element2.timer <= 0:
                        element2.body.hide()

                    else:
                        element2.body.hide()

                    break

    def test_timer(self):
        self.time_counter = 0
        for i in self.circles:
            if i.timer > 0:
                self.time_counter += 1
        if self.time_counter == 0:
            risar.QMessageBox.information(None, "Chain Reaction",
                                          "{} circles have been blown".format(len(self.poped) - 1))
        return self.time_counter == 0


a = Launcher()
a.load_circles(20)
risar.QMessageBox.information(None, "Chain Reaction", "razstreli 20 krogcev")
while 1:
    a.move()
    a.mouse()
    a.test_collision()
    if a.test_timer() == True:
        break
    a.test_timer()
    risar.obnovi()
    risar.cakaj(0.002)
risar.cakaj(0.5)

a = Launcher()
a.load_circles(10)
risar.QMessageBox.information(None, "Chain Reaction", "razstreli 10 krogcev")
while 1:
    a.move()
    a.mouse()
    a.test_collision()
    if a.test_timer() == True:
        break
    a.test_timer()
    risar.obnovi()
    risar.cakaj(0.002)
risar.cakaj(0.5)

a = Launcher()
a.load_circles(7)
risar.QMessageBox.information(None, "Chain Reaction", "razstreli 7 krogcev")
while 1:
    a.move()
    a.mouse()
    a.test_collision()
    if a.test_timer() == True:
        break
    a.test_timer()
    risar.obnovi()
    risar.cakaj(0.002)
risar.cakaj(0.5)
risar.QMessageBox.information(None, "Chain Reaction", "VICTORY!")
