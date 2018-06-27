__author__ = 'jh0228'
from math import *

stranica1 = float(input("vpisi dolzino 1. stranice"))
stranica2 = float(input("vpisi dolzino 2. stranice"))
stranica3 = float(input("vpisi dolzino 3. stranice"))
s = (stranica1 + stranica2 + stranica3) / 2
ploscina = sqrt(s * (s - stranica1) * (s - stranica2) * (s - stranica3))
polmer_vcrtan_krog = ploscina / s
vcrtan_krog = pi * polmer_vcrtan_krog ** 2
ocrtan_krog = ((stranica1 * stranica2 * stranica3) / (4 * ploscina))
print("ploscina kroga je", ploscina, " ploscina vcrtanega kroga je", vcrtan_krog, " ploscina ocrtanega pa", ocrtan_krog)
# TODO NAROBE OCRTAN KROG ENACBA
