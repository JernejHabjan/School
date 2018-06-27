from math import sqrt


def neki(a, b, c=1, d=0, j="ana"):  # defaulti
    print(neki(5, 8))
    print(neki(5, 8, 7, 234))  # nadomesti defaulte


def neki(a, b, *s):
    print(neki(4, 5, 6, 6, 7, 6, 55, 6, ))  # a in b sprejmeta po 1... v *s pa gre vse ostalo


class Vector():  # importali smo list (Vector(list)
    # potem nima values ter nekaterih classov
    def __init__(self, *s):  # sprejme poljubno stevilo argumentov
        # super().__init__(s) - to dodamo ko dedujemo iz "list"
        self.values = list(s)

        # objekti so tisti ki vse naredijo

    def __str__(self):  # metoda ne sme imeti argumentv########################################
        # to moramo delati ker ne zna izracunati za "Vector"... ne zna ga izpisati

        # return "Benjamin" #print(a)--- vrne Benjamin
        # seznam rece printu kaj mora printati
        return "[" + ",".join(str(e) for e in self.values) + "]"  # v printu smo sestavili izpis kako naj izpise vektor

    def __abs__(self):  # za dolzino vektorja
        return sqrt(sum(x ** 2 for x in self.values))

        # če ni tega reče... "Vector" has no len()

    def __len__(self):
        return len(self.values)

    def __add__(self, other):
        if len(self) != len(other):
            raise ValueError("mismatching vector lenghts")  # namerno sprozili napako če vektorja nista enako dolga

        coords = [x + y for x, y in zip(self.values, other.values)]  # vrne seznam
        return Vector(*coords)  # tu smo iz seznama spremenili v argumente-seznam razpakira v argumente

    def __mul__(self, other):
        coords = [other * x for x in self.values]
        return Vector(*coords)

    __rmul__ = __mul__

    def __sub__(self, other):
        if len(self) != len(other):
            raise ValueError("mismatching vector lenghts")  # vrne napako
        coords = [x - y for x, y in zip(self.values, other.values)]  # vrne seznam
        return Vector(*coords)

    def __neg__(self):
        coords = [-x for x in self.values]
        return Vector(*coords)

        # se drugacen sub - sesteje in obrnjeni other

    def __sub__(self, other):
        return self + -other

    def __getitem__(self, item):
        return self.values[item]

    def __getitem__(self, item):  # vse te bedarije lah not pišeš
        return 42
        return i ** 3
        return "benjamin"  # kaj je i-ti element = benjamin


a = Vector(3, 7, 1)
b = Vector(-2, 3, 0)
e = Vector(-4, 6, 0)
c = Vector(3, 7)
d = Vector(3, 7, 1, 5, 6, 8)
print(a)

t = abs(a)
print(abs(a))

print(len(a))

# sestevanje vektorjev
t = a + b
print(t)
print(t + e)  # spremenili ga v vektor da lahko računamo s tem naprej

# mnozenje
print(t * 5)
# print(5*t)#od 5 je poklical mul "a.__mul__(5)"!= "5.__mul__(a)"
# ker je mnozenje asociativno se poklice rmul
print(5 * t)

# odstevanje
u = t - a
print(u)  # isto kot sestevanje

r = -t  # unarni minus
print(r)

# dostop do komponent
print(t[0], t[1], t[2])  # nezna niti indeksirati neki.__getitem__(1)

print(t + d)  # to je sestevanje razlicnih dolzin
