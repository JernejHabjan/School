def skoki(s):
    i = s[0]
    a = 0
    for x in range(len(s)):
        i = s[i]

        if i == s[0]:
            return a + 1
        if s[i] > len(s):
            return -1
        a += 1


def transakcije(zacetek, dajatve):
    slovar = dict()
    for neki, ne in zacetek:
        slovar[neki] = ne
    for bla, bla1, bla2 in dajatve:
        slovar[bla] -= bla2
        slovar[bla1] += bla2
    naj = ["bla", 0]
    for x, y in slovar.items():
        if y > naj[1]:
            naj[0] = x
            naj[1] = y
    return naj[0]


def deli_niz(s, k):
    print(s, k)
    a = len(s)
    if len(s) % k == 0:
        b = int(len(s) / k)
        nova = []

        for x in range(b):
            nova.append(s[x])
        c = "".join(nova)
        if c * k == s:
            return c
    return None


def v_prav(pravokotnik, tocka):
    return True if pravokotnik[0] <= tocka[0] <= pravokotnik[2] and pravokotnik[1] <= tocka[1] <= pravokotnik[
        3] else False


def se_sekata(prav1, prav2):
    for x in prav1:
        for y in prav2:
            if v_prav(prav1, x):
                return False
            if v_prav(prav2, y):
                return False
    return True


# Razred za nalogo 5
class Vozlisce:
    def __init__(self, levo, desno):
        self.__levo, self.__desno = levo, desno

    def levi(self):
        return self.__levo

    def desni(self):
        return self.__desno


import unittest


class TestSkoki(unittest.TestCase):
    def test_skoki(self):
        self.assertEqual(skoki([3, 4, 0, 4, 2, 3]), 4)
        self.assertEqual(skoki([1, 2, 3, 4, 0]), 5)
        self.assertEqual(skoki([1, 1]), -2)
        self.assertEqual(skoki([1, 2, 3, 4, 5, 3]), -2)
        self.assertEqual(skoki([2, 42, 0]), 2)
        self.assertEqual(skoki([1, 2, 3, 8]), -1)
        self.assertEqual(skoki([0]), 1)


class TestTransakcije(unittest.TestCase):
    def test_transakcije(self):
        self.assertEqual(transakcije([("Ana", 0), ("Berta", 1)],
                                     [("Berta", "Ana", 1)]), "Ana")

        self.assertEqual(transakcije([("Ana", 0), ("Berta", 1)],
                                     []), "Berta")

        self.assertEqual(transakcije([("Ana", 3), ("Berta", 2)],
                                     [("Berta", "Berta", 3)]), "Ana")

        self.assertEqual(transakcije(
            [("Ana", 4), ("Berta", 8), ("Cilka", 10)],
            [("Cilka", "Ana", 3), ("Cilka", "Ana", 2), ("Ana", "Berta", 2)]
        ), "Berta")

        from random import choice
        ime = "".join(choice("abcdefghijklmnopqrstuvwxyz") for i in range(10))
        self.assertEqual(transakcije([(ime, 0), ("Cilka", 1)],
                                     [("Cilka", ime, 1)]), ime)


class TestDeliNiz(unittest.TestCase):
    def test_deli_niz(self):
        self.assertEqual(deli_niz("blablablabla", 4), "bla")
        self.assertEqual(deli_niz("abababab", 2), "abab")
        self.assertEqual(deli_niz("", 4), "")
        self.assertIsNone(deli_niz("uhohah", 3))
        self.assertEqual(deli_niz("uhohah", 1), "uhohah")
        self.assertIsNone(deli_niz("foofoofoo", 4))


class TestPravokotniki(unittest.TestCase):
    def test_v_prav(self):
        self.assertTrue(v_prav((1, 1, 5, 7), (2, 3)))
        self.assertTrue(v_prav((1, 1, 5, 7), (1, 1)))
        self.assertTrue(v_prav((1, 1, 5, 7), (5, 7)))

        self.assertFalse(v_prav((1, 1, 5, 7), (0, 1)))
        self.assertFalse(v_prav((1, 1, 5, 7), (1, 0)))
        self.assertFalse(v_prav((1, 1, 5, 7), (6, 1)))
        self.assertFalse(v_prav((1, 1, 5, 7), (1, 8)))
        self.assertFalse(v_prav((1, 1, 5, 7), (8, 8)))

        self.assertFalse(v_prav((1, 1, 3, 3), (0, 0)))
        self.assertFalse(v_prav((1, 1, 3, 3), (0, 2)))
        self.assertFalse(v_prav((1, 1, 3, 3), (0, 4)))
        self.assertFalse(v_prav((1, 1, 3, 3), (2, 0)))
        self.assertFalse(v_prav((1, 1, 3, 3), (2, 4)))
        self.assertFalse(v_prav((1, 1, 3, 3), (4, 0)))
        self.assertFalse(v_prav((1, 1, 3, 3), (4, 2)))
        self.assertFalse(v_prav((1, 1, 3, 3), (4, 4)))

    def test_se_sekata(self):
        self.assertTrue(se_sekata((1, 1, 5, 7), (2, 4, 6, 8)))
        self.assertTrue(se_sekata((2, 4, 6, 8), (1, 1, 5, 7)))
        self.assertTrue(se_sekata((1, 1, 5, 7), (1, 1, 5, 7)))
        self.assertTrue(se_sekata((1, 1, 5, 7), (0, 0, 1, 1)))
        self.assertTrue(se_sekata((0, 0, 1, 1), (1, 1, 5, 7)))
        self.assertTrue(se_sekata((0, 0, 9, 9), (1, 1, 2, 2)))
        self.assertTrue(se_sekata((1, 1, 2, 2), (0, 0, 9, 9)))

        self.assertFalse(se_sekata((1, 1, 5, 7), (3, 9, 5, 10)))
        self.assertFalse(se_sekata((1, 1, 5, 7), (-1, 4, 0, 5)))

    def test_sekajoci_par(self):
        self.assertEqual(
            tuple(sorted(sekajoci_par([(1, 1, 5, 7), (3, 9, 5, 10), (2, 4, 6, 8)]))),
            ((1, 1, 5, 7), (2, 4, 6, 8))
        )
        self.assertEqual(
            tuple(sorted(sekajoci_par([(1, 1, 5, 7), (2, 4, 6, 8)]))),
            ((1, 1, 5, 7), (2, 4, 6, 8))
        )
        self.assertEqual(sekajoci_par([(0, 0, 1, 1), (0, 0, 1, 1)]),
                         ((0, 0, 1, 1), (0, 0, 1, 1)))

        self.assertIsNone(sekajoci_par([(1, 1, 5, 7), (3, 9, 5, 10)]))
        self.assertIsNone(sekajoci_par([(1, 1, 5, 7)]))
        self.assertIsNone(sekajoci_par([]))


class VozlisceTest(unittest.TestCase):
    def test_globina(self):
        def build(s):
            s = isinstance(s, str) and list(s) or s
            c = s.pop(0)
            return VozliscePlus(build(s) if c in "BL" else None,
                                build(s) if c in "BR" else None)

        self.assertEqual(build("O").globina(), 0,
                         "Vozlisce brez potomcev ima globino 0")
        self.assertEqual(build("BOO").globina(), 1,
                         "Vozlisce, ki ima dva sinova brez sinov, ima globino 1")
        self.assertEqual(build("LLLO").globina(), 3,
                         "Vozlisce, ki ima levega sina, ta levega ima sina in ta ima levega sina, ima globino 3")
        self.assertEqual(build("RRRO").globina(), 3,
                         "Vozlisce, ki ima desnega sina, ta desnega ima sina in ta ima desnega sina, ima globino 3")
        self.assertEqual(build("BBLOBBOOOO").globina(), 4,
                         "Drevo s slike v nalogi ima globino 4")
        self.assertEqual(build("BLLLLLLLLLORRRRRRRRRO").globina(), 10)
        self.assertEqual(build("BBBBBOOBOOBBOOBOOBBBOOBOOBBOOBOOBBBBOOBOOBBOOBOOBBBOOBOOBBOOBOO").globina(), 5)
