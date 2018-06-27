__author__ = 'Roglan'


def najdaljsa(s):
    najd = 0
    for element in s.split():  # .split razdeli na besede brez presledkov
        if najd == None or len(element) > najd:
            najd = len(element)
            naj_kraj = element
    return naj_kraj


def podobnost(s1, s2):
    counter = 0
    a = len(s1)  # primerjamo 2 seznama en z drugmu na istih mestih. primerjamo z istimi indeksi v različnih seznamih
    b = len(s2)
    if a > b:
        minim = b
    else:  # tu primerjam, ali je druga beseda daljša od prve. če bi bila bi vrnil error
        minim = a
    for i in range(minim):
        if s1[i] == s2[i]:
            counter += 1
    return counter


def sumljive(s):
    seznam = []
    s = s.split()  # split za besede
    for a in s:
        if a.count("a") and a.count("u"):  # .count prešteje koliko "črk" je v enem elementu
            seznam.append(a)
    return seznam


def vsi(xs):
    for element in xs:
        if not element:
            return False
    return True


def vsaj_eden(xs):
    for element in xs:
        if element:
            return True


def domine(xs):  # Pazi na indekse.. zanka for x in range(len(xs) se uporablja za indeksirat!!!!!
    for x in range(len(xs) - 1):
        if xs[x][1] != xs[x + 1][0]:
            return False
    return True


def vsota_seznamov(xss):
    skupna_vsota = []
    for ele in xss:
        delna_vsota = 0
        for neki in ele:
            delna_vsota += neki
        skupna_vsota.append(delna_vsota)
    return skupna_vsota


def najvecji_podseznam(xss):  # key vrne seznam!!!!
    return max(xss, key=sum)  # KEY!!!!!! max(1,2,3,4,5,6, key=functiongoeshere)


"""
xs = "ena dvaa tri stiri abrakadabra zz a"

def koliko_ajev(s):
    i=0
    for x in s:
        if x == "a":
            i+=1
    return i
print(max(xs.split(), key=koliko_ajev))
"""


def cezar(s):
    beseda = ""
    for crka in s:
        if crka == ' ':
            beseda += " "  # space nadomesti s "space"
        elif crka > "w":  # w je tretja črka od zadaj
            a = ord(crka) - ord('x')
            beseda += chr(a + 97)
        else:  # str.maketrans(x,y)
            beseda += chr(ord(crka) + 3)  # str.translate(mkt)

    return beseda


# abcdefghijklmnopqrstuvw xyz
# defghijklmnopqrstuvwxyz abc

print(cezar('the quick brown fox jumps over the lazy dog'))


def mrange(start, faktor, dolzina):  # TODO
    seznam = []
    for x in range(dolzina):
        seznam.append(start)
        start = start * faktor  # appendamo na prejšnem mestu, ker se mora appendat prva vrednost
    return seznam


def slikaj(f, xs):  # TODO
    seznam = []
    for neki in xs:
        seznam.append(f(neki))
    return seznam


def slikaj(f, xs):
    return list(map(f, xs))


### ^^^ Naloge rešujte nad tem komentarjem. ^^^ ###

import unittest


def fail_msg(args):
    return 'Failed on input: {}'.format(repr(args))


class TestVaje4(unittest.TestCase):
    def test_najdaljsa(self):
        in_out = [
            ('beseda', 'beseda'),
            ('an ban', 'ban'),
            ('an ban pet podgan', 'podgan'),
            ('an ban pet podgan stiri misi', 'podgan'),
            ('ta clanek je lepo napisan', 'napisan'),
            ('123456 12345 1234 123 12 1', '123456'),
            ('12345 123456 12345 1234 123 12 1', '123456'),
            ('1234 12345 123456 12345 1234 123 12 1', '123456'),
        ]

        for i, o in in_out:
            self.assertEqual(najdaljsa(i), o, fail_msg(i))

    def test_podobnost(self):
        in_out = [
            (('sobota', 'robot'), 4),
            (('', 'robot'), 0),
            (('sobota', ''), 0),
            (('', ''), 0),
            (('a', 'b'), 0),
            (('a', 'a'), 1),
            (('aaa', 'a'), 1),
            (('amper', 'amonijak'), 2),
            (('1000 let', 'tisoc let'), 0),
            (('hamming distance', 'haming  distance'), 12)
        ]

        for i, o in in_out:
            self.assertEqual(podobnost(*i), o, fail_msg(i))
            self.assertEqual(podobnost(*i[::-1]), o, fail_msg(i))

    def test_sumljive(self):
        in_out = [
            ('', []),
            ('aa uu', []),
            ('aa uu au', ['au']),
            ('muha', ['muha']),
            ('Muha pa je rekla: "Tale juha se je pa res prilegla, najlepša huala," in odletela.',
             ['Muha', 'juha', 'huala,"']),
            ('ameba nima aja in uja, ampak samo a', ['uja,']),
        ]

        for i, o in in_out:
            self.assertListEqual(sumljive(i), o, fail_msg(i))

    def test_vsi(self):
        in_out = [
            ([True, True, False], False),
            ([True, True], True),
            ([1, 2, 3, 0], False),
            (['foo', 42, True], True),
            (['foo', '', 42, True], False),
            (['foo', 0.0, 42, True], False),
            (['foo', None, 42, True], False),
            (['foo', (), 42, True], False),
            (['foo', [], 42, True], False),
            ([], True),
        ]

        for i, o in in_out:
            f = self.assertTrue if o else self.assertFalse
            f(vsi(i), fail_msg(i))

    def test_vsaj_eden(self):
        in_out = [
            ([2, 3, 0], True),
            ([], False),
            ([True, False, False], True),
            ([False, False], False),
            (['foo', 42, True], True),
            ([False, 0, 0.0, '', None, (), []], False),
            ([False, 0, 0.42, '', None, (), []], True),
            ([False, 0, 0.0, '', None, (), [42]], True),
        ]

        for i, o in in_out:
            f = self.assertTrue if o else self.assertFalse
            f(vsaj_eden(i), fail_msg(i))

    def test_domine(self):
        in_out = [
            ([], True),
            ([(2, 4), (4, 4)], True),
            ([(2, 4), (4, 4), (4, 2)], True),
            ([(2, 4), (4, 4), (4, 2), (2, 9), (9, 1)], True),
            ([(2, 4), (4, 3), (4, 2), (2, 9), (9, 1)], False),
            ([(3, 6), (6, 6), (6, 1), (1, 0)], True),
            ([(3, 6), (6, 6), (2, 3)], False),
        ]

        for i, o in in_out:
            f = self.assertTrue if o else self.assertFalse
            f(domine(i), fail_msg(i))

    def test_vsota_seznamov(self):
        in_out = [
            ([], []),
            ([[]], [0]),
            ([[0]], [0]),
            ([[1, 2]], [3]),
            ([[1, 2], [], [0]], [3, 0, 0]),
            ([[2, 4, 1], [3, 1], [], [8, 2], [1, 1, 1, 1]], [7, 4, 0, 10, 4]),
            ([[5, 3, 6, 3], [1, 2, 3, 4], [5, -1, 0]], [17, 10, 4]),
        ]

        for i, o in in_out:
            self.assertEqual(vsota_seznamov(i), o, fail_msg(i))

    def test_najvecji_podseznam(self):
        in_out = [
            ([[0]], [0]),
            ([[1, 2]], [1, 2]),
            ([[1, 2], [], [0]], [1, 2]),
            ([[2, 4, 1], [3, 1], [], [8, 2], [1, 1, 1, 1]], [8, 2]),
            ([[5, 3, 6, 3], [1, 2, 3, 4], [5, -1, 0]], [5, 3, 6, 3]),
        ]

        for i, o in in_out:
            self.assertEqual(najvecji_podseznam(i), o, fail_msg(i))

    def test_cezar(self):
        in_out = [
            ('', ''),
            ('a', 'd'),
            ('aa', 'dd'),
            ('ab', 'de'),
            ('z', 'c'),
            ('xyz', 'abc'),
            (' ', ' '),
            ('a  a', 'd  d'),
            ('julij cezar je seveda uporabljal cezarjevo sifro',
             'mxolm fhcdu mh vhyhgd xsrudeomdo fhcdumhyr vliur'),
            ('the quick brown fox jumps over the lazy dog',
             'wkh txlfn eurzq ira mxpsv ryhu wkh odcb grj'),
        ]

        for i, o in in_out:
            self.assertEqual(cezar(i), o, fail_msg(i))

    def test_mrange(self):
        in_out = [
            ((32, 2, 0), []),
            ((32, 2, 1), [32]),
            ((32, 2, 2), [32, 64]),
            ((42, -1, 5), [42, -42, 42, -42, 42]),
            ((7, 4, 7), [7, 28, 112, 448, 1792, 7168, 28672]),
        ]

        for i, o in in_out:
            self.assertListEqual(mrange(*i), o, fail_msg(i))

    def test_slikaj(self):
        in_out = [
            ((abs, [-5, 8, -3, -1, 3]), [5, 8, 3, 1, 3]),
            ((len, 'Daydream delusion limousine eyelash'.split()), [8, 8, 9, 7]),
            ((int, '1 3 5 42'.split()), [1, 3, 5, 42]),
        ]

        for i, o in in_out:
            self.assertListEqual(slikaj(*i), o, fail_msg(i))


if __name__ == '__main__':
    unittest.main(verbosity=2)
