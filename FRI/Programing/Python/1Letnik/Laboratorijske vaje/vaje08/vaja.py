__author__ = 'Roglan'


# vsota kvadratov
def vsota_kvadratov(st):
    return sum([x ** 2 for x in range(st)])


def vsota_polindromov(st):
    return sum([x ** 2 for x in range(st) if str(x) == str(x)[::-1]])  # str se lahko obrača z [::-1]


print(vsota_kvadratov(101))
print(vsota_polindromov(1001))


# zamenjava crk
def subs(niz, polozaj):
    return "".join([niz[y] for y in list(map(int, polozaj))])  # nice--- poglej si jo


print(subs("komar", "23401"))

# povprecje in standardni odklon
from math import sqrt


def povp_odkl(s):
    m = sum([x for x in s]) / len(s)  # Yield vraca generator if printed
    s = sqrt(sum([(x - m) ** 2 for x in s]) / len(s))  # **2 in ne ^2
    return (m, "%.4f" % s)


print(povp_odkl([183, 168, 175, 176, 192, 180]))

# morsejeva abeceda
morse = {
    'A': '.-',
    'B': '-...',
    'C': '-.-.',
    'D': '-..',
    'E': '.',
    'F': '..-.',
    'G': '--.',
    'H': '....',
    'I': '..',
    'J': '.---',
    'K': '-.-',
    'L': '.-..',
    'M': '--',
    'N': '-.',
    'O': '---',
    'P': '.--.',
    'Q': '--.-',
    'R': '.-.',
    'S': '...',
    'T': '-',
    'U': '..-',
    'V': '...-',
    'W': '.--',
    'X': '-..-',
    'Y': '-.--',
    'Z': '--..',
    '1': '.----',
    '2': '..---',
    '3': '...--',
    '4': '....-',
    '5': '.....',
    '6': '-....',
    '7': '--...',
    '8': '---..',
    '9': '----.',
    '0': '-----',
    ' ': ''
}


def txt2morse(s):
    return ' '.join(morse[c] for c in s)


morse_r = {v: k for k, v in morse.items()}


def morse2txt(s):
    return ''.join(morse_r[c] for c in s.split(' '))


def valid(isbn):
    return len(isbn) == 10 and sum((10 if c == 'X' else int(c)) * (10 - i) for i, c in enumerate(isbn)) % 11 == 0


# Počasnejša rešitev (čas izvajanja: 200 s)
datoteke = [open('datoteke/{}.txt'.format(i)).read() for i in range(10)]
for i in range(10):
    for j in range(i):
        for k in range(0, len(datoteke[i]) - 1000):
            if datoteke[i][k:k + 1000] in datoteke[j]:
                print(i, j)

# Hitrejša rešitev (čas izvajanja: < 1 s)
# Ta rešitev ni več povsem pravilna, saj se lahko zgodi, da vrne tudi imena
# datotek v katerih se ponovi 500 znakov, nikoli pa ne spregleda datotek v
# katerih se ponovi vsaj 1000 znakov.
datoteke = [open('datoteke/{}.txt'.format(i)).read() for i in range(10)]
for i in range(10):
    for j in range(i):
        for k in range(0, len(datoteke[i]) - 500, 500):
            if datoteke[i][k:k + 500] in datoteke[j]:
                print(i, j)
