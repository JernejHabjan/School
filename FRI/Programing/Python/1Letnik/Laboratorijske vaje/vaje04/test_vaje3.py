__author__ = 'jh0228'


# https://ucilnica.fri.uni-lj.si/mod/page/view.php?id=7674
def naj(xs):
    najvecja = xs[0]
    for stevilo in xs:
        if stevilo > najvecja:
            najvecja = stevilo
    return najvecja


from math import *


def naj_abs(xs):
    najvecje = 0
    for stevila in xs:
        if sqrt(najvecje ** 2) < sqrt(stevila ** 2):
            najvecje = stevila
    return najvecje


def ostevilci(xs):
    i = 0
    seznam = []
    for i in range(len(xs)):
        seznam.append((i, xs[i]))
    return seznam


def bmi(osebe):
    seznam = []
    for ime, teza, visina in osebe:
        indeks_telesne = teza / (visina / 100) ** 2
        seznam.append((ime, indeks_telesne))
    return seznam


def bmi2(imena, teze, visine):
    seznam = []
    for ime, teza, visina in zip(imena, teze, visine):
        indeks_telesne = teza / (visina / 100) ** 2
        seznam.append((ime, indeks_telesne,))
    return seznam


def is_prime(n):
    for i in range(2, n):
        if n % i == 0:
            return False
    return True


def prastevila(n):
    seznam = []
    for stevila in range(2, n):
        if is_prime(stevila):
            seznam.append(stevila)
    return len(seznam)


def palindrom(s):
    if not len(s):
        return True
    elif s == s[::-1]:
        return True
    return False


def palindromska_stevila():
    maksimum = 0
    for stevilo1 in range(100, 1000):
        for stevilo2 in range(100, 1000):
            produkt = stevilo1 * stevilo2
            if palindrom(str(produkt)) and produkt > maksimum:
                maksimum = produkt
    return maksimum


def inverzije(xs):
    counter = 0
    for indeks1 in range(len(xs) - 1):
        for indeks2 in range(indeks1, len(xs)):
            if xs[indeks1] > xs[indeks2]:
                counter += 1
    return counter


def an_ban_pet_podgan(xs):
    zlogi = 0
    while len(xs) != 1:
        for j in range(10):
            zlogi += 1
            if zlogi == len(xs):
                zlogi = 0
        xs.pop(zlogi)
        if zlogi == len(xs):  # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! FUCKED NALOGA!!!!!
            zlogi = 0  # ZARADI TAZADNJEGA... zlogi = 2 xs = ["maja", "j"] , če zbrišemo tistega, na kerem imamo indeks
    return xs[0]


### ^^^ Naloge rešujte nad tem komentarjem. ^^^ ###

import unittest


class TestVaje(unittest.TestCase):
    def test_naj(self):
        self.assertEqual(naj([1]), 1)
        self.assertEqual(naj([-1]), -1)
        self.assertEqual(naj([5, 1, -6, -7, 2]), 5)
        self.assertEqual(naj([1, -6, -7, 2, 5]), 5)
        self.assertEqual(naj([-5, -1, -6, -7, -2]), -1)
        self.assertEqual(naj([1, 2, 5, 6, 10, 2, 3, 4, 9, 9]), 10)
        self.assertEqual(naj([-10 ** 10, -10 ** 9]), -10 ** 9)

    def test_naj_abs(self):
        self.assertEqual(naj_abs([1]), 1)
        self.assertEqual(naj_abs([-1]), -1)
        self.assertEqual(naj_abs([10, 12, 9]), 12)
        self.assertEqual(naj_abs([0, 0, 0, 0, 0]), 0)
        self.assertEqual(naj_abs([5, 1, -6, -7, 2]), -7)
        self.assertEqual(naj_abs([1, -6, 5, 2, -7]), -7)
        self.assertEqual(naj_abs([-5, -1, -6, -7, -2]), -7)
        self.assertEqual(naj_abs([100, 1, 5, 3, -90, 3]), 100)
        self.assertEqual(naj_abs([-100, 1, 5, 3, -90, 3]), -100)
        self.assertEqual(naj_abs([-10 ** 10, -10 ** 9]), -10 ** 10)
        self.assertEqual(naj_abs([1, 2, 5, 6, 10, 2, 3, 4, 9, 9]), 10)
        self.assertEqual(naj_abs([1, 2, 5, 6, -10, 2, 3, 4, 9, 9]), -10)

    def test_ostevilci(self):
        self.assertEqual(list(ostevilci([])), [])
        self.assertEqual(list(ostevilci([1])), [(0, 1)])
        self.assertEqual(list(ostevilci([5, 1, 4, 2, 3])), [(0, 5), (1, 1), (2, 4), (3, 2), (4, 3)])

    def test_bmi(self):
        in_out = [
            ([], []),
            ([('Ana', 55, 165), ('Berta', 60, 153)],
             [('Ana', 20.202020202020204), ('Berta', 25.63116749967961)]),
            ([('Ana', 55, 165), ('Berta', 60, 153), ('Cilka', 70, 183)],
             [('Ana', 20.202020202020204), ('Berta', 25.63116749967961), ('Cilka', 20.902385858042937)]),
        ]
        for i, o in in_out:
            for (nu, bu), (n, b) in zip(bmi(i), o):
                self.assertEqual(nu, n)
                self.assertAlmostEqual(bu, b)

    def test_bmi2(self):
        in_out = [
            (([], [], []), []),
            ((['Ana', 'Berta'], [55, 60], [165, 153]),
             [('Ana', 20.202020202020204), ('Berta', 25.63116749967961)]),
            ((['Ana', 'Berta', 'Cilka'], [55, 60, 70], [165, 153, 183]),
             [('Ana', 20.202020202020204), ('Berta', 25.63116749967961), ('Cilka', 20.902385858042937)]),
        ]
        for i, o in in_out:
            for (nu, bu), (n, b) in zip(bmi2(*i), o):
                self.assertEqual(nu, n)
                self.assertAlmostEqual(bu, b)

    def test_prastevila(self):
        self.assertEqual(prastevila(10), 4)
        self.assertEqual(prastevila(11), 4)
        self.assertEqual(prastevila(12), 5)
        self.assertEqual(prastevila(50), 15)
        self.assertEqual(prastevila(100), 25)
        self.assertEqual(prastevila(1000), 168)

    #    def test_prastevila_hard(self):
    #        self.assertEqual(prastevila(10**6), 78498)
    #        self.assertEqual(prastevila(10**7), 664579)

    def test_palindrom(self):
        self.assertEqual(palindrom(''), True)
        self.assertEqual(palindrom('a'), True)
        self.assertEqual(palindrom('aa'), True)
        self.assertEqual(palindrom('ab'), False)
        self.assertEqual(palindrom('aba'), True)
        self.assertEqual(palindrom('abc'), False)
        self.assertEqual(palindrom('abcdefedcba'), True)
        self.assertEqual(palindrom('abcdefgedcba'), False)
        self.assertEqual(palindrom('pericarezeracirep'), True)
        self.assertEqual(palindrom('perica'), False)

    def test_palindromska_stevila(self):
        self.assertEqual(palindromska_stevila(), 906609)

    def test_inverzije(self):
        self.assertEqual(inverzije([]), 0)
        self.assertEqual(inverzije([1]), 0)
        self.assertEqual(inverzije([1, 2]), 0)
        self.assertEqual(inverzije([2, 1]), 1)
        self.assertEqual(inverzije([3, 2, 1]), 3)
        self.assertEqual(inverzije([4, 3, 2, 1]), 6)
        self.assertEqual(inverzije([5, 4, 3, 2, 1]), 10)
        self.assertEqual(inverzije([1, 4, 3, 5, 2]), 4)
        self.assertEqual(inverzije([10, 3, 9, 2, 22, 42, 0, 88, 66]), 12)

    def test_an_ban_pet_podgan(self):
        self.assertEqual(an_ban_pet_podgan(["Maja"]), "Maja")
        self.assertEqual(an_ban_pet_podgan(["Maja", "Janja", "Sabina"]), "Maja")
        self.assertEqual(an_ban_pet_podgan(["Maja", "Janja", "Sabina", "Ina"]), "Ina")
        self.assertEqual(an_ban_pet_podgan(["Maja", "Janja", "Sabina", "Ina", "Jasna"]), "Jasna")
        self.assertEqual(an_ban_pet_podgan(["Maja", "Janja", "Sabina", "Ina", "Jasna", "Mojca"]), "Ina")
        self.assertEqual(an_ban_pet_podgan(["Maja", "Janja", "Sabina", "Ina", "Jasna", "Mojca", "Tina"]), "Maja")


if __name__ == '__main__':
    unittest.main(verbosity=2)
