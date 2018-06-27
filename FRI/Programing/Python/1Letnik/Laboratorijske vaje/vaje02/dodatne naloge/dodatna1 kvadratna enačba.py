__author__ = 'jh0228'
from math import *

a = int(input("vpisi a"))
b = int(input("vpisi b"))
c = int(input("vpisi c"))
print("vpisali ste ", a, "x", "^2+", b, "x+", c, "=0")
diskriminanta = b ** 2 - 2 * a * c
if diskriminanta > 0:
    x1 = (-b + sqrt(diskriminanta) / 2 * a)
    x2 = (-b - sqrt(diskriminanta) / 2 * a)
    print("formula ", a, "x", "^2+", b, "x+ ", c, "=0 ima realni resitvi ", x1, "in", x2)
elif diskriminanta == 0:
    x = (-b + sqrt(diskriminanta) / 2 * a)
    print("formula ", a, "x", "^2+", b, "x+ ", c, "=0 ima realno resitev ", x)
else:
    print("formula ", a, "x", "^2+", b, "x+ ", c, "=0 nima realnih ničel")
