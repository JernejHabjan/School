def visina(plosca):
    return len(plosca)


def sirina(plosca):
    return len(plosca[0])


def polj(plosca):
    return sirina(plosca) * visina(plosca)


def na_plosci(plosca, x, y):
    return True if 0 <= x < sirina(plosca) and 0 <= y < visina(plosca) else False


def preberi(plosca, x, y):
    a = [stev for stev in plosca[y][x]]
    return (a[0], int("".join(a[1:])))


def premakni(plosca, x, y):
    w = 0
    z = 0
    slovar = dict()
    a = ("".join([stev for stev in plosca[y][x]]))
    for i, neki in enumerate(plosca):
        for j, neki1 in enumerate(neki):
            if j == x and i == y:
                w = j
                z = i
    if a[0] == "J":
        z += abs(int(a[1:]))
    if a[0] == "S":
        z -= abs(int(a[1:]))
    if a[0] == "Z":
        w -= abs(int(a[1:]))
    if a[0] == "V":
        w += abs(int(a[1:]))
    return (w, z)


def dolzina_poti(plosca, x, y):
    plus = 0
    a = x
    b = y
    while na_plosci(plosca, a, b):
        a, b = premakni(plosca, a, b)
        plus += 1
    return plus


def pot(plosca, x, y):
    seznam = []
    a = x
    b = y
    while na_plosci(plosca, a, b):
        seznam.append((a, b))
        a, b = premakni(plosca, a, b)
        if len(seznam) > 9:
            break
    return seznam


def izberi(plosca, lastnost):
    a = set()
    for i in range(sirina(plosca)):
        for j in range(visina(plosca)):
            if lastnost(plosca, i, j):
                a.add((i, j))
    return a


def ciklicno(plosca, x, y):
    plus = 0
    a = x
    b = y
    while na_plosci(plosca, a, b) and plus < 11:
        a, b = premakni(plosca, a, b)
        plus += 1
    if plus > 10:
        return True
    else:
        return False


def ciklicna(plosca):
    return izberi(plosca, ciklicno)


def vrnljivo(plosca, x, y):
    for bla in pot(plosca, x, y)[1:]:
        if bla == pot(plosca, x, y)[0]:
            return True
    else:
        return False


def vrnljiva(plosca):
    return izberi(plosca, vrnljivo)


def varno(plosca, x, y):
    x1, y1 = premakni(plosca, x, y)
    if na_plosci(plosca, x1, y1):
        return True
    else:
        return False


def varna(plosca):
    return izberi(plosca, varno)


def dolzina_cikla(plosca, x, y):
    return


def veckratnik_ciklov(plosca, x, y):
    return


def igra(plosca, pozicije):
    return


def igra(plosca, zacetki):
    return


import unittest


class TestOcena05(unittest.TestCase):
    def test_visina(self):
        self.assertEqual(visina([["V1", "V1"],
                                 ["V2", "V2"],
                                 ["V3", "V3"]]), 3)

        self.assertEqual(visina([["V1", "V1"],
                                 ["V2", "V2"],
                                 ["V3", "V3"],
                                 ["V4", "V4"],
                                 ["V4", "V4"]]), 5)

        self.assertEqual(visina([["V1", "V1", "V1", "V1", "V1"]]), 1)

        self.assertEqual(visina([["V1"]]), 1)

    def test_sirina(self):
        self.assertEqual(sirina([["V1", "V1"],
                                 ["V2", "V2"],
                                 ["V3", "V3"]]), 2)

        self.assertEqual(sirina([["V1", "V1"],
                                 ["V2", "V2"],
                                 ["V3", "V3"],
                                 ["V4", "V4"],
                                 ["V4", "V2"]]), 2)

        self.assertEqual(sirina([["V1", "V1", "V1", "V1", "V1"]]), 5)

        self.assertEqual(sirina([["V1", "V1", "V1", "V1", "V1"],
                                 ["V2", "V2", "V2", "V2", "V2"]]), 5)

        self.assertEqual(sirina([["V1", "V2", "V3", "V4", "V5"]]), 5)

        self.assertEqual(sirina([["V1"]]), 1)

    def test_polj(self):
        self.assertEqual(polj([["V1", "V1"],
                               ["V2", "V2"],
                               ["V3", "V3"]]), 6)

        self.assertEqual(polj([["V1", "V1"],
                               ["V2", "V2"],
                               ["V3", "V3"],
                               ["V4", "V4"],
                               ["V4", "V2"]]), 10)

        self.assertEqual(polj([["V1", "V1", "V1", "V1", "V1"]]), 5)

        self.assertEqual(polj([["V1", "V1", "V1", "V1", "V1"],
                               ["V2", "V2", "V2", "V2", "V2"]]), 10)

        self.assertEqual(polj([["V1", "V2", "V3", "V4", "V5"]]), 5)

        self.assertEqual(polj([["V1"]]), 1)

    def test_na_plosci(self):
        plosca = [["V1", "V1"],
                  ["V2", "V2"],
                  ["V3", "V3"]]
        self.assertTrue(na_plosci(plosca, 0, 0))
        self.assertTrue(na_plosci(plosca, 1, 0))
        self.assertTrue(na_plosci(plosca, 0, 1))
        self.assertTrue(na_plosci(plosca, 1, 2))

        self.assertFalse(na_plosci(plosca, 0, 3))
        self.assertFalse(na_plosci(plosca, 2, 0))

        self.assertFalse(na_plosci(plosca, 1, 5))
        self.assertFalse(na_plosci(plosca, 1, -5))
        self.assertFalse(na_plosci(plosca, -10, -5))
        self.assertFalse(na_plosci(plosca, 10, 5))
        self.assertFalse(na_plosci(plosca, -10, 0))

        plosca = [["V1"] * 5] * 12
        self.assertTrue(na_plosci(plosca, 4, 11))
        self.assertTrue(na_plosci(plosca, 0, 0))
        self.assertFalse(na_plosci(plosca, 5, 11))
        self.assertFalse(na_plosci(plosca, 4, 12))
        self.assertFalse(na_plosci(plosca, 5, 12))
        self.assertFalse(na_plosci(plosca, -1, -1))

    def test_preberi(self):
        plosca = [["J2", "Z12", "J1"],
                  ["V2", "V1", "S2"],
                  ["S1", "J1345", "S1"]]
        self.assertEqual(preberi(plosca, 0, 1), ("V", 2))
        self.assertEqual(preberi(plosca, 2, 1), ("S", 2))
        self.assertEqual(preberi(plosca, 0, 2), ("S", 1))
        self.assertEqual(preberi(plosca, 1, 0), ("Z", 12))
        self.assertEqual(preberi(plosca, 1, 2), ("J", 1345))


class Plosce(unittest.TestCase):
    def setUp(self):
        self.plosca1 = [["J2", "Z1", "J1"],
                        ["V2", "V1", "S2"],
                        ["S1", "Z1", "S1"]]

        self.plosca2 = [["J2", "Z1", "V1"],
                        ["S1", "V1", "J1"],
                        ["S1", "S1", "Z1"]]

        self.plosca3 = [["J1", "J2"],
                        ["J1", "V1"],
                        ["S1", "S1"]]

        self.plosca4 = [["V2", "V1", "Z1", "J1"]]

        self.plosca5 = [["J2"], ["J1"], ["S1"], ["V1"]]

        self.vsa = {(x, y) for x in range(3) for y in range(3)}


class TestOcena06(Plosce):
    def test_premakni(self):
        plosca = [["J2", "Z12", "J1"],
                  ["V2", "V1", "S2"],
                  ["S1", "J1345", "S1"]]
        self.assertTupleEqual(premakni(plosca, 0, 0), (0, 2))
        self.assertTupleEqual(premakni(plosca, 1, 0), (-11, 0))
        self.assertTupleEqual(premakni(plosca, 2, 0), (2, 1))
        self.assertTupleEqual(premakni(plosca, 0, 1), (2, 1))
        self.assertTupleEqual(premakni(plosca, 1, 1), (2, 1))
        self.assertTupleEqual(premakni(plosca, 2, 1), (2, -1))
        self.assertTupleEqual(premakni(plosca, 0, 2), (0, 1))
        self.assertTupleEqual(premakni(plosca, 1, 2), (1, 1347))
        self.assertTupleEqual(premakni(plosca, 2, 2), (2, 1))

    def test_dolzina_poti(self):
        plosca = self.plosca1
        self.assertEqual(dolzina_poti(plosca, 0, 0), 4)
        self.assertEqual(dolzina_poti(plosca, 1, 0), 5)
        self.assertEqual(dolzina_poti(plosca, 2, 0), 2)
        self.assertEqual(dolzina_poti(plosca, 0, 1), 2)
        self.assertEqual(dolzina_poti(plosca, 1, 1), 2)
        self.assertEqual(dolzina_poti(plosca, 2, 1), 1)
        self.assertEqual(dolzina_poti(plosca, 0, 2), 3)
        self.assertEqual(dolzina_poti(plosca, 1, 2), 4)
        self.assertEqual(dolzina_poti(plosca, 2, 2), 2)

        plosca = [["J2", "Z1"]]
        self.assertEqual(dolzina_poti(plosca, 0, 0), 1)
        self.assertEqual(dolzina_poti(plosca, 1, 0), 2)

        self.assertEqual(dolzina_poti([["Z2"]], 0, 0), 1)


class TestOcena07(Plosce):
    def test_pot(self):
        potl = lambda *x: list(pot(*x))

        plosca = self.plosca1
        self.assertEqual(potl(plosca, 0, 0), [(0, 0), (0, 2), (0, 1), (2, 1)])
        self.assertEqual(potl(plosca, 1, 0), [(1, 0), (0, 0), (0, 2), (0, 1), (2, 1)])
        self.assertEqual(potl(plosca, 2, 0), [(2, 0), (2, 1)])
        self.assertEqual(potl(plosca, 0, 1), [(0, 1), (2, 1)])
        self.assertEqual(potl(plosca, 1, 1), [(1, 1), (2, 1)])
        self.assertEqual(potl(plosca, 2, 1), [(2, 1)])
        self.assertEqual(potl(plosca, 0, 2), [(0, 2), (0, 1), (2, 1)])
        self.assertEqual(potl(plosca, 1, 2), [(1, 2), (0, 2), (0, 1), (2, 1)])
        self.assertEqual(potl(plosca, 2, 2), [(2, 2), (2, 1)])

        plosca = [["J2", "Z1"]]
        self.assertEqual(potl(plosca, 0, 0), [(0, 0)])
        self.assertEqual(potl(plosca, 1, 0), [(1, 0), (0, 0)])

        self.assertEqual(potl([["Z2"]], 0, 0), [(0, 0)])


class TestOcena08(Plosce):
    def test_ciklicno(self):
        for x in range(3):
            for y in range(3):
                self.assertFalse(ciklicno(self.plosca1, x, y))
                if (x, y) != (2, 0):
                    self.assertTrue(ciklicno(self.plosca2, x, y))
        self.assertFalse(ciklicno(self.plosca2, 2, 0))

        self.assertTrue(ciklicno(self.plosca3, 0, 0))
        self.assertTrue(ciklicno(self.plosca3, 0, 1))
        self.assertTrue(ciklicno(self.plosca3, 0, 2))
        self.assertFalse(ciklicno(self.plosca3, 1, 0))
        self.assertFalse(ciklicno(self.plosca3, 1, 1))
        self.assertFalse(ciklicno(self.plosca3, 1, 2))

        self.assertTrue(ciklicno(self.plosca4, 0, 0))
        self.assertTrue(ciklicno(self.plosca4, 1, 0))
        self.assertTrue(ciklicno(self.plosca4, 2, 0))
        self.assertFalse(ciklicno(self.plosca4, 3, 0))

        self.assertTrue(ciklicno(self.plosca5, 0, 0))
        self.assertTrue(ciklicno(self.plosca5, 0, 1))
        self.assertTrue(ciklicno(self.plosca5, 0, 2))
        self.assertFalse(ciklicno(self.plosca5, 0, 3))

    def test_ciklicna(self):
        self.assertSetEqual(ciklicna(self.plosca1), set())
        self.assertSetEqual(ciklicna(self.plosca2), self.vsa - {(2, 0)})
        self.assertSetEqual(ciklicna(self.plosca3), {(0, 0), (0, 1), (0, 2)})
        self.assertSetEqual(ciklicna(self.plosca4), {(0, 0), (1, 0), (2, 0)})
        self.assertSetEqual(ciklicna(self.plosca5), {(0, 0), (0, 1), (0, 2)})

    def test_vrnljivo(self):
        for x in range(3):
            for y in range(3):
                self.assertFalse(vrnljivo(self.plosca1, x, y))
                if (x, y) not in {(1, 0), (2, 0)}:
                    self.assertTrue(vrnljivo(self.plosca2, x, y))
                else:
                    self.assertFalse(vrnljivo(self.plosca2, x, y))
        self.assertFalse(vrnljivo(self.plosca3, 0, 0))
        self.assertTrue(vrnljivo(self.plosca3, 0, 1))
        self.assertTrue(vrnljivo(self.plosca3, 0, 2))
        self.assertFalse(vrnljivo(self.plosca3, 1, 0))
        self.assertFalse(vrnljivo(self.plosca3, 1, 1))
        self.assertFalse(vrnljivo(self.plosca3, 1, 2))

        self.assertFalse(vrnljivo(self.plosca4, 0, 0))
        self.assertTrue(vrnljivo(self.plosca4, 1, 0))
        self.assertTrue(vrnljivo(self.plosca4, 2, 0))
        self.assertFalse(vrnljivo(self.plosca4, 3, 0))

        self.assertFalse(vrnljivo(self.plosca5, 0, 0))
        self.assertTrue(vrnljivo(self.plosca5, 0, 1))
        self.assertTrue(vrnljivo(self.plosca5, 0, 2))
        self.assertFalse(vrnljivo(self.plosca5, 0, 3))

    def test_vrnljiva(self):
        self.assertSetEqual(vrnljiva(self.plosca1), set())
        self.assertSetEqual(vrnljiva(self.plosca2), self.vsa - {(1, 0), (2, 0)})
        self.assertSetEqual(vrnljiva(self.plosca3), {(0, 1), (0, 2)})
        self.assertSetEqual(vrnljiva(self.plosca4), {(1, 0), (2, 0)})
        self.assertSetEqual(vrnljiva(self.plosca5), {(0, 1), (0, 2)})

    def test_varno(self):
        for x in range(3):
            for y in range(3):
                if (x, y) != (2, 1):
                    self.assertTrue(varno(self.plosca1, x, y))
                if (x, y) != (2, 0):
                    self.assertTrue(varno(self.plosca2, x, y))
        self.assertFalse(varno(self.plosca1, 2, 1))
        self.assertFalse(varno(self.plosca2, 2, 0))

    def test_varna(self):
        self.assertSetEqual(varna(self.plosca1), self.vsa - {(2, 1)})
        self.assertSetEqual(varna(self.plosca2), self.vsa - {(2, 0)})
        self.assertSetEqual(varna(self.plosca3),
                            {(0, 0), (1, 0), (0, 1), (0, 2), (1, 2)})
        self.assertSetEqual(varna(self.plosca4), {(0, 0), (1, 0), (2, 0)})
        self.assertSetEqual(varna(self.plosca5), {(0, 0), (0, 1), (0, 2)})


class TestOcena_09(Plosce):
    def test_dolzina_cikla(self):
        for x in range(3):
            for y in range(3):
                self.assertIsNone(dolzina_cikla(self.plosca1, x, y))
                if x == 0 or (x, y) == (1, 0):
                    self.assertEqual(dolzina_cikla(self.plosca2, x, y), 3)
                elif (x, y) == (2, 0):
                    self.assertIsNone(dolzina_cikla(self.plosca2, x, y))
                else:
                    self.assertEqual(dolzina_cikla(self.plosca2, x, y), 4)

        self.assertEqual(dolzina_cikla(self.plosca3, 0, 0), 2)
        self.assertEqual(dolzina_cikla(self.plosca3, 0, 1), 2)
        self.assertEqual(dolzina_cikla(self.plosca3, 0, 2), 2)
        self.assertIsNone(dolzina_cikla(self.plosca3, 1, 0))
        self.assertIsNone(dolzina_cikla(self.plosca3, 1, 1))
        self.assertIsNone(dolzina_cikla(self.plosca3, 1, 2))

        self.assertEqual(dolzina_cikla(self.plosca4, 0, 0), 2)
        self.assertEqual(dolzina_cikla(self.plosca4, 1, 0), 2)
        self.assertEqual(dolzina_cikla(self.plosca4, 2, 0), 2)
        self.assertIsNone(dolzina_cikla(self.plosca4, 3, 0))

        self.assertEqual(dolzina_cikla(self.plosca5, 0, 0), 2)
        self.assertEqual(dolzina_cikla(self.plosca5, 0, 1), 2)
        self.assertEqual(dolzina_cikla(self.plosca5, 0, 2), 2)
        self.assertIsNone(dolzina_cikla(self.plosca5, 0, 3))

    def test_veckratnik_ciklov(self):
        self.assertEqual(veckratnik_ciklov(self.plosca1), 1)
        self.assertEqual(veckratnik_ciklov(self.plosca2), 12)
        self.assertEqual(veckratnik_ciklov(self.plosca3), 2)
        self.assertEqual(veckratnik_ciklov(self.plosca4), 2)
        self.assertEqual(veckratnik_ciklov(self.plosca5), 2)


class TestOcena_10(Plosce):
    def test_igra(self):
        # samo en igralec / single player
        self.assertTrue(igra(self.plosca1, [(2, 0)]) in [0, {0}])

        # prvi izloci drugega, se preden se le-ta premakne
        # the first eliminates the second even before the latter moves
        self.assertTrue(igra(self.plosca1, [(1, 0), (0, 0)]) in [0, {0}])

        # drugi "izloci" prvega  / the second removes the first
        self.assertTrue(igra(self.plosca1, [(2, 0), (2, 2)]) in [1, {1}])
        self.assertTrue(igra(self.plosca1, [(2, 2), (2, 0)]) in [1, {1}])

        # tisti, ki so blizje, padejo cez rob
        # those closer to the path fall off the board
        print(igra(self.plosca1, [(0, 2), (2, 2), (2, 0)]))
        self.assertTrue(
            igra(self.plosca1, [(0, 2), (2, 2), (2, 0)]) in [0, {0}])
        self.assertTrue(
            igra(self.plosca1, [(2, 2), (0, 2), (2, 0)]) in [1, {1}])
        self.assertTrue(
            igra(self.plosca1, [(2, 2), (2, 0), (0, 2)]) in [2, {2}])

        # drugi "izloci" prvega  / the second removes the first
        self.assertTrue(igra(self.plosca2, [(0, 1), (1, 0)]) in [1, {1}])
        self.assertTrue(igra(self.plosca2, [(1, 0), (0, 1)]) in [1, {1}])

        # drugi "izloci" prvega, eden pa pade ÄŤez
        # the second removes the first, and one falls off
        self.assertTrue(
            igra(self.plosca2, [(1, 0), (0, 1), (2, 0)]) in [1, {1}])
        self.assertTrue(
            igra(self.plosca2, [(2, 0), (1, 0), (0, 1)]) in [2, {2}])

        self.assertTrue(igra(self.plosca3, [(0, 0), (0, 1)]) in [0, {0}])
        self.assertTrue(igra(self.plosca3, [(0, 1), (0, 0)]) in [0, {0}])
        self.assertTrue(igra(self.plosca3, [(0, 1), (0, 2)]) in [0, {0}])
        self.assertTrue(igra(self.plosca3, [(0, 2), (0, 1)]) in [0, {0}])
        self.assertTrue(igra(self.plosca3, [(0, 2), (0, 0)]) in [1, {1}])
        self.assertTrue(igra(self.plosca3, [(0, 0), (0, 2)]) in [1, {1}])

        self.assertTrue(
            igra(self.plosca3, [(0, 0), (0, 2), (1, 0)]) in [1, {1}])
        self.assertTrue(
            igra(self.plosca3, [(0, 0), (1, 0), (0, 2)]) in [2, {2}])
        self.assertTrue(
            igra(self.plosca3, [(1, 0), (0, 0), (0, 2)]) in [2, {2}])


class TestOcena_11(Plosce):
    def test_igra(self):
        self.assertSetEqual(
            igra(self.plosca2, [(1, 0), (0, 1), (2, 0), (2, 2)]),
            {1, 3})
        self.assertSetEqual(
            igra(self.plosca2, [(1, 0), (0, 1), (2, 0), (2, 2), (1, 2)]),
            {1, 3})
        self.assertSetEqual(
            igra(self.plosca2, [(1, 0), (0, 1), (2, 0), (2, 2), (1, 2), (1, 1)]),
            {1, 3, 5})
        self.assertSetEqual(
            igra(self.plosca2, [(1, 1), (2, 1), (2, 2), (1, 2)]),
            {0, 2})


if __name__ == "__main__":
    unittest.main()
