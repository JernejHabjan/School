def trdnjava(premiki):
    p = (0, 0)
    for kam, koliko in premiki:
        if kam == "U":
            p = (p[0] - int(koliko), p[1])
        elif kam == "D":
            p = (p[0] + int(koliko), p[1])
        elif kam == "L":
            p = (p[0], p[1] - int(koliko))
        elif kam == "R":
            p = (p[0], p[1] + int(koliko))
        if p == (0, 0):
            return True
    return False


def primerjaj(s, t):
    if len(s) == len(t):
        a = 0
        b = 0
        for x in range(len(s)):
            if s[x] < t[x]:
                a += 1
            if s[x] > t[x]:
                b += 1
        if len(s) == a:
            return -1
        if len(s) == b:
            return 1
        else:
            return 0
    return 0


from collections import defaultdict


def narocila(s):
    slovar = defaultdict(str)
    for ime, narocilo in s:
        if ime not in slovar:
            slovar[ime] = [narocilo]
        else:
            if narocilo[1:] not in slovar[ime]:
                slovar[ime].append(narocilo)
            else:
                slovar[ime].remove(narocilo[1:])
    return slovar


def crka(c, a):
    a = int(a) if a.isdigit() else a
    if a == "U":
        return c.capitalize()
    if type(a) is int:
        return a * c

    if a == ".":
        return c
    else:
        return ""


def kodiraj(beseda, koda):
    a = []
    for x, y in zip(beseda, koda):
        print(x)
        a.append(str(crka(x, y)))
    return "".join(a)


import unittest


class Stevilka:
    def __init__(self, vrednost, levo, desno):
        self.__vrednost = vrednost
        self.__levo, self.__desno = levo, desno

    def vrednost(self):
        return self.__vrednost

    def levi(self):
        return self.__levo

    def desni(self):
        return self.__desno


class StevilkaPlus(Stevilka):
    def najvecji(self):
        naj = self.vrednost()
        n = self.levi()
        if n:
            naj = max(naj, n.najvecji())
        n = self.desni()
        if n:
            naj = max(naj, n.najvecji())
        return naj


class TestTrdnjava(unittest.TestCase):
    def test_trdnjava(self):
        self.assertTrue(trdnjava(['U5', 'D5']))
        self.assertTrue(trdnjava(['R5', 'L5', 'R3']))
        self.assertTrue(trdnjava(['R5', 'U5', 'L5', 'D5', 'R5', 'U5']))
        self.assertTrue(trdnjava(['L3', 'U2', 'D2', 'R1', 'R1', 'R1',
                                  'R1', 'R1', 'R1', 'R1']))
        self.assertTrue(trdnjava(['U0']))
        self.assertTrue(trdnjava(['U0', 'U1']))

        self.assertFalse(trdnjava(['L3', 'U2', 'D2', 'R1', 'R1']))
        self.assertFalse(trdnjava(['R5', 'U4', 'L3']))


class TestPrimerjaj(unittest.TestCase):
    def test_primerjaj(self):
        self.assertEqual(primerjaj([1, 2, 3, 4], [2, 3, 4, 5]), -1)
        self.assertEqual(primerjaj([2, 3, 4, 5], [1, 2, 3, 4]), 1)
        self.assertEqual(primerjaj([1, 2, 3], [4, 5, 6, 7]), 0)
        self.assertEqual(primerjaj([4, 5, 6, 7], [1, 2, 3]), 0)
        self.assertEqual(primerjaj([1, 2, 3], [1, 2, 3]), 0)
        self.assertEqual(primerjaj([1, 2], [2, 1]), 0)
        self.assertEqual(primerjaj([2, 1], [1, 2]), 0)
        self.assertEqual(primerjaj([1], [2]), -1)
        self.assertEqual(primerjaj([2], [1]), 1)


class TestNatakar(unittest.TestCase):
    def test_narocila_brez_preklicev(self):
        self.assertDictEqual(narocila(
            [("Tone", "pivo")]),
            {"Tone": ["pivo"]})

        self.assertDictEqual(narocila(
            [("Tone", "pizza"), ("Tone", "sok")]),
            {"Tone": ["pizza", "sok"]})

        # Poskusi nakljucno generirano ime in jed!
        from random import choice
        ime = "".join(choice("abcdefghijklmnopqrstuvwxyz") for i in range(10))
        jed = "".join(choice("abcdefghijklmnopqrstuvwxyz") for i in range(10))
        self.assertDictEqual(narocila([(ime, jed)]), {ime: [jed]})

    def test_narocila(self):
        self.assertDictEqual(narocila(
            [('Ana', 'torta'), ('Berta', 'krof'), ('Cilka', 'kava'),
             ('Ana', 'kava'), ('Berta', '-krof'), ('Cilka', '-torta'),
             ('Berta', 'torta')]),
            {'Cilka': ['kava'], 'Berta': ['torta'], 'Ana': ['torta', 'kava']})

        self.assertDictEqual(narocila([('Ana', 'torta'), ('Ana', '-torta')]),
                             {'Ana': []})

        r = narocila([('Ana', '-torta')])
        self.assertTrue(r == {} or r == {'Ana': []})


class TestEditor(unittest.TestCase):
    def test_crka(self):
        self.assertEqual(crka("a", "U"), "A")
        self.assertEqual(crka("A", "U"), "A")
        self.assertEqual(crka("x", "U"), "X")

        self.assertEqual(crka("e", "."), "e")

        self.assertEqual(crka("t", "x"), "")

        self.assertEqual(crka("t", "0"), "")
        self.assertEqual(crka("t", "1"), "t")
        self.assertEqual(crka("t", "3"), "ttt")
        self.assertEqual(crka("t", "7"), "ttttttt")

    def test_kodiraj(self):
        self.assertEqual(kodiraj("beseda", ".U5.x."), "bEsssssea")
        self.assertEqual(kodiraj("beseda", "......"), "beseda")
        self.assertEqual(kodiraj("beseda", "xxxxxx"), "")
        self.assertEqual(kodiraj("beseda", "000000"), "")
        self.assertEqual(kodiraj("beseda", "111111"), "beseda")
        self.assertEqual(kodiraj("beseda", "010123"), "eeddaaa")

        self.assertEqual(kodiraj("foo", "U2x"), "Foo")
        self.assertEqual(kodiraj("", ""), "")


class StevilkaTest(unittest.TestCase):
    def test_stevilka(self):
        def build(s):
            s = isinstance(s, str) and list(s) or s
            c = s.pop(0)
            v = int(s.pop(0))
            return StevilkaPlus(v, build(s) if c in "BL" else None,
                                build(s) if c in "BR" else None)

        self.assertEqual(build("O3").najvecji(), 3,
                         "Vozlisce brez potomcev z vrednostjo 3")
        self.assertEqual(build("B3O2O2").najvecji(), 3,
                         "Vozlisce, z vrednostjo 3, oba potomca imata vrednost 2")
        self.assertEqual(build("B3O4O1").najvecji(), 4,
                         "Vozlisce, z vrednostjo 3, levi sin je 4, desni 1")
        self.assertEqual(build("B3O1O4").najvecji(), 4,
                         "Vozlisce, z vrednostjo 3, levi sin je 1, desni 4")
        self.assertEqual(build("L1L2L4O3").najvecji(), 4)
        self.assertEqual(build("L4L2L1O3").najvecji(), 4)
        self.assertEqual(build("L1L2L3O4").najvecji(), 4)
        self.assertEqual(build("R1R2L4O3").najvecji(), 4)
        self.assertEqual(build("R4R2L1O3").najvecji(), 4)
        self.assertEqual(build("R1R2L3O4").najvecji(), 4)
        self.assertEqual(build("R3B4O1O1").najvecji(), 4)
        self.assertEqual(build("B5B1L5O2B8B5O4O1O6O3").najvecji(), 8,
                         "Drevo s slike v nalogi")
