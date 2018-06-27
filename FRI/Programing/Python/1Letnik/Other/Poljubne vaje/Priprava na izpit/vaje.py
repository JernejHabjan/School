xs = [42, 5, 4, -7, 2, 12, -3, -4, 11, 42, 2]


def vsebuje42(xs):
    for x in xs:
        if x == 42:
            return True
    return False


# print(vsebuje42(xs))

def vsebuje42counter(xs):
    v = 0
    for x in xs:
        if x == 42:
            v += 1
    return v


# print(vsebuje42counter(xs))

def samo(xs):
    for x in xs:
        if x % 42 != 0:
            return False
    return True


# print(samo(xs))

filmi = [
    ('Poletje v skoljki 2', 6.1),
    ('Ne cakaj na maj', 7.3),
    ('Pod njenim oknom', 7.1),
    ('Kekec', 8.1),
    ('Poletje v skoljki', 7.2),
    ('To so gadi', 7.7),
]


def film(xs):
    seznam = []
    for x in xs:
        if x[1] >= 7:
            seznam.append(x[0])
    return seznam


# print(film(filmi))


def p(l, i=1):
    if i > len(l):
        return
    p(l, i + 1)
    print(l[len(l) - i])


def naj(xs):
    return max(xs)


# print(naj(xs))

def enumerat(xs):
    for i, x in enumerate(xs):
        print((i, x))


# print(enumerat(xs))

def prast(xs):
    seznam = dict()
    for x in xs:
        for i in range(2, x):
            if x % i == 0:
                if x in seznam:
                    seznam[x].append(i)
                else:
                    seznam[x] = [i]
    return seznam


# print(prast(xs))

#################################################
# PRINTANJE IZ SEZNAMA
def p(xs):
    if not xs:
        return

    p(xs[:-1])
    print(xs[-1])


# print(p(xs))


def najd(xs):
    a = xs.split()
    najd = 0
    na = ""
    for x in a:
        if len(x) > najd:
            na = x
            najd = len(x)
    return na


# print(najd("an ban pet podgan"))

nov = [[2, 4, 1], [3, 1], [], [8, 2], [1, 1, 1, 1]]


def podsez(xs):
    vsota = 0
    for x in xs:
        vsota += sum(x)
    return vsota


# print(podsez(nov))

def vsota_seznamov(xss):
    return list(map(sum, xss))  ##############     MAP


def slikaj(f, xs):
    seznam = []
    for x in xs:
        seznam.append(f(x))
    return seznam


# print(slikaj(len, "Daydream delusion limousine eyelash".split()))

family = [('bob', 'mary'), ('bob', 'tom'), ('bob', 'judy'), ('alice', 'mary'),
          ('alice', 'tom'), ('alice', 'judy'), ('renee', 'rob'), ('renee', 'bob'),
          ('sid', 'rob'), ('sid', 'bob'), ('tom', 'ken'), ('ken', 'suzan'), ('rob', 'jim')]


def fam_tree(family):
    slovar = dict()
    for key, value in family:
        if key in slovar:
            slovar[key].append(value)
        else:
            slovar[key] = [value]
    return slovar


# print(fam_tree(family))

def children(tree, name):
    return tree[name]


tree = fam_tree(family)


# print(children(tree,"alice"))

def children(tree, name):
    return tree.get(name, [])


def succesors_rekurzija(tree, name):
    seznam = [name]
    for x in tree[name]:
        sezn2 = children(tree, x)
        seznam.extend(sezn2)
    return seznam[1:]


# print(succesors_rekurzija(tree,"tom"))
from random import randint


def generate(n, i=1):
    xs = []
    for x in range(randint(1, 3)):
        xs.append(randint(0, 9))

        if i <= n:
            xs.append(generate(n, i + 1))
    return xs


print(generate(randint(3, 7)))

#########################

"""
import time
import threading

out = 1

def stream_output():
    t = time.time()
    global out
    while 1:
        if time.time() - t > 1.0:
            t = time.time()
            out = 0 if out else 1

t = threading.Thread(target=stream_output)
t.setDaemon(True)
t.start()

# -------------------------------------------------------
# nekaj izpiši, ko se spremeni vrednost spremenljivke 'out'
# (iz 1 v 0 ALI iz 0 v 1)

while 1:
    print(out)
    time.sleep(0.1)
"""
###############################
