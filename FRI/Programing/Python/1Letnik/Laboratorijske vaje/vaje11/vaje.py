# https://ucilnica.fri.uni-lj.si/mod/page/view.php?id=7821
# https://drive.google.com/drive/folders/0BwoDQsnfgapYdkVaYXVYQWk5WUk
# windowtranslation
import time
from math import *
from random import randint

from Modules import risar


def krogi_povezani(n):
    koord = []
    for x in range(n):
        kdiameter = randint(0, 50)
        kx = randint(kdiameter, risar.maxX - kdiameter)
        ky = randint(kdiameter, risar.maxY - kdiameter)
        koord.append((kx, ky))
        color = risar.barva(randint(50, 255), randint(50, 255), randint(50, 255))
        a = risar.krog(kx, ky, kdiameter, barva=color, sirina=1)
    for x1, y1 in koord:
        risar.obnavljaj = False
        for x2, y2 in koord:
            barva = risar.barva(randint(0, 255), randint(0, 255), randint(0, 255))
            sirina = randint(2, 2)
            risar.crta(x1, y1, x2, y2, barva, sirina)
    risar.stoj()


# krogi_povezani(30)
###########################################################################################
def kroznice_na_kroznici(n):
    max_iter = n
    diameter = 100
    alpha = 2 * math.pi / max_iter
    l = []
    for i in range(max_iter):
        l.append((math.cos(i * alpha), math.sin(i * alpha)))
    seznam = []
    for kx, ky in l:
        x = kx * diameter + risar.maxX / 2
        y = ky * diameter + risar.maxY / 2
        seznam.append((x, y))
        dia = math.pi * diameter / max_iter
        risar.krog(x, y, dia, barva=risar.barva(255, 255, 0))
        barva = risar.barva(randint(0, 255), randint(0, 255), randint(0, 255))
        sirina = randint(2, 2)
    for x2, y2 in seznam:
        for x3, y3 in seznam:
            risar.crta(x3, y3, x2, y2, barva, sirina)
    risar.stoj()


# kroznice_na_kroznici(20)
################################################################
def nesekajoci_se_krogi(n, max_cirlces):
    seznam = []
    for i in range(n + 1):

        barv = risar.barva(randint(0, 255), randint(0, 255), randint(0, 255), 128)
        risar.barvaOzadja(barv)
        kdiameter = randint(0, 500)
        x = risar.maxX / 2
        y = risar.maxY / 2
        a = risar.krog(x, y, kdiameter, barv, sirina=50)
        seznam.append(a)
        time.sleep(0.05)
        print(i - max_cirlces)
        if i > max_cirlces:
            print(i - max_cirlces)
            seznam[i - max_cirlces].hide()

            if i < i - max_cirlces:
                del (seznam[0])
        print(len(seznam))

    risar.stoj()


# nesekajoci_se_krogi(1000,50)
"""
quadric = lambda x: x ** 2
pubic = lambda x: min([i ** 2 for i in x])

print(pubic([2,43,43,634,6,346]))
"""
risar.obnavljaj = False


def pajkova(n):
    koordinate = []
    i = 0
    barv1 = risar.barva(16, 36, 27)
    risar.barvaOzadja(barv1)

    while i <= n:
        kdiameter = randint(2, 50)
        kx = randint(0, risar.maxX - 2 * kdiameter)
        ky = randint(0, risar.maxY - 2 * kdiameter)
        x = kx + kdiameter
        y = ky + kdiameter
        barv = risar.barva(randint(100, 255), randint(100, 255), randint(100, 255))

        for x1, y1, dia in koordinate:
            d = sqrt((x1 - x) ** 2 + (y1 - y) ** 2)
            r2 = d - dia
            if r2 < kdiameter:
                kdiameter = r2

        if kdiameter > 0:
            sirina = randint(5, 10)
            a = risar.krog(x, y, kdiameter - sirina / 2, barv, sirina)
            koordinate.append((x, y, kdiameter))
            i += 1
    risar.shrani("lol1.jpg")
    risar.stoj()

# print(pajkova(1000))
