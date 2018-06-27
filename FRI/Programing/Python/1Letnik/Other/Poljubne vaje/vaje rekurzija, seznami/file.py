# rekurzija
xs = [1, 2, 3, 4, 5, 6]


def rekurzija1(xs):
    if not len(xs):
        return []
    return [xs[0]] + rekurzija1(xs[1:])


# print(rekurzija1(xs))

def dolzina(xs):
    if not len(xs):
        return 0
    return 1 + dolzina(xs[1:])


# print(dolzina(xs))

def duplicate(xs):
    if not len(xs):
        return []
    return [xs[0], xs[0]] + duplicate(xs[1:])


# print(duplicate(xs))

def sestejVgnezdene(xs):
    s = 0
    for x in xs:
        if isinstance(x, list):
            s += sestejVgnezdene(x)
        else:
            s += x
    return s


# print(sestejVgnezdene(xs))

##################################################
# seznami
x = [1, 2, 3]
y = [4, 5, 6]


def zipper(list1, list2):
    seznam = []
    for x0, y0 in zip(list1, list2):
        seznam.append((x0, y0))
    return tuple(seznam)


# print(zipper(x,y))

def joiner(list1, list2):
    seznam = []
    for i in range(len(list1)):
        seznam.append((list1[i], list2[i]))
    return tuple(seznam)


# print(joiner(x,y))

###################################################

# slovarji
def dict1(element, key):
    d = dict()
    d[key] = element
    return d


# print(dict1("čmarjan","feget"))

def poizDict():
    d = {"lol": "feget", 35: "noob"}
    return d.get("lo", "tega v slovarju ni")


# print(poizDict())

def forDict():
    d = {"lol": "feget", 35: "noob"}
    for key, var in d.items():
        print(var, key)


# print(forDict())

###############################################################

# class
from random import randint


class Clovek():
    def __init__(self, name, age):
        self.age = age
        self.name = name
        self.x = 0
        self.y = 0

    def walk(self):
        self.x += randint(-5, 5)
        self.y += randint(-5, 5)
        # World().add_coords().update

    def pozdravi(self):
        print("zivjo, moje ime je {} in sem star {} let".format(self.name, self.age))


class Policeman(Clovek):
    def __init__(self, name, age):
        super().__init__(name, age)

    def arrest(self, human):
        World.seznamLjudi.remove(human)

    def pozdravi(self):
        print("zivjo sem policis {}".format(self.ime))


class World():
    def __init__(self):
        self.peoplecoord = []
        self.seznamLjudi = []

    def add_coords(self):
        self.peoplecoord.append((Clovek.x, Clovek.y))


ponudbe = [(set("ga"), set("ijk")),
           (set("abc"), set("def")),
           (set("efc"), set("bg"))]
print(ponudbe)
