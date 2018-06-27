__author__ = 'Roglan'
from math import *


def razdalja(x1, y1, x2, y2):
    return sqrt((x1 - x2) ** 2 + (y1 - y2) ** 2)


def koordinate(kraj, kraji):
    for pos_kraj, x, y in kraji:
        if kraj == pos_kraj:
            return x, y


def oddaljenost(kraj1, kraj2, kraji):
    x1, y1 = koordinate(kraj1, kraji)
    x2, y2 = koordinate(kraj2, kraji)
    return razdalja(x1, y1, x2, y2)


def najblizji(kraj, kraji):
    najblizji_kraj = 0
    najmanjsa_razdalja = None
    for ime, x, y in kraji:
        if kraj != ime:
            razd = oddaljenost(kraj, ime, kraji)
            if najmanjsa_razdalja == None or razd < najmanjsa_razdalja:
                najmanjsa_razdalja = razd
                najblizji_kraj = ime
    return najblizji_kraj


def brez_kraja(kraj, kraji):
    tabela_kraji = []
    for ime, x, y in kraji:
        if kraj != ime:
            tabela_kraji.append((ime, x, y))
    return tabela_kraji


def veriga(kraj, kraji):
    seznam = kraji
    places = []
    places.append(kraj)
    trenutni_kraj = kraj
    while seznam != len(seznam) > 1:
        najbljizi_kraj = najblizji(trenutni_kraj, seznam)
        places.append(najbljizi_kraj)
        seznam = brez_kraja(trenutni_kraj, seznam)
        trenutni_kraj = najbljizi_kraj
    return places


import unittest


class TestKraji(unittest.TestCase):
    class CountCalls:
        def __init__(self, f):
            self.f = f
            self.call_count = 0

        def __call__(self, *args, **kwargs):
            self.call_count += 1
            return self.f(*args, **kwargs)

    @classmethod
    def setUpClass(cls):
        global koordinate, razdalja
        try:
            koordinate = cls.CountCalls(koordinate)
        except:
            pass
        try:
            razdalja = cls.CountCalls(razdalja)
        except:
            pass

    def test_1_razdalja(self):
        self.assertEqual(razdalja(0, 0, 1, 0), 1)
        self.assertEqual(razdalja(0, 0, 0, 1), 1)
        self.assertEqual(razdalja(0, 0, -1, 0), 1)
        self.assertEqual(razdalja(0, 0, 0, -1), 1)
        self.assertEqual(razdalja(1, 0, 0, 0), 1)
        self.assertEqual(razdalja(0, 1, 0, 0), 1)
        self.assertEqual(razdalja(-1, 0, 0, 0), 1)
        self.assertEqual(razdalja(0, -1, 0, 0), 1)

        self.assertEqual(razdalja(1, 2, 4, 6), 5)
        self.assertEqual(razdalja(1, 2, -2, 6), 5)
        self.assertEqual(razdalja(1, 2, 4, -2), 5)
        self.assertEqual(razdalja(1, 2, -2, -2), 5)

        from math import sqrt
        self.assertAlmostEqual(razdalja(1, 2, 0, 1), sqrt(2))

    def test_2_koordinate(self):
        kraji = [
            ('BreĹľice', 68.66, 7.04),
            ('Lenart', 85.20, 78.75),
            ('RateÄŤe', -65.04, 70.04),
            ('Ljutomer', 111.26, 71.82)
        ]

        self.assertEqual(koordinate("BreĹľice", kraji), (68.66, 7.04))
        self.assertEqual(koordinate("Lenart", kraji), (85.20, 78.75))
        self.assertEqual(koordinate("RateÄŤe", kraji), (-65.04, 70.04))
        self.assertEqual(koordinate("Ljutomer", kraji), (111.26, 71.82))
        self.assertIsNone(koordinate("Ljubljana", kraji))

        kraji = [('BreĹľice', 68.66, 7.04)]
        self.assertEqual(koordinate("BreĹľice", kraji), (68.66, 7.04))
        self.assertIsNone(koordinate("Lenart", kraji))

        kraji = []
        self.assertIsNone(koordinate("BreĹľice", kraji))

    def test_2_range_len(self):
        class NoGetItem(list):
            def __getitem__(*x):
                raise IndexError("NauÄŤi se (pravilno) uporabljati zanko for!")

        kraji = NoGetItem([('BreĹľice', 68.66, 7.04), ('Lenart', 85.20, 78.75),
                           ('RateÄŤe', -65.04, 70.04)])
        self.assertEqual(koordinate("BreĹľice", kraji), (68.66, 7.04))
        self.assertEqual(koordinate("Lenart", kraji), (85.20, 78.75))
        self.assertEqual(koordinate("RateÄŤe", kraji), (-65.04, 70.04))
        self.assertIsNone(koordinate("Ljubljana", kraji))

    def test_3_razdalja_krajev(self):
        kraji = [
            ('BreĹľice', 10, 20),
            ('Lenart', 13, 24),
            ('RateÄŤe', 17, 20),
            ('Ljutomer', 8, 36)
        ]

        from math import sqrt
        self.assertEqual(oddaljenost("BreĹľice", "Lenart", kraji), 5)
        self.assertEqual(oddaljenost("Lenart", "BreĹľice", kraji), 5)
        self.assertEqual(oddaljenost("BreĹľice", "RateÄŤe", kraji), 7)
        self.assertAlmostEqual(oddaljenost("Lenart", "RateÄŤe", kraji), sqrt(32))
        self.assertEqual(oddaljenost("Lenart", "Ljutomer", kraji), 13)

        koordinate.call_count = razdalja.call_count = 0
        oddaljenost("BreĹľice", "Lenart", kraji)
        self.assertEqual(
            koordinate.call_count, 2,
            "Funkcija `razdalja_krajev` mora dvakrat poklicati `koordinate`")
        self.assertEqual(
            razdalja.call_count, 1,
            "Funkcija `razdalja_krajev` mora enkrat poklicati `razdalja`")

    def test_4_najblizji(self):
        kraji = [
            ('BreĹľice', 68.66, 7.04),
            ('Lenart', 85.20, 78.75),
            ('RateÄŤe', -65.04, 70.04),
            ('Ljutomer', 111.26, 71.82),
            ('RogaĹˇka Slatina', 71.00, 42.00),
            ('Ribnica', 7.10, -10.50),
            ('Dutovlje', -56.80, -6.93),
            ('Lokve', -57.94, 19.32),
            ('Vinica', 43.81, -38.43),
            ('Brtonigla', -71.00, -47.25),
            ('Kanal', -71.00, 26.25)]
        self.assertEqual(najblizji("BreĹľice", kraji), "RogaĹˇka Slatina")
        self.assertEqual(najblizji("Kanal", kraji), "Lokve")
        self.assertEqual(najblizji("Ljutomer", kraji), "Lenart")

        razdalja.call_count = 0
        najblizji("Ljutomer", kraji)
        self.assertGreaterEqual(
            razdalja.call_count, len(kraji) - 1,
            "Funkcija `najblizji` mora vsaj {}-krat poklicati funkcijo "
            "`razdalja`".format(len(kraji) - 1))

        kraji = [
            ('BreĹľice', 68.66, 7.04),
            ('Lenart', 85.20, 78.75)]
        self.assertEqual(najblizji("BreĹľice", kraji), "Lenart")

    kraji = [
        ('BreĹľice', 68.66, 7.04),
        ('Lenart', 85.20, 78.75),
        ('RateÄŤe', -65.04, 70.04),
        ('Ljutomer', 111.26, 71.82),
        ('RogaĹˇka Slatina', 71.00, 42.00),
        ('Ribnica', 7.10, -10.50),
        ('Dutovlje', -56.80, -6.93),
        ('Lokve', -57.94, 19.32),
        ('Vinica', 43.81, -38.43),
        ('Brtonigla', -71.00, -47.25),
        ('Kanal', -71.00, 26.25),
        ('ÄŚrnomelj', 39.05, -27.93),
        ('Trbovlje', 29.61, 35.07),
        ('Beltinci', 114.81, 80.54),
        ('DomĹľale', -2.34, 31.50),
        ('HodoĹˇ', 120.70, 105.00),
        ('Ĺ kofja Loka', -23.64, 35.07),
        ('Velike LaĹˇÄŤe', 0.00, 0.00),
        ('Velenje', 33.16, 54.29),
        ('Ĺ oĹˇtanj', 29.61, 57.75),
        ('LaĹˇko', 42.60, 33.29),
        ('Postojna', -29.54, -5.25),
        ('Ilirska Bistrica', -27.19, -27.93),
        ('Radenci', 100.61, 84.00),
        ('ÄŚrna', 15.41, 66.57),
        ('RadeÄŤe', 39.05, 24.57),
        ('Vitanje', 47.36, 57.75),
        ('Bled', -37.84, 56.07),
        ('Tolmin', -63.90, 36.75),
        ('Miren', -72.14, 7.04),
        ('Ptuj', 87.61, 61.32),
        ('Gornja Radgona', 97.06, 89.25),
        ('Plave', -73.34, 21.00),
        ('Novo mesto', 37.91, -3.47),
        ('Bovec', -76.89, 52.50),
        ('Nova Gorica', -69.79, 12.29),
        ('KrĹˇko', 60.35, 14.07),
        ('Cerknica', -18.89, -3.47),
        ('Slovenska Bistrica', 66.31, 57.75),
        ('Anhovo', -72.14, 22.78),
        ('OrmoĹľ', 107.71, 61.32),
        ('Ĺ kofije', -59.14, -27.93),
        ('ÄŚepovan', -60.35, 22.78),
        ('Murska Sobota', 108.91, 87.57),
        ('Ljubljana', -8.24, 22.78),
        ('Idrija', -43.74, 17.54),
        ('Radlje ob Dravi', 41.46, 82.32),
        ('Ĺ˝alec', 37.91, 43.79),
        ('Mojstrana', -49.70, 64.79),
        ('Log pod Mangartom', -73.34, 59.54),
        ('Podkoren', -62.69, 70.04),
        ('KoÄŤevje', 16.61, -21.00),
        ('SoÄŤa', -69.79, 52.50),
        ('AjdovĹˇÄŤina', -53.25, 5.25),
        ('Bohinjska Bistrica', -48.49, 47.25),
        ('TrĹľiÄŤ', -22.44, 56.07),
        ('Piran', -75.69, -31.50),
        ('Kranj', -20.09, 43.79),
        ('Kranjska Gora', -60.35, 68.25),
        ('Izola', -68.59, -31.50),
        ('Radovljica', -31.95, 54.29),
        ('Gornji Grad', 13.06, 49.03),
        ('Ĺ entjur', 54.46, 40.32),
        ('Koper', -63.90, -29.72),
        ('Celje', 45.01, 42.00),
        ('Mislinja', 42.60, 66.57),
        ('Metlika', 48.56, -19.21),
        ('Ĺ˝aga', -81.65, 49.03),
        ('Komen', -63.90, -1.68),
        ('Ĺ˝uĹľemberk', 21.30, 0.00),
        ('Pesnica', 74.55, 80.54),
        ('Vrhnika', -23.64, 14.07),
        ('Dravograd', 28.40, 78.75),
        ('Kamnik', -1.14, 40.32),
        ('Jesenice', -40.19, 64.79),
        ('Kobarid', -74.55, 43.79),
        ('PortoroĹľ', -73.34, -33.18),
        ('Muta', 37.91, 82.32),
        ('SeĹľana', -54.39, -13.96),
        ('Vipava', -47.29, 1.79),
        ('Maribor', 72.21, 75.28),
        ('Slovenj Gradec', 31.95, 71.82),
        ('Litija', 14.20, 22.78),
        ('Na Logu', -62.69, 57.75),
        ('Stara FuĹľina', -52.04, 47.25),
        ('Motovun', -56.80, -52.50),
        ('Pragersko', 73.41, 57.75),
        ('Most na SoÄŤi', -63.90, 33.29),
        ('Brestanica', 60.35, 15.75),
        ('Savudrija', -80.44, -34.96),
        ('SodraĹľica', 0.00, -6.93),
    ]

    def test_5_veriga(self):
        self.assertEqual(
            veriga("BreĹľice", self.kraji),
            ['BreĹľice', 'KrĹˇko', 'Brestanica', 'RadeÄŤe', 'LaĹˇko', 'Celje',
             'Ĺ˝alec', 'Velenje', 'Ĺ oĹˇtanj', 'Slovenj Gradec', 'Dravograd',
             'Muta', 'Radlje ob Dravi', 'Mislinja', 'Vitanje', 'Ĺ entjur',
             'RogaĹˇka Slatina', 'Pragersko', 'Slovenska Bistrica', 'Maribor',
             'Pesnica', 'Lenart', 'Gornja Radgona', 'Radenci', 'Murska Sobota',
             'Beltinci', 'Ljutomer', 'OrmoĹľ', 'Ptuj', 'HodoĹˇ', 'ÄŚrna',
             'Gornji Grad', 'Kamnik', 'DomĹľale', 'Ljubljana', 'Vrhnika',
             'Cerknica', 'Postojna', 'Vipava', 'AjdovĹˇÄŤina', 'Dutovlje',
             'SeĹľana', 'Ĺ kofije', 'Koper', 'Izola', 'PortoroĹľ', 'Piran',
             'Savudrija', 'Brtonigla', 'Motovun', 'Ilirska Bistrica',
             'SodraĹľica', 'Velike LaĹˇÄŤe', 'Ribnica', 'KoÄŤevje', 'Ĺ˝uĹľemberk',
             'Novo mesto', 'Metlika', 'ÄŚrnomelj', 'Vinica', 'Litija',
             'Trbovlje', 'Kranj', 'Ĺ kofja Loka', 'Radovljica', 'Bled',
             'Jesenice', 'Mojstrana', 'Kranjska Gora', 'Podkoren', 'RateÄŤe',
             'Na Logu', 'SoÄŤa', 'Bovec', 'Ĺ˝aga', 'Kobarid', 'Tolmin',
             'Most na SoÄŤi', 'Kanal', 'Anhovo', 'Plave', 'Nova Gorica',
             'Miren', 'Komen', 'Lokve', 'ÄŚepovan', 'Idrija',
             'Bohinjska Bistrica', 'Stara FuĹľina', 'Log pod Mangartom',
             'TrĹľiÄŤ'])

    def test_6_vojska(self):
        resitev = {
            ('BreĹľice', 'KrĹˇko'), ('KrĹˇko', 'Brestanica'),
            ('Brestanica', 'RadeÄŤe'), ('RadeÄŤe', 'LaĹˇko'), ('LaĹˇko', 'Celje'),
            ('Celje', 'Ĺ˝alec'), ('Celje', 'Ĺ entjur'), ('Ĺ˝alec', 'Velenje'),
            ('Velenje', 'Ĺ oĹˇtanj'), ('Ĺ˝alec', 'Trbovlje'),
            ('Ĺ oĹˇtanj', 'Slovenj Gradec'), ('Slovenj Gradec', 'Dravograd'),
            ('Dravograd', 'Muta'), ('Muta', 'Radlje ob Dravi'),
            ('Slovenj Gradec', 'Mislinja'), ('Mislinja', 'Vitanje'),
            ('Ĺ entjur', 'RogaĹˇka Slatina'), ('RogaĹˇka Slatina', 'Pragersko'),
            ('Pragersko', 'Slovenska Bistrica'), ('Pragersko', 'Ptuj'),
            ('Ĺ oĹˇtanj', 'ÄŚrna'), ('Pragersko', 'Maribor'), ('Maribor', 'Pesnica'),
            ('Pesnica', 'Lenart'), ('Lenart', 'Gornja Radgona'),
            ('Gornja Radgona', 'Radenci'), ('Radenci', 'Murska Sobota'),
            ('Murska Sobota', 'Beltinci'), ('Beltinci', 'Ljutomer'),
            ('Ljutomer', 'OrmoĹľ'), ('ÄŚrna', 'Gornji Grad'),
            ('Gornji Grad', 'Kamnik'), ('Kamnik', 'DomĹľale'),
            ('DomĹľale', 'Ljubljana'), ('Ljubljana', 'Vrhnika'),
            ('Vrhnika', 'Cerknica'), ('Cerknica', 'Postojna'),
            ('DomĹľale', 'Litija'), ('Postojna', 'Vipava'),
            ('Vipava', 'AjdovĹˇÄŤina'), ('AjdovĹˇÄŤina', 'Dutovlje'),
            ('Dutovlje', 'SeĹľana'), ('Dutovlje', 'Komen'), ('Komen', 'Miren'),
            ('Miren', 'Nova Gorica'), ('Nova Gorica', 'Plave'),
            ('Plave', 'Anhovo'), ('Anhovo', 'Kanal'), ('Kanal', 'Most na SoÄŤi'),
            ('Most na SoÄŤi', 'Tolmin'), ('Most na SoÄŤi', 'ÄŚepovan'),
            ('ÄŚepovan', 'Lokve'), ('Tolmin', 'Kobarid'), ('Kobarid', 'Ĺ˝aga'),
            ('Ĺ˝aga', 'Bovec'), ('Bovec', 'SoÄŤa'), ('Bovec', 'Log pod Mangartom'),
            ('SoÄŤa', 'Na Logu'), ('Na Logu', 'Kranjska Gora'),
            ('Kranjska Gora', 'Podkoren'), ('Podkoren', 'RateÄŤe'),
            ('Kranjska Gora', 'Mojstrana'), ('Mojstrana', 'Jesenice'),
            ('Jesenice', 'Bled'), ('Bled', 'Radovljica'), ('Radovljica', 'TrĹľiÄŤ'),
            ('TrĹľiÄŤ', 'Kranj'), ('Kranj', 'Ĺ kofja Loka'),
            ('Bled', 'Bohinjska Bistrica'), ('Bohinjska Bistrica', 'Stara FuĹľina'),
            ('Lokve', 'Idrija'), ('SeĹľana', 'Ĺ kofije'), ('Ĺ kofije', 'Koper'),
            ('Koper', 'Izola'), ('Izola', 'PortoroĹľ'), ('PortoroĹľ', 'Piran'),
            ('Piran', 'Savudrija'), ('PortoroĹľ', 'Brtonigla'),
            ('Brtonigla', 'Motovun'), ('Cerknica', 'SodraĹľica'),
            ('SodraĹľica', 'Velike LaĹˇÄŤe'), ('SodraĹľica', 'Ribnica'),
            ('Ribnica', 'KoÄŤevje'), ('Ribnica', 'Ĺ˝uĹľemberk'),
            ('Ĺ˝uĹľemberk', 'Novo mesto'), ('Novo mesto', 'Metlika'),
            ('Metlika', 'ÄŚrnomelj'), ('ÄŚrnomelj', 'Vinica'),
            ('Murska Sobota', 'HodoĹˇ'), ('Postojna', 'Ilirska Bistrica')}

        self.assertSetEqual(
            set(vojska(self.kraji)), resitev,
            "ÄŚe ta test ne uspe, to Ĺˇe ne pomeni nujno, da je reĹˇitev napaÄŤna")


if __name__ == "__main__":
    unittest.main()
