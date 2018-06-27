# https://ucilnica.fri.uni-lj.si/mod/page/view.php?id=7846
from math import sin, cos, radians

from Modules import risar
from PyQt4.QtCore import *
from PyQt4.QtGui import *


###KODA POD TURTLE

class Turtle(object):
    def __init__(self):
        self.x, self.y = risar.maxX // 2, risar.maxY // 2
        self.angle = 0
        self.penActive = True

        self.pause = 0
        self.body = risar.krog(0, 0, 5, risar.zelena, 3)
        self.head = risar.krog(0, 0, 2, risar.zelena, 3)
        self.update()

    def show(self):
        self.body.show()
        self.head.show()

    def hide(self):
        self.body.hide()
        self.head.hide()

    def update(self):
        angle = radians(90 - self.angle)
        self.body.setPos(self.x, self.y)
        self.head.setPos(self.x + 5 * cos(angle), self.y - 5 * sin(angle))
        if self.pause:
            self.wait(self.pause)

    def setPause(self, s):
        self.pause = s

    def noPause(self):
        self.setPause(0)

    def forward(self, l):
        angle = radians(90 - self.angle)
        nx, ny = self.x + l * cos(angle), self.y - l * sin(angle)
        nx, ny = max(0, nx), max(0, ny)
        nx, ny = min(nx, risar.maxX), min(ny, risar.maxY)
        if self.penActive:
            risar.crta(self.x, self.y, nx, ny)
        self.x, self.y = nx, ny
        self.update()

    def backward(self, l):
        self.forward(-l)

    def turn(self, angle):
        self.angle += angle
        self.update()

    def left(self):
        self.turn(-90)

    def right(self):
        self.turn(90)

    def penUp(self):
        self.penActive = False

    def penDown(self):
        self.penActive = True

    def fly(self, x, y, angle):
        self.x, self.y = x, y
        self.angle = angle
        self.update()

    def wait(self, s=0):
        risar.cakaj(s)


class ZelvaZImenom(Turtle):
    def __init__(self, ime):
        super().__init__()
        self.ime = ime

    def pozdrav(self):
        print("helllo world ime mi je ", self.ime)


class ZelvaZImenom(Turtle):
    def __init__(self, ime):
        super().__init__()
        self.ime = ime

    def pozdrav(self):
        print('Jaz sem želva', self.ime)


class Cveka(ZelvaZImenom):
    def forward(self, l):
        super().forward(l)
        print(self.ime, 'gre naprej', l)

    def turn(self, angle):
        super().turn(angle)
        print(self.ime, 'se obrača', angle)


class Rdecevratka(Turtle):
    def __init__(self):
        super().__init__()
        self.head.setPen(QPen(QBrush(risar.rdeca), 3))

    def forward(self, l):
        super().forward(l // 2)

    def hide(self):
        self.head.hide()


import random


class Pijanka(Turtle):
    def __init__(self):
        super().__init__()
        self.drinks = 0

    def drink(self):
        self.drinks += 1

    def forward(self, l):
        if self.drinks < 5:
            self.angle += random.randint(-self.drinks * 5, self.drinks * 5)
            super().forward(l)

    def turn(self, angle):
        if self.drinks < 5:
            super().turn(angle)


import math


class Pravokotnica(Turtle):
    def forward(self, l):
        angle = math.radians(90 - self.angle)
        nx, ny = l * math.cos(angle), l * math.sin(angle)

        angle_old = self.angle
        self.angle = 0
        super().forward(ny)
        self.angle = 90
        super().forward(nx)
        self.angle = angle_old
        self.update()

    # Razredu Turtle dodajte metodo __mul__
    def __mul__(self, other):
        if (self.x - other.x) ** 2 + (self.y - other.y) ** 2 > 10 ** 2:
            raise ConnectionRefusedError()
        return Turtle()
