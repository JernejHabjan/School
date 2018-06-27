__author__ = 'jh0228'


# pisal 74% :D


##########################################
def cokolada(n, odlomi):
    vsota = n * n
    stolpci = n
    vrstice = n
    for x in odlomi:
        if x[0] == "<":
            stolpci -= 1 * int(x[1])

        if x[0] == ">":
            stolpci -= 1 * int(x[1])

        if x[0] == "^":
            vrstice -= 1 * int(x[1])
        if x[0] == "v":
            vrstice -= 1 * int(x[1])
    return stolpci * vrstice if stolpci * vrstice > 0 else  0


# dodaj dvopicja za enkam in imaš narejeno

###################################

def zdruzi(xs):
    slovar = dict()
    for i, x in enumerate(xs):
        if x in slovar:
            slovar[x].add(i)
        else:
            slovar[x] = {i}

    return slovar


def razmeci(xs):
    slovar = {}
    for key1, value in xs.items():

        for value1 in value:
            if value1 in slovar:
                slovar[value1].add(key1)
            else:
                slovar[value1] = {key1}

    seznam = []
    for x, y in slovar.items():
        seznam.append((x, y))
    a = sorted(seznam)
    seznam2 = []
    for a, b in seznam:

        for x in b:
            seznam2.append(x)
    return seznam2


###############################################
def brez_jecljanja_rec(xs):
    seznam = [xs[0]]

    for x in xs:
        seznam2 = brez_jecljanja_rec(x) if x not in seznam else None
    return seznam + seznam2


def brez_jecljanja_gen(xs):
    return [xs[0] + x for i, x in enumerate(xs) if x != xs[i - 1]]


###########################################
def sopomena(stavek1, stavek2, sopomenke):
    a = len(stavek1.split())
    s = 0
    for x, y in zip(stavek1.split(), stavek2.split()):
        print(x, y)
        for mnozica in sopomenke:
            if x in mnozica and y in mnozica:
                s += 1

    print(s, a)
    if 2 * s == a:
        return True
    else:
        return False


##########################################


class Picerija:
    def __init__(self):

        self.pice = []
        self.stevilopic = len(self.pice)

    def speci(self):

        self.pice.append("margerita")
        self.pice.append("klasika")
        self.pice.append("zelenjavna")
        self.pice.append("siri")

    def prodaj(self):
        if len(self.pice):
            a = self.pice.pop(-1)
            return a
        else:
            return None

    def zasluzek(self):
        self.cena = 0
        for x in self.pice:
            if x == "margerita":
                self.cena += 1
            if x == "klasika":
                self.cena += 2
            if x == "zelenjavna":
                self.cena += 3
            if x == "siri":
                self.cena += 4
        return self.cena

    def len(self):
        return self.stevilopic

    def print(self):
        str = []
        for x in self.pice:
            str.append(x)
            str.append(">")
        return "".join(str[:-1])


# def __len__(Picerija):
#       return len(Picerija.stevilopic)

import unittest


class Naloga_1_Cokolada(unittest.TestCase):
    def test_cokolada(self):
        self.assertEqual(cokolada(10, ["<2", ">1", "^3", "^1", "v2", "<1"]), 24)
        self.assertEqual(cokolada(100, ["<20", ">13", "^3", "^18", "v12", "<1"]), 4422)
        self.assertEqual(cokolada(10, ["<2", ">8", "^3", "^1", "v2", "<1"]), 0)
        self.assertEqual(cokolada(10, ["<2"]), 80)
        self.assertEqual(cokolada(10, ["<12"]), 0)
        self.assertEqual(cokolada(6, []), 36)


class Naloga_2_ZdruziRazmeci(unittest.TestCase):
    def test_zdruzi(self):
        self.assertEqual(zdruzi([5, 1, 2, 5, 1, 8]), {5: {0, 3}, 1: {1, 4}, 2: {2}, 8: {5}})
        self.assertEqual(zdruzi([5, 1]), {5: {0}, 1: {1}})
        self.assertEqual(zdruzi([]), {})

    def test_razmeci(self):
        self.assertEqual(razmeci({5: {0, 3}, 1: {1, 4}, 2: {2}, 8: {5}}), [5, 1, 2, 5, 1, 8])
        self.assertEqual(razmeci({1: {2}, 0: {1, 3, 5}, 42: {0, 4}}), [42, 0, 1, 0, 42, 0])


class Naloga_3_BrezJecljanja(unittest.TestCase):
    def test_brez_jecljanja_rec(self):
        with self.assertRaises(RuntimeError,
                               msg="Funkcija mora biti rekurzivna"):
            brez_jecljanja_rec(list(range(2000)))
            brez_jecljanja_rec([0] * 2000)

        self.assertEqual(brez_jecljanja_rec([1, 1, 2, 3, 4, 1, 1, 1]), [1, 2, 3, 4, 1])
        self.assertEqual(brez_jecljanja_rec([8, 4, 7, 9, 4, 6]), [8, 4, 7, 9, 4, 6])
        self.assertEqual(brez_jecljanja_rec([42]), [42])
        self.assertEqual(brez_jecljanja_rec([42, 42, 42]), [42])
        self.assertEqual(brez_jecljanja_rec([]), [])
        self.assertEqual(brez_jecljanja_rec([1, 2, 3, 3, 4, 5]), [1, 2, 3, 4, 5])
        self.assertEqual(brez_jecljanja_rec([1, 2, 4, 3, 3, 5]), [1, 2, 4, 3, 5])
        self.assertEqual(brez_jecljanja_rec([1, 2, 4, 5, 3, 3]), [1, 2, 4, 5, 3])
        self.assertEqual(brez_jecljanja_rec([3, 3, 1, 2, 4, 5]), [3, 1, 2, 4, 5])
        self.assertEqual(brez_jecljanja_rec([3, 3, 1, 2, 3, 3, 4, 5, 3, 3]), [3, 1, 2, 3, 4, 5, 3])
        self.assertEqual(brez_jecljanja_rec([3, 3, 1, 2, 3, 4, 5, 3, 3]), [3, 1, 2, 3, 4, 5, 3])
        self.assertEqual(brez_jecljanja_rec([3, 3, 1, 2, 3, 4, 5, 3]), [3, 1, 2, 3, 4, 5, 3])
        self.assertEqual(brez_jecljanja_rec([3, 1, 2, 3, 4, 5, 3, 3]), [3, 1, 2, 3, 4, 5, 3])

        self.assertEqual(brez_jecljanja_rec(list("ABCAACCBA")), list("ABCACBA"))

    def test_brez_jecljanja_gen(self):
        self.assertEqual(brez_jecljanja_gen([1, 1, 2, 3, 4, 1, 1, 1]), [1, 2, 3, 4, 1])
        self.assertEqual(brez_jecljanja_gen([8, 4, 7, 9, 4, 6]), [8, 4, 7, 9, 4, 6])
        self.assertEqual(brez_jecljanja_gen([42]), [42])
        self.assertEqual(brez_jecljanja_gen([42, 42, 42]), [42])
        self.assertEqual(brez_jecljanja_gen([]), [])
        self.assertEqual(brez_jecljanja_gen([1, 2, 3, 3, 4, 5]), [1, 2, 3, 4, 5])
        self.assertEqual(brez_jecljanja_gen([1, 2, 4, 3, 3, 5]), [1, 2, 4, 3, 5])
        self.assertEqual(brez_jecljanja_gen([1, 2, 4, 5, 3, 3]), [1, 2, 4, 5, 3])
        self.assertEqual(brez_jecljanja_gen([3, 3, 1, 2, 4, 5]), [3, 1, 2, 4, 5])
        self.assertEqual(brez_jecljanja_gen([3, 3, 1, 2, 3, 3, 4, 5, 3, 3]), [3, 1, 2, 3, 4, 5, 3])
        self.assertEqual(brez_jecljanja_gen([3, 3, 1, 2, 3, 4, 5, 3, 3]), [3, 1, 2, 3, 4, 5, 3])
        self.assertEqual(brez_jecljanja_gen([3, 3, 1, 2, 3, 4, 5, 3]), [3, 1, 2, 3, 4, 5, 3])
        self.assertEqual(brez_jecljanja_gen([3, 1, 2, 3, 4, 5, 3, 3]), [3, 1, 2, 3, 4, 5, 3])

        self.assertEqual(brez_jecljanja_gen(list("ABCAACCBA")), list("ABCACBA"))


class Naloga_4_Sopomenke(unittest.TestCase):
    def test_sopomena(self):
        sinonimi = [{"fant", "deček", "pob"},
                    {"dekle", "punca"},
                    {"cesta", "pot", "kolovoz", "makadam"},
                    {"kis", "jesih"},
                    {"noge", "tace"}]
        self.assertTrue(
            sopomena("pob in dekle sta vzela pot pod noge",
                     "pob in dekle sta vzela pot pod noge", sinonimi))
        self.assertTrue(
            sopomena("pob in dekle sta vzela pot pod noge",
                     "fant in punca sta vzela pot pod tace", sinonimi))
        self.assertTrue(
            sopomena("pob in dekle sta vzela kolovoz pod noge",
                     "fant in punca sta vzela pot pod tace", sinonimi))
        self.assertTrue(
            sopomena("fant in punca sta vzela pot pod tace",
                     "pob in dekle sta vzela pot pod noge", sinonimi))
        self.assertTrue(
            sopomena("fant in punca sta vzela pot pod tace",
                     "pob in dekle sta vzela kolovoz pod noge", sinonimi))

        self.assertFalse(
            sopomena("pob in dekle sta vzela pot pod noge",
                     "fant in deček sta vzela pot pod tace", sinonimi))
        self.assertFalse(
            sopomena("pob in dekle sta vzela pot pod noge",
                     "fant in deček sta vzela pot pod tace", sinonimi))

        self.assertFalse(
            sopomena("pob in dekle sta vzela pot pod noge",
                     "fant in punca sta vzela pot pod tace",
                     [{"fant", "deček", "pob"}]))


class Naloga_5_Picerija(unittest.TestCase):
    def setUp(self):
        self.pizze = "margerita > klasika > zelenjavna > siri"

    def test_1_init_len(self):
        p = Picerija()
        self.assertEqual(len(p), 0)

    def test_2_speci_str(self):
        p = Picerija()
        q = Picerija()
        p.speci()
        self.assertEqual(len(p), 4)
        self.assertEqual(str(p), self.pizze)
        p.speci()
        self.assertEqual(len(p), 8)
        self.assertEqual(str(p), self.pizze + " > " + self.pizze)
        self.assertEqual(len(q), 0)

        r = Picerija()
        self.assertEqual(len(p), 8)
        self.assertEqual(len(q), 0)
        self.assertEqual(len(r), 0)

        q.speci()
        self.assertEqual(len(p), 8)
        self.assertEqual(len(q), 4)
        self.assertEqual(len(r), 0)

    def test_3_prodaj_zasluzek(self):
        p = Picerija()
        q = Picerija()
        p.speci()
        q.speci()
        q.speci()
        self.assertEqual(p.prodaj(), "siri")
        self.assertEqual(p.prodaj(), "zelenjavna")
        self.assertEqual(q.prodaj(), "siri")
        self.assertEqual(p.prodaj(), "klasika")
        self.assertEqual(p.prodaj(), "margerita")
        self.assertIsNone(p.prodaj())
        self.assertEqual(q.prodaj(), "zelenjavna")
        self.assertIsNone(p.prodaj())

        self.assertEqual(p.zasluzek(), 7)
        self.assertEqual(q.zasluzek(), 4)

        q.speci()
        self.assertEqual(q.prodaj(), "siri")
        self.assertEqual(q.prodaj(), "zelenjavna")
        self.assertEqual(q.prodaj(), "klasika")
        self.assertEqual(q.prodaj(), "margerita")
        self.assertEqual(q.prodaj(), "klasika")
        self.assertEqual(q.prodaj(), "margerita")
        self.assertIsNone(p.prodaj())

    def test_4_get_item(self):
        p = Picerija()
        p.speci()
        p.speci()
        self.assertEqual(p[1], "siri")
        self.assertEqual(p[2], "zelenjavna")
        self.assertEqual(p[3], "klasika")
        self.assertEqual(p[4], "margerita")
        p.prodaj()
        self.assertEqual(p[1], "zelenjavna")
        self.assertEqual(p[2], "klasika")
        self.assertEqual(p[3], "margerita")
        self.assertEqual(p[4], "siri")
        self.assertEqual(p[5], "zelenjavna")
        self.assertEqual(p[6], "klasika")
        self.assertEqual(p[7], "margerita")


if __name__ == "__main__":
    unittest.main()
