__author__ = 'Roglan'

# oboje
"""for key, value in cenik.items():
        print(key,value) -dobimo oboje                             ITEMS

ali je kljuc v slovarju?
print("sir" in cenik)


del cenik["mleko]


če nekaj ni noter....
print(cenik.get("sir","v ceniku ni tega izdelka")... get ima 2 parametra... drugi je kaj izpišemo, če tega ni... če izpustimo dobimo None


import collections
seznam_nakupov =["kruh","mleko","kruh"]

pogostosti={}
print(pogostosti["kruh"]).... error... v slovarju pogostosti tega elementa ni

pogostosti=collections.defaultdict(int)
print(pogostosti["kruh"])... vrne nič... ter doda v seznam

for hrana in seznam_nakupov
    pogostosti[hrana]+=1
print(pogostosti)
print(dict(pogostosti))--- določi da bo output navaden slovar ne defaultdict



get... če poizvedujemo po izdelku (2 parametra)
collections.defaultdict()... če nekej apendamo, ki ni nujno da ma kakšen value

_____
množice

hrana=set()
hrana.add(...)
"""


def family_tree(family):
    seznam = {}
    for oce, sin in family:
        if oce in seznam:
            seznam[oce].append(sin)
        else:
            seznam[oce] = [sin]  ###########kca nardi [sin]!!!!!!!!!!
    return seznam


def children(tree, name):
    return tree.get(name, [])


def grandchildren(tree, name):
    seznam = []
    for otrok in children(tree, name):
        for vnuk in children(tree, otrok):
            seznam.append(vnuk)
    return seznam


def successors(tree, name):
    return


def pogostost(s):
    pogostosti = collections.defaultdict(int)  # POGOSTOST!!!!!!!!
    for beseda_ali_crka in s:
        pogostosti[beseda_ali_crka] += 1  ###########kako to prišteva v slovar... a se nebi začel z 1????
    return pogostosti


def v_key(pogostosti):
    max_beseda = None  # tukaj je shranjen key
    max_value = -1  # shranjen value
    for element, value in pogostosti.items():
        if value > max_value:
            max_beseda = element
            max_value = value
    return max_beseda


def najpogostejse(s):
    naj_beseda = v_key(pogostost(s.split()))
    naj_znak = v_key(pogostost(s))
    return (naj_beseda, naj_znak)


def najpogostejse_urejene(s):
    mnozica = set()
    for beseda_crka in s:
        mnozica.add(beseda_crka)
    min = None
    for neki in mnozica:
        print()
    return


def nasledniki(txt):
    a = txt.split()
    seznam = collections.defaultdict(list)
    for b, nasl_b in zip(a, a[1:]):
        seznam[b].append(nasl_b)
    return seznam


def floodfill(w, h, d, f, o, start_i, start_j):
    q = [(start_i, start_j)]
    while q:
        i, j = q.pop()
        if 0 <= i < h and 0 <= j < w and o[i][j] == o[start_i][start_j]:
            q.append((i - 1, j))
            q.append((i + 1, j))
            q.append((i, j - 1))
            q.append((i, j + 1))
            o[i][j] = -1
    return o


def num_components(w, h, d, f, o):
    cnt = 0
    for i in range(h):
        for j in range(w):
            if o[i][j] != -1:
                cnt += 1
                floodfill(w, h, d, f, o, i, j)
    return cnt


import collections
import unittest


class TestNaloge5(unittest.TestCase):
    def setUp(self):
        self.tree = {
            'alice': ['mary', 'tom', 'judy'],
            'bob': ['mary', 'tom', 'judy'],
            'ken': ['suzan'],
            'renee': ['rob', 'bob'],
            'rob': ['jim'],
            'sid': ['rob', 'bob'],
            'tom': ['ken']}

    def assertDictCounterEqual(self, first, second, msg=None):
        def dict_counter(d):
            d_copy = dict(d)
            for k, v in d_copy.items():
                d_copy[k] = collections.Counter(v)
            return d_copy

        self.assertDictEqual(dict_counter(first), dict_counter(second), msg)

    def test_family_tree(self):
        family = [
            ('bob', 'mary'),
            ('bob', 'tom'),
            ('bob', 'judy'),
            ('alice', 'mary'),
            ('alice', 'tom'),
            ('alice', 'judy'),
            ('renee', 'rob'),
            ('renee', 'bob'),
            ('sid', 'rob'),
            ('sid', 'bob'),
            ('tom', 'ken'),
            ('ken', 'suzan'),
            ('rob', 'jim')]
        self.assertDictCounterEqual(family_tree(family), self.tree)

    def test_children(self):
        self.assertCountEqual(children(self.tree, 'alice'), ['mary', 'tom', 'judy'])
        self.assertCountEqual(children(self.tree, 'mary'), [])
        self.assertCountEqual(children(self.tree, 'renee'), ['bob', 'rob'])
        self.assertCountEqual(children(self.tree, 'rob'), ['jim'])
        self.assertCountEqual(children(self.tree, 'suzan'), [])

    def test_grandchildren(self):
        self.assertCountEqual(grandchildren(self.tree, 'alice'), ['ken'])
        self.assertCountEqual(grandchildren(self.tree, 'bob'), ['ken'])
        self.assertCountEqual(grandchildren(self.tree, 'ken'), [])
        self.assertCountEqual(grandchildren(self.tree, 'mary'), [])
        self.assertCountEqual(grandchildren(self.tree, 'renee'), ['jim', 'mary', 'tom', 'judy'])
        self.assertCountEqual(grandchildren(self.tree, 'sid'), ['jim', 'mary', 'tom', 'judy'])
        self.assertCountEqual(grandchildren(self.tree, 'tom'), ['suzan'])

    def test_successors(self):
        self.assertCountEqual(successors(self.tree, 'tom'), ['ken', 'suzan'])
        self.assertCountEqual(successors(self.tree, 'sid'),
                              ['rob', 'bob', 'jim', 'mary', 'tom', 'judy', 'ken', 'suzan'])
        self.assertCountEqual(successors(self.tree, 'suzan'), [])
        self.assertCountEqual(successors(self.tree, 'ken'), ['suzan'])
        self.assertCountEqual(successors(self.tree, 'rob'), ['jim'])

    def test_najpogostejse(self):
        self.assertEqual(najpogostejse('a'), ('a', 'a'))
        self.assertEqual(najpogostejse('aa bb aa'), ('aa', 'a'))
        self.assertEqual(najpogostejse('in to in ono in to smo mi'), ('in', ' '))
        self.assertEqual(najpogostejse('abc abc abc abacbca'), ('abc', 'a'))
        self.assertEqual(najpogostejse('abc abc abc abacbcb'), ('abc', 'b'))
        self.assertEqual(najpogostejse('abc abc abc abacbcc'), ('abc', 'c'))

    def test_najpogostejse_urejene(self):
        self.assertEqual(najpogostejse_urejene('a'), (['a'], ['a']))
        self.assertEqual(najpogostejse_urejene('aa bb aa'), (['aa', 'bb'], ['a', ' ', 'b']))
        self.assertEqual(najpogostejse_urejene('in to in ono in to smo mi'),
                         (['in', 'to', 'mi', 'ono', 'smo'], [' ', 'o', 'i', 'n', 'm', 't', 's']))
        self.assertEqual(najpogostejse_urejene('abc abc abc abacbca'),
                         (['abc', 'abacbca'], ['a', 'b', 'c', ' ']))
        self.assertEqual(najpogostejse_urejene('abc abc abc abacbcb'),
                         (['abc', 'abacbcb'], ['b', 'a', 'c', ' ']))
        self.assertEqual(najpogostejse_urejene('abc abc abc abacbcc'),
                         (['abc', 'abacbcc'], ['c', 'a', 'b', ' ']))

    def test_sifra(self):
        self.assertEqual(sifra('\x19\x14\x1c]\x19\x0f\x14\t\x13\x18\t]\x12\x0e[\n\x1a\t\x18\x15\x12\x13\x1c'),
                         'big brother is watching')
        self.assertEqual(sifra('\xe1d\xe0q\xe5r\xf7b\xe0i'), 'strawberry')

    def test_nasledniki(self):
        self.assertDictCounterEqual(nasledniki('in in in in'), {'in': ['in', 'in', 'in']})
        self.assertDictCounterEqual(nasledniki('in to in ono in to smo mi'),
                                    {'smo': ['mi'], 'to': ['in', 'smo'], 'ono': ['in'], 'in': ['to', 'ono', 'to']})
        self.assertDictCounterEqual(nasledniki('danes je lep dan danes sije sonce'),
                                    {'lep': ['dan'], 'je': ['lep'], 'dan': ['danes'], 'danes': ['je', 'sije'],
                                     'sije': ['sonce']})

    def test_tekst(self):
        self.assertEqual(tekst({'in': ['in', 'in']}, 3), 'in in in')


if __name__ == '__main__':
    unittest.main(verbosity=2)
