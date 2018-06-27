from math import *

hitrost = float(input("vpisi hitrost projektila: "))
kot = float(input("pod katerim kotom je projektil izstreljen: "))
radians = kot * (pi / 180)
razdalja = (hitrost ** 2 * sin(2 * radians)) / 9.81
print("kroglja je letela: ", abs(razdalja), " metrov")
