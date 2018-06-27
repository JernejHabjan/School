__author__ = 'Roglan'


def s(n):
    seznam4 = []
    for x in range(n):
        if x % 4 == 0:
            seznam4.append(x)
    return seznam4


def t(n):
    seznam3 = []
    for x in range(n):
        if x % 3 == 0:
            seznam3.append(x)
    return seznam3


def is_prime(n):
    for i in range(2, n):
        if n % i == 0:
            return False
    return True


def prastevila(n):
    seznam = []
    for stevila in range(2, n):
        if is_prime(stevila):
            seznam.append(stevila)
    return seznam


prvi = []
prvi.extend(s(101))
prvi.extend(prastevila(101))

drugi = []
drugi.extend(t(101))
drugi.extend(prastevila(101))

a = set()
for neki in prvi:
    for bla in drugi:
        a.add((neki, bla))

print(len(a))
