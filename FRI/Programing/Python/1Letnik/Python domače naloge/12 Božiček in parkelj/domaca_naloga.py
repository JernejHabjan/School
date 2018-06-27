# https://ucilnica.fri.uni-lj.si/mod/assign/view.php?id=10581
class Mesto():
    def __init__(self):
        self.mnozica = set()

    def obdaruj(self, x, y):
        self.mnozica.add((x, y))

    def je_obdarovana(self, x, y):
        return (x, y) in self.mnozica  ####

    def vse_obdarovane(self):
        return self.mnozica


class Bozicek:
    def __init__(self, mesto):
        self.mesto = mesto
        self.x = 0
        self.y = 0

    def premik(self, c):
        if c == "^":
            self.y += 1
        if c == "v":
            self.y -= 1
        if c == "<":
            self.x -= 1
        if c == ">":
            self.x += 1

            # self.x += (c==">") - (c=="<")  #to je logicni izraz.. pristeje ce true...
            # self.y += (c=="v") - (c=="^")

    def premiki(self, pot):
        for sign in pot:
            self.premik(sign)

    def obdaruj(self):
        self.mesto.mnozica.add((self.x, self.y))  ######################################
        # self.mesto.obdaruj(self.x,self.y) ---- tako se klice metoda iz unga classa


class HitriBozicek(Bozicek):
    def __init__(self, mesto, speed):
        self.speed = speed
        super().__init__(mesto)  ############### klici izpeljanke konstruktorja!!!!!!!!!!!!!!!

    def premik(self, c):
        if c == "^":
            self.y += 1 * self.speed
        if c == "v":
            self.y -= 1 * self.speed
        if c == "<":
            self.x -= 1 * self.speed
        if c == ">":
            self.x += 1 * self.speed

        ##################################################################################################################
        # zip z zacetnimi koordinatami in seznamom his


def parkelj(x, y, hise): return "".join(
    "<" * max(x0 - x1, 0) + ">" * max(x1 - x0, 0) + "v" * max(y0 - y1, 0) + "^" * max(y1 - y0, 0) for (x0, y0), (x1, y1)
    in zip([(x, y)] + list(hise), hise))


def dolzina_poti(x, y, hise):
    return sum(abs(x0 - x1) + abs(y0 - y1)
               for (x0, y0), (x1, y1) in zip([(x, y)] + list(hise), hise))


from functools import partial
from itertools import permutations


def hitri_parkelj(x, y, mesto):
    return parkelj(x, y, min(permutations(mesto.vse_obdarovane()),
                             key=partial(dolzina_poti, x, y)))


##################################################################################################################
import unittest


class TestMesto(unittest.TestCase):
    def test_mesto(self):
        a = Mesto()
        b = Mesto()

        self.assertSetEqual(a.vse_obdarovane(), set())
        self.assertSetEqual(b.vse_obdarovane(), set())
        self.assertFalse(a.je_obdarovana(1, 3))
        self.assertFalse(b.je_obdarovana(1, 3))

        a.obdaruj(1, 3)
        self.assertSetEqual(a.vse_obdarovane(), {(1, 3)})
        self.assertSetEqual(b.vse_obdarovane(), set())
        self.assertTrue(a.je_obdarovana(1, 3))
        self.assertFalse(b.je_obdarovana(1, 3))

        a.obdaruj(1, 3)
        self.assertSetEqual(a.vse_obdarovane(), {(1, 3)})
        self.assertSetEqual(b.vse_obdarovane(), set())
        self.assertTrue(a.je_obdarovana(1, 3))
        self.assertFalse(a.je_obdarovana(-2, -3))
        self.assertFalse(b.je_obdarovana(1, 3))

        a.obdaruj(-2, -3)
        self.assertSetEqual(a.vse_obdarovane(), {(1, 3), (-2, -3)})
        self.assertSetEqual(b.vse_obdarovane(), set())
        self.assertTrue(a.je_obdarovana(1, 3))
        self.assertTrue(a.je_obdarovana(-2, -3))
        self.assertFalse(b.je_obdarovana(1, 3))

        b.obdaruj(0, 0)
        self.assertSetEqual(a.vse_obdarovane(), {(1, 3), (-2, -3)})
        self.assertSetEqual(b.vse_obdarovane(), {(0, 0)})
        self.assertTrue(a.je_obdarovana(1, 3))
        self.assertTrue(a.je_obdarovana(-2, -3))
        self.assertFalse(b.je_obdarovana(1, 3))
        self.assertTrue(b.je_obdarovana(0, 0))
        self.assertFalse(a.je_obdarovana(0, 0))


class TestBozicek(unittest.TestCase):
    def test_obdaruj(self):
        m = Mesto()
        n = Mesto()
        b = Bozicek(m)
        b.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0)})
        self.assertSetEqual(n.vse_obdarovane(), set())

    def test_premik(self):
        m = Mesto()
        n = Mesto()
        b = Bozicek(m)
        c = Bozicek(m)
        b.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0)})
        self.assertSetEqual(n.vse_obdarovane(), set())
        b.premik("^")
        b.premik("<")
        b.premik("<")
        b.obdaruj()
        b.premik("v")
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (-2, 1)})
        self.assertSetEqual(n.vse_obdarovane(), set())
        c.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (-2, 1)})
        b.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (-2, 1), (-2, 0)})
        b.premik(">")
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (-2, 1), (-2, 0)})
        b.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (-2, 1), (-2, 0), (-1, 0)})
        c.premik("v")
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (-2, 1), (-2, 0), (-1, 0)})
        c.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (-2, 1), (-2, 0), (-1, 0), (0, -1)})
        self.assertSetEqual(n.vse_obdarovane(), set())

    def test_premiki(self):
        m = Mesto()
        b = Bozicek(m)
        b.premiki("v<<^^^>>>>>")
        b.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(3, 2)})

        cc = ""

        def p(c):
            nonlocal cc
            cc += c

        b.premik = p
        b.premiki("v<<^^^>>>>>")
        self.assertEqual(
            cc, "v<<^^^>>>>>",
            "Napiši metodo `premiki` tako, da bo uporabljala metodo `premik`")

    def test_hitri_bozicek(self):
        m = Mesto()
        b = HitriBozicek(m, 2)
        c = HitriBozicek(m, 3)
        b.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0)})
        b.premik("^")
        b.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (0, 2)})
        b.premik("<")
        b.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (0, 2), (-2, 2)})
        c.premik("<")
        c.obdaruj()
        self.assertSetEqual(m.vse_obdarovane(), {(0, 0), (0, 2), (-2, 2), (-3, 0)})

    def test_metode(self):
        self.assertIs(HitriBozicek.premiki, Bozicek.premiki,
                      "HitriBozicek naj podeduje metodo premiki")
        self.assertIs(HitriBozicek.obdaruj, Bozicek.obdaruj,
                      "HitriBozicek naj podeduje metodo obdaruj")


class TestParkelj(unittest.TestCase):
    def test_parkelj(self):
        self.assertEqual(parkelj(0, 0, [(4, 0)]), ">>>>")
        self.assertEqual(parkelj(0, 0, [(-2, 0)]), "<<")
        self.assertEqual(parkelj(5, 0, [(5, 3)]), "^^^")
        self.assertEqual(parkelj(12, 10, [(12, 8)]), "vv")

        self.assertEqual(sorted(parkelj(0, 0, [(4, 2)])), sorted(">>>>^^"))

        pot = parkelj(50, 6, [(46, 2), (52, 2), (41, 0)])
        self.assertEqual(sorted(pot[:8]), sorted("<<<<vvvv"))
        self.assertEqual(pot[8:14], ">>>>>>")
        self.assertEqual(sorted(pot[-13:]), sorted("<" * 11 + "vv"))

        self.assertEqual(parkelj(0, 0, [(0, 0)]), "")
        self.assertEqual(parkelj(0, 0, [(0, 0), (2, 0)]), ">>")

    def test_hitri_parkelj(self):
        m = Mesto()
        for i in range(3):
            m.obdaruj(i, 0)
            m.obdaruj(i, 1)
        self.assertTrue(hitri_parkelj(0, 0, m) in (">>^<<", "^>>v<"))

        n = Mesto()
        n.obdaruj(2, 0)
        n.obdaruj(0, 1)
        n.obdaruj(0, 10)
        pot = hitri_parkelj(0, 0, n)
        self.assertTrue(pot[:2], ">>")


if __name__ == "__main__":
    unittest.main()
