def tekac(startna, ime, leto, h, m, s):
    return "{:4}. {:20} {:02}:{:02}:{:02}".format(startna, ime, h, m, s)


def tekac_star(startna, ime, leto, h, m, s):
    ime_leto = "{} ({})".format(ime, leto)
    return "{:4}. {a:26} {ho:02}:{mi:02}:{se:02}".format(startna, a=ime_leto, ho=h, mi=m, se=s)


import unittest


class TestTekaci(unittest.TestCase):
    def test_tekac(self):
        self.assertEqual(tekac(1, "ROMAN SONJA", 1979, 1, 15, 2),
                         "   1. ROMAN SONJA          01:15:02")
        self.assertEqual(tekac(1234, "ROMAN SONJA", 1979, 0, 1, 23),
                         "1234. ROMAN SONJA          00:01:23")
        self.assertEqual(tekac(1, "JAN HUS", 1979, 1, 15, 2),
                         '   1. JAN HUS              01:15:02')

    def test_tekac_star(self):
        self.assertEqual(tekac_star(1, "ROMAN SONJA", 1979, 1, 15, 2),
                         "   1. ROMAN SONJA (1979)         01:15:02")
        self.assertEqual(tekac_star(1234, "ROMAN SONJA", 1979, 0, 1, 23),
                         "1234. ROMAN SONJA (1979)         00:01:23")
        self.assertEqual(tekac_star(1234, "JAN HUS", 1979, 0, 1, 23),
                         '1234. JAN HUS (1979)             00:01:23')


if __name__ == "__main__":
    unittest.main()
