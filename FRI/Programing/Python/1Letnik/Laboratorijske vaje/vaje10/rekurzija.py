# https://ucilnica.fri.uni-lj.si/mod/page/view.php?id=7796

# Napišite funkcijo, ki izpiše vsa imena v določeni rodbini.
def izpis_imen_v_rodbini(ime):
    print(ime)
    for otrok in otroci[ime]:
        izpis_imen_v_rodbini(otrok)


# Podobno kot prejšnja naloga, le da tokrat vrnete seznam imen v rodbini.
def imena_v_rodbini(ime):
    seznam = [ime]
    for otrok in otroci[ime]:
        seznam2 = imena_v_rodbini(otrok)
        seznam.extend(seznam2)
    return seznam


# Funkcija naj vrne starost in ime najmlajšega člana določene rodbine.
def najmlajsi_v_rodbini(ime):
    st = [(starost[ime], ime)]
    for x in otroci[ime]:
        st.append(najmlajsi_v_rodbini(x))
    return min(st)


# Napišite funkcijo, ki pove kako globoko v rodbini osebe od_ime je oseba z imenom do_ime.

def globina_do(od_ime, do_ime):
    if od_ime == do_ime:
        return 0
    for otrok in otroci[od_ime]:
        d = globina_do(otrok, do_ime)
        if d is not None:
            return d + 1


# Reši podobno nalogo kot je prejšnja, le da naj funkcija ne vrne razdalje do določene osebe, temveč pot do nje.
def pot_do(od_ime, do_ime):
    if od_ime == do_ime:
        return [od_ime]
    for otrok in otroci[od_ime]:
        d = pot_do(otrok, do_ime)
        if d is not None:
            return [od_ime] + d


###################################################################################
####################################################################################

# Vaje s seznami

# Včasih so programerji sestavljali sezname z gnezdenjem terk.
# Ubogi reveži, druge izbire niso imeli. Napiši funkcijo convert(xs), ki seznam xs pretvori v ta arhaični zapis.
def convert(xs):
    if not xs:
        return ()
    return xs[0], convert(xs[1:])


# Napiši funkcijo, ki izračuna dolžino seznama gnezdenih terk.
def length(xs):
    if not xs:
        return 0
    return 1 + length(xs[1])


# Napiši funkcijo, ki ponovi vsak element seznama.
def dup(xs):
    if not xs:
        return ()
    return xs[0], (xs[0], dup(xs[1]))


# Napiši funkcijo, ki obrne seznam.
def reverse(xs, ys=()):
    if xs == ():
        return ys
    return reverse(xs[1], (xs[0], ys))


# Napiši funkcijo sum(xs), ki sešteje elemente vgnezdenega seznama xs. Pomagajte si s funkcijo isinstance.
def sestej(xs):
    s = 0
    for x in xs:
        if isinstance(x, list):
            s += sestej(x)
        else:
            s += x
    return s


# še brez for zanke
def sestej2(xs):
    if not xs:
        return 0
    if isinstance(xs[0], list):
        return sestej2(xs[0]) + sestej2(xs[1:])
    else:
        return xs[0] + sestej2(xs[1:])


# Napiši program, ki prebere datoteko.
# V prvem stolpcu je zapisan id vrstice, v drugem id starša, v tretjem stolpcu pa najdete ime izdelka.
def izpis(db, pid='0', prefix=''):
    for id, name in db[pid]:
        if id not in db:
            print(prefix + name)
        izpis(db, id, prefix + name + ' / ')


###################################################################################

otroci = {
    "Adam": ["Matjaž", "Cilka", "Daniel", "Erik"],
    "Aleksander": [],
    "Alenka": [],
    "Barbara": [],
    "Cilka": [],
    "Daniel": ["Elizabeta", "Hans"],
    "Erik": [],
    "Elizabeta": ["Ludvik", "Jurij", "Barbara", "Herman", "Mihael"],
    "Franc": [],
    "Herman": ["Margareta"],
    "Hans": [],
    "Jožef": ["Alenka", "Aleksander", "Petra"],
    "Jurij": ["Franc", "Jožef"],
    "Ludvik": [],
    "Margareta": [],
    "Matjaž": ["Viljem"],
    "Mihael": [],
    "Petra": [],
    "Tadeja": [],
    "Viljem": ["Tadeja"],
}

starost = {
    "Adam": 111, "Matjaž": 90, "Cilka": 88, "Daniel": 85, "Erik": 83,
    "Viljem": 58, "Tadeja": 20, "Elizabeta": 67, "Hans": 64, "Ludvik": 50,
    "Jurij": 49, "Barbara": 45, "Herman": 39, "Mihael": 32, "Franc": 30,
    "Jožef": 29, "Margareta": 10, "Alenka": 5, "Aleksander": 7, "Petra": 9}

import unittest
from test.support import captured_stdout


class TestRekurzija(unittest.TestCase):
    def test_izpis_imen_v_rodbini(self):
        def f(ime):
            with captured_stdout() as stdout:
                izpis_imen_v_rodbini(ime)
            return stdout.getvalue().splitlines()

        self.assertCountEqual(['Hans'], f('Hans'))
        self.assertCountEqual(['Herman', 'Margareta'], f('Herman'))
        self.assertCountEqual(['Jurij', 'Franc', 'Jožef', 'Alenka', 'Aleksander', 'Petra'], f('Jurij'))
        self.assertCountEqual(['Viljem', 'Tadeja'], f('Viljem'))
        self.assertCountEqual(
            ['Daniel', 'Elizabeta', 'Ludvik', 'Jurij', 'Franc', 'Jožef', 'Alenka', 'Aleksander', 'Petra', 'Barbara',
             'Herman', 'Margareta', 'Mihael', 'Hans'], f('Daniel'))
        self.assertCountEqual(
            ['Adam', 'Matjaž', 'Viljem', 'Tadeja', 'Cilka', 'Daniel', 'Elizabeta', 'Ludvik', 'Jurij', 'Franc', 'Jožef',
             'Alenka', 'Aleksander', 'Petra', 'Barbara', 'Herman', 'Margareta', 'Mihael', 'Hans', 'Erik'], f('Adam'))

    def test_imena_v_rodbini(self):
        self.assertCountEqual(['Hans'], imena_v_rodbini('Hans'))
        self.assertCountEqual(['Herman', 'Margareta'], imena_v_rodbini('Herman'))
        self.assertCountEqual(['Jurij', 'Franc', 'Jožef', 'Alenka', 'Aleksander', 'Petra'], imena_v_rodbini('Jurij'))
        self.assertCountEqual(['Viljem', 'Tadeja'], imena_v_rodbini('Viljem'))
        self.assertCountEqual(
            ['Daniel', 'Elizabeta', 'Ludvik', 'Jurij', 'Franc', 'Jožef', 'Alenka', 'Aleksander', 'Petra', 'Barbara',
             'Herman', 'Margareta', 'Mihael', 'Hans'], imena_v_rodbini('Daniel'))
        self.assertCountEqual(
            ['Adam', 'Matjaž', 'Viljem', 'Tadeja', 'Cilka', 'Daniel', 'Elizabeta', 'Ludvik', 'Jurij', 'Franc', 'Jožef',
             'Alenka', 'Aleksander', 'Petra', 'Barbara', 'Herman', 'Margareta', 'Mihael', 'Hans', 'Erik'],
            imena_v_rodbini('Adam'))

    def test_najmlajsi_v_rodbini(self):
        self.assertEqual((64, 'Hans'), najmlajsi_v_rodbini('Hans'))
        self.assertEqual((5, 'Alenka'), najmlajsi_v_rodbini('Daniel'))
        self.assertEqual((10, 'Margareta'), najmlajsi_v_rodbini('Herman'))
        self.assertEqual((20, 'Tadeja'), najmlajsi_v_rodbini('Viljem'))
        self.assertEqual((7, 'Aleksander'), najmlajsi_v_rodbini('Aleksander'))
        self.assertEqual((5, 'Alenka'), najmlajsi_v_rodbini('Adam'))

    def test_globina_do(self):
        self.assertEqual(0, globina_do('Hans', 'Hans'))
        self.assertEqual(1, globina_do('Daniel', 'Hans'))
        self.assertEqual(2, globina_do('Adam', 'Hans'))
        self.assertEqual(4, globina_do('Adam', 'Franc'))
        self.assertEqual(5, globina_do('Adam', 'Petra'))
        self.assertEqual(3, globina_do('Elizabeta', 'Aleksander'))
        self.assertEqual(3, globina_do('Adam', 'Tadeja'))
        self.assertEqual(2, globina_do('Daniel', 'Ludvik'))

    def test_pot_do(self):
        self.assertEqual(['Hans'], pot_do('Hans', 'Hans'))
        self.assertEqual(['Daniel', 'Hans'], pot_do('Daniel', 'Hans'))
        self.assertEqual(['Adam', 'Daniel', 'Hans'], pot_do('Adam', 'Hans'))
        self.assertEqual(['Adam', 'Daniel', 'Elizabeta', 'Jurij', 'Franc'], pot_do('Adam', 'Franc'))
        self.assertEqual(['Adam', 'Daniel', 'Elizabeta', 'Jurij', 'Jožef', 'Petra'], pot_do('Adam', 'Petra'))
        self.assertEqual(['Elizabeta', 'Jurij', 'Jožef', 'Aleksander'], pot_do('Elizabeta', 'Aleksander'))
        self.assertEqual(['Adam', 'Matjaž', 'Viljem', 'Tadeja'], pot_do('Adam', 'Tadeja'))
        self.assertEqual(['Daniel', 'Elizabeta', 'Ludvik'], pot_do('Daniel', 'Ludvik'))

    def test_sum(self):
        self.assertEqual(sum([]), 0)
        self.assertEqual(sum([1, 2, 3, 4, 5]), 15)
        self.assertEqual(sum([1, [], [2, 3, [4]], 5]), 15)
        self.assertEqual(sum([1, [], [2, 3, [4]], 5, [[[[10], 6], [[7], 9], 8]]]), 55)

    def test_convert(self):
        self.assertEqual(convert([]), ())
        self.assertEqual(convert([1]), (1, ()))
        self.assertEqual(convert([5, 4, 6, 7, 1]), (5, (4, (6, (7, (1, ()))))))
        self.assertEqual(convert([5, 4, 6, 7, 1, 0]), (5, (4, (6, (7, (1, (0, ())))))))

    def test_length(self):
        self.assertEqual(length(()), 0)
        self.assertEqual(length((1, ())), 1)
        self.assertEqual(length((5, (4, (6, (7, (1, ())))))), 5)
        self.assertEqual(length((5, (4, (6, (7, (1, (0, ()))))))), 6)

    def test_dup(self):
        self.assertEqual(dup((1, (2, ()))), (1, (1, (2, (2, ())))))
        self.assertEqual(dup((5, (4, (6, (7, (1, ())))))), (5, (5, (4, (4, (6, (6, (7, (7, (1, (1, ())))))))))))

    def test_reverse(self):
        self.assertEqual(reverse((5, (4, (6, (7, (1, ())))))), (1, (7, (6, (4, (5, ()))))))


if __name__ == '__main__':
    unittest.main(verbosity=2)
