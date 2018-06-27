# https://ucilnica.fri.uni-lj.si/mod/page/view.php?id=7844
from math import sin, cos, radians

from Modules import risar


class Turtle(object):
    def __init__(self):
        self.x, self.y = risar.maxX // 2, risar.maxY // 2
        self.angle = 0
        self.penActive = True

        self.pause = 0
        self.body = risar.krog(0, 0, 5, risar.zelena, 3)
        self.head = risar.krog(0, 0, 2, risar.zelena, 3)
        self.indicator = risar.krog(0, 0, 2, risar.rumena, 3)  #######

        self.update()

        self.color = risar.barva(100, 100, 100)
        self.width = 5

        self.narisan_seznam = []  ###############

    def show(self):
        self.body.show()
        self.head.show()
        if self.penActive == True:
            self.indicator.show()  ###################

    def hide(self):
        self.body.hide()
        self.head.hide()
        self.indicator.hide()  ################

    #######################################################################################    4
    def stamp(self):
        t = Turtle()  ####################################################################################################3 STAMP NAJTEŽJA
        t.x = self.x
        t.y = self.y
        t.angle = self.angle
        t.update()

    def clearStamps(self):
        for stamp in self.narisan_seznam:
            stamp.hide()
        del self.narisan_seznam[:]

    def update(self):
        angle = radians(90 - self.angle)
        self.body.setPos(self.x, self.y)
        self.head.setPos(self.x + 5 * cos(angle), self.y - 5 * sin(angle))
        self.indicator.setPos(self.x, self.y)  #########################
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
            risar.crta(self.x, self.y, nx, ny, self.color, self.width)
        self.x, self.y = nx, ny
        self.update()

    def backward(self, l):
        self.forward(-l)

    ###########################################################################  2
    def setWidth(self, width):
        self.width = width

    ######################################################################2
    def setColor(self, r, g, b):
        self.color = risar.barva(r, g, b)

    def turn(self, angle):
        self.angle += angle
        self.update()

    ##############################################################################    1
    def turnAround(self):
        self.angle += 180
        self.update()

    def left(self):
        self.turn(-90)

    def right(self):
        self.turn(90)

    def penUp(self):
        self.penActive = False
        self.indicator.hide()  #########

    def penDown(self):
        self.penActive = True
        self.indicator.show()  ########

    def fly(self, x, y, angle):
        self.x, self.y = x, y
        self.angle = angle
        self.update()

    def wait(self, s=0):
        risar.cakaj(s)


t = Turtle()
for x in range(100):
    t.forward(20)
    t.stamp()
    t.turn(3.66)

t.stamp()

risar.stoj()
