# map(func,iterable)
# ord (iz ascii v ascii št)
# chr (ic ascii št v ascii)
# zip(za 2 seznama ali 2 vrednosti... pazi lahko [0] in[1:]
# capitalize
# lower
# .strip()
# type x izpiše str int...
# get(what to get, default value)
# ime.count("A") vrne št črk A v nizu
# sorted()
# \t , \n
# [izraz for spremenljivka in seznam if pogoj]
# del self.knjige[knjiga] to je delete itema  iz slovarja
# enumerate
# slovar[oseba].remove(value)
# print int('11111111', 2)- convert from bin to dec
# s.clear() clear seznam
######################################################################
#                            VAJE 5 TEDEN SEZNAMI
# PRIMERJANJE Z INDEKSI SKOZI 2 SEZNAMA
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


# PRIMERNAJNE Z NASLEDNJO Z INDEKSI
def domine(xs):  # Pazi na indekse.. zanka for x in range(len(xs) se uporablja za indeksirat!!!!!
    for x in range(len(xs) - 1):
        if xs[x][1] != xs[x + 1][0]:
            return False
    return True


# obračanje 90 stopninj izpit
def spremeni_smer(smer, obrat):
    smeri = "URDL"
    return smeri[(smeri.index(smer) + {"F": 0, "L": -1, "R": 1}[obrat]) % 4]


##########################################################################
#                         VAJE 6 TEDEN SLOVARJI
# OBIČAJNO DODAJANJE V SLOVAR
def family_tree(family):
    seznam = {}
    for oce, sin in family:
        if oce in seznam:
            seznam[oce].append(sin)
        else:
            seznam[oce] = [sin]  # TO JE SEZNAM
    return seznam


# COUNTER
from collections import *


def pogostost(s):
    pogostosti = collections.defaultdict(int)  # POGOSTOST!!!!!!!!!!!!!!!!!!!!!!!!! PRIMER DEFAULTDICTA
    for beseda_ali_crka in s:
        pogostosti[beseda_ali_crka] += 1  # v isti vrstici naštela iz 0 v 1
    return pogostosti


def nasledniki(txt):
    a = txt.split()
    seznam = collections.defaultdict(list)
    for b, nasl_b in zip(a, a[1:]):
        seznam[b].append(nasl_b)
    return seznam


#######################################################################
#                       VAJE REKURZIJA
# FILE NOVAKOVI STARŠI 9 DN
# FILE VAJE 9 REKURZIJA
def najmlajsi_stars(oseba):
    x = starost_starsa(oseba)
    for posameznik in rodbina[oseba]:
        star = najmlajsi_stars(posameznik)
        if x > star:
            x = star
    return x


# isto ta sam lepše
# Funkcija naj vrne starost in ime najmlajšega člana določene rodbine.
def najmlajsi_v_rodbini(ime):
    st = [(starost[ime], ime)]
    for x in otroci[ime]:
        st.append(najmlajsi_v_rodbini(x))
    return min(st)


# VSA IMENA V RODBINI
def izpis_imen_v_rodbini(ime):
    print(ime)
    for otrok in otroci[ime]:
        izpis_imen_v_rodbini(otrok)


# DOLŽINO POTI DO KONCA
def pot_do(od_ime, do_ime):
    if od_ime == do_ime:
        return [od_ime]
    for otrok in otroci[od_ime]:
        d = pot_do(otrok, do_ime)
        if d is not None:
            return [od_ime] + d


##vrne število vnukov
def vnukov(ime, rodovnik):
    slovar = 0
    for x in rodovnik[ime]:
        slovar += len(rodovnik[x])

    return slovar


# vne kdo v rodbini ima max vnukov
def najvec_vnukov(ime, rodovnik):
    najvec = vnukov(ime, rodovnik)
    for posameznik in rodovnik[ime]:
        otrokovih = najvec_vnukov(posameznik, rodovnik)
        if otrokovih > najvec:
            najvec = otrokovih
    return najvec


# globina vej
def crv(drevo):
    naj = 0
    for veja, naprej in drevo.items():
        if veja == "zelena":
            naj = max(naj, 1 + crv(naprej))
    return naj


# SEZNAMI
def convert(xs):
    if not xs:
        return ()
    return xs[0], convert(xs[1:])


# SEŠTEVANJE VGNEZDENIH SEZNAMOV
def sestej2(xs):
    if not xs:
        return 0
    if isinstance(xs[0], list):
        return sestej2(xs[0]) + sestej2(xs[1:])
    else:
        return xs[0] + sestej2(xs[1:])


# from bin to dec
def decimalno(s):
    if len(s) == 1:
        return int(s)
    return 2 * decimalno(s[:-1]) + int(s[-1])


# SODILIHI
def sodilihi(s):
    return not s or s[0][-1] == "a" and lihisodi(s[1:])


def lihisodi(s):
    return not s or s[0][-1] != "a" and sodilihi(s[1:])


def izmenjavanje(imena):
    return sodilihi(imena) or lihisodi(imena)


####################################################################
#                               CLASS
import random


class Pijanka(Turtle):
    def __init__(self):
        super().__init__()
        self.drinks = 0

    def drink(self):
        self.drinks += 1

    def forward(self, l):
        if self.drinks < 5:
            self.angle += random.randint(-self.drinks * 5, self.drinks * 5)
            super().forward(l)

    def turn(self, angle):
        if self.drinks < 5:
            super().turn(angle)


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


# 1 VRSTICA
# vsota kvadratov
def vsota_kvadratov(st):
    return sum([x ** 2 for x in range(st)])


def vsota_polindromov(st):
    return sum([x ** 2 for x in range(st) if str(x) == str(x)[::-1]])  # str se lahko obrača z [::-1]


print(vsota_kvadratov(101))
print(vsota_polindromov(1001))
