# https://ucilnica.fri.uni-lj.si/mod/assign/view.php?id=8911
def v_sekunde(s):
    a = s.split(":")
    return (3600 * int(a[0]) + 60 * int(a[1]) + int(a[2]))


def iz_sekund(s):
    seznam = []
    m, sec = divmod(s, 60)
    h, m = divmod(m, 60)
    seznam.append(str(h))
    seznam.append(str(m))
    seznam.append(str(sec))
    return ":".join(seznam)


def podatki(s):
    a = s.split("\t")
    return (a[2], int(a[3]), v_sekunde(a[5]), v_sekunde(a[6]))


def pospesek(vrstice):
    neki = vrstice.split("\t")
    a = v_sekunde(neki[5])
    b = v_sekunde(neki[6])
    return (b - a) / a


def naj_pospesek(vrstice):
    maks_posp = None
    maks_ime = ""
    for element in vrstice:
        a = element.split()
        posp = pospesek(element)
        if maks_posp == None or maks_posp > posp:
            maks_posp = posp
            maks_ime = a[2], a[3]
    return " ".join(maks_ime)


def vsi_pospeseni(vrstica, faktor):
    seznam = []
    for a in vrstica:
        b = a.split("\t")
        prvi_cas = v_sekunde(b[5])
        drugi_cas = (v_sekunde(b[6]) - v_sekunde(b[5]))
        if drugi_cas <= prvi_cas * faktor:
            seznam.append(b[2])
    return seznam


def leta(vrstice):
    seznam = []
    for a in vrstice:
        b = a.split("\t")
        if seznam.count(int(b[3])) == 0:
            seznam.append(int(b[3]))
    return sorted(seznam)


def tekaci_leta(vrstice, leto):
    seznam = []
    for element in vrstice:
        a = element.split("\t")
        if leto == int(a[3]):
            seznam.append(a[2])
    if len(seznam) <= 1:
        return "".join(seznam)
    else:
        b = ", ".join(seznam[:len(seznam) - 1]), " in ", seznam[len(seznam) - 1]
        return "".join(b)


def najboljsi_po_letih(vrstice):
    po_letih = []
    for vrstica in vrstice:
        ime, leto, cas1, cas2 = podatki(vrstica)
        for i, (leto1, naj_ime, naj_cas) in enumerate(po_letih):  ###########3 enumeerate
            if leto == leto1:
                if cas2 < naj_cas:
                    po_letih[i] = (leto, ime, cas2)
                break
        else:
            po_letih.append((leto, ime, cas2))  # PAZI TAJ TEZKA!!!!!!!!!!!

    po_letih2 = []
    for leto, ime, cas in po_letih:
        po_letih2.append((leto, ime))
    return sorted(po_letih2)


    # return [min(value, key=lambda x: x[-1])[1::-1] for key, value in groupby(sorted([podatki(s) for s in vrstice], key=lambda x: x[1]), key=lambda x: x[1])]


import unittest


class TestMaraton(unittest.TestCase):
    def test_v_sekunde(self):
        self.assertEqual(v_sekunde("00:00:00"), 0)
        self.assertEqual(v_sekunde("00:00:01"), 1)
        self.assertEqual(v_sekunde("00:00:02"), 2)
        self.assertEqual(v_sekunde("00:01:00"), 60)
        self.assertEqual(v_sekunde("00:02:00"), 120)
        self.assertEqual(v_sekunde("02:00:00"), 7200)
        self.assertEqual(v_sekunde("02:02:02"), 7322)
        self.assertEqual(v_sekunde("1:03:46"), 3826)
        self.assertEqual(v_sekunde("01:03:46"), 3826)
        self.assertEqual(v_sekunde("01:3:46"), 3826)
        self.assertEqual(v_sekunde("01:30:6"), 5406)
        self.assertEqual(v_sekunde("01:2:15"), 3735)

    def test_iz_sekund(self):
        self.assertEqual(iz_sekund(0), "0:0:0")
        self.assertEqual(iz_sekund(1), "0:0:1")
        self.assertEqual(iz_sekund(60), "0:1:0")
        self.assertEqual(iz_sekund(61), "0:1:1")
        self.assertEqual(iz_sekund(62), "0:1:2")
        self.assertEqual(iz_sekund(122), "0:2:2")
        self.assertEqual(iz_sekund(3600), "1:0:0")
        self.assertEqual(iz_sekund(3666), "1:1:6")

    def test_podatki(self):
        self.assertEqual(podatki("1\t14895\tROMAN SONJA\t1979\tSLO\t0:16:39\t0:33:32"),
                         ("ROMAN SONJA", 1979, 999, 2012))
        self.assertEqual(podatki("1\t14895\tROMAN B. SONJA\t1979\tSLO\t0:16:39\t0:33:32"),
                         ("ROMAN B. SONJA", 1979, 999, 2012))

    def test_razlika(self):
        self.assertEqual(pospesek("1\t14895\tROMAN SONJA\t1979\tSLO\t0:16:10\t0:32:20"), 1)
        self.assertEqual(pospesek("1\t14895\tROMAN SONJA\t1979\tSLO\t0:16:10\t0:48:30"), 2)
        self.assertEqual(pospesek("1\t14895\tROMAN SONJA\t1979\tSLO\t0:16:10\t0:24:15"), 0.5)

    @property
    def realdata(self):
        return open("10z.txt", encoding="utf-8")

    def test_naj_razlika(self):
        self.assertEqual(naj_pospesek(self.realdata), "GRUDEN SONJA")

    def test_vsi_pospeseni(self):
        self.assertEqual(
            vsi_pospeseni(self.realdata, 0.85),
            ['GRUDEN SONJA', 'DEBELJAK MOJCA', 'MESTNIK LARA', 'BALANT ZDENKA', 'HOSTA ANJA'])
        self.assertEqual(vsi_pospeseni(self.realdata, 0.8), ['GRUDEN SONJA'])

    def test_leta(self):
        self.assertEqual(
            leta(self.realdata),
            [1937, 1938, 1942, 1943] + list(range(1946, 2004)) + [2005, 2006])

    def test_tekaci_leta(self):
        self.assertEqual(tekaci_leta(self.realdata, 1948), 'JAGER JOŽICA, KOČEVAR MILA in RUPAR ALENKA')
        self.assertEqual(tekaci_leta(self.realdata, 1947), "BOLE MARIJA")
        self.assertEqual(tekaci_leta(self.realdata, 1945), "")

    def test_najboljsi_po_letih(self):
        self.assertEqual(
            najboljsi_po_letih(self.realdata),
            [(1937, 'MCNALLY ETHEL'), (1938, 'PUHAR MIRA'),
             (1942, 'LOUHI REJEC LEENA'), (1943, 'GOLOB IVANA'),
             (1946, 'ŠUŠTERŠIČ DUNJA'), (1947, 'BOLE MARIJA'),
             (1948, 'JAGER JOŽICA'), (1949, 'ŽNIDARŠIČ JOŽICA'),
             (1950, 'KRMAVNAR dAMJANA'), (1951, 'GOMIVNIK VIDA'),
             (1952, 'STRAŠEK DRAGICA'), (1953, 'RINK MARJETA'),
             (1954, 'DOLENEC IRENA'), (1955, 'JERAJ MARIJA'),
             (1956, 'VRTAČNIK BOKAL EDA'), (1957, 'HERMAN EMILIJA'),
             (1958, 'HIRŠMAN ALENKA'), (1959, 'PŠAKER MARIJA'),
             (1960, 'MOČIVNIK ŠKEDELJ BARBKA'), (1961, 'PERAK LUCIJA'),
             (1962, 'NAHTIGAL BRIGITA'), (1963, 'RUPNIK SONJA'),
             (1964, 'OBRČ TEA'), (1965, 'BERKOPEC MARTA'),
             (1966, 'SELJAK BERNARDA'), (1967, 'ČRNILOGAR VESNA'),
             (1968, 'RUPNIK ANDREJA'), (1969, 'ŠTEVANEC MARIJA'),
             (1970, 'AHČIN MATEJKA'), (1971, 'ŽGAJNER MAJDA'),
             (1972, 'HELENA VALAS'), (1973, 'Pirc Alenka'),
             (1974, 'RADIVO MANICA'), (1975, 'CONRADI MARJETKA'),
             (1976, 'VESEL HELENA'), (1977, 'STRUNA NINA'),
             (1978, 'PRAPROTNIK ZALA'), (1979, 'ROMAN SONJA'),
             (1980, 'BRODNIK NIKA'), (1981, 'LEMUT KLEMENTINA'),
             (1982, 'PAVLIČ MARY'), (1983, 'MUŠIČ RADEJ NINA'),
             (1984, 'FAJDIGA ANJA'), (1985, 'PLANOVŠEK ANA'),
             (1986, 'ŽUNIČ KATJA'), (1987, 'MIKLAVŽIN MAJA'),
             (1988, 'BERČIČ KATJA'), (1989, 'KOPAČ POLONA'),
             (1990, 'MUŠIČ MATEJA'), (1991, 'VIDMAR SARA'),
             (1992, 'MEJAČ ANJA'), (1993, 'KOZJEK TINA'),
             (1994, 'MIŠMAŠ MARUŠA'), (1995, 'ŽGAJNER JERNEJA'),
             (1996, 'VAN DE VOORDE MAGDALENA'), (1997, 'GUZELJ BLATNIK LAURA'),
             (1998, 'LEŠNJAK ANA'), (1999, 'ČESNIK TINA'),
             (2000, 'STAUBER LAURA'), (2001, 'MIHEVC LARA'),
             (2002, 'MALI KLARA'), (2003, 'FERJANČIČ MAŠA'),
             (2005, 'SLAPNIK AŠA'), (2006, 'MEGLIČ AJŠA')])


if __name__ == "__main__":
    unittest.main()
