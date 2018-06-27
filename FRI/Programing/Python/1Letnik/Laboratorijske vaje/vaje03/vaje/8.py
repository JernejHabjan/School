__author__ = 'jh0228'
"""220 in 284 sta prijateljski števili. Delitelji 220 so 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 in 110.
 Če jih seštejemo, dobimo 284. Delitelji 284 pa so 1, 2, 4, 71 in 142. Vsota teh števil pa je 220.
  Napiši program, ki mu uporabnik vnese število. Program preveri, ali ima to število prijatelja in če ga ima, ga izpiše.
   Če ga nima, pa pove, da ga nima. Primer: če uporabnik vpiše 220, program odgovori 284.
    Če uporabnik vpiše 222, program odgovori, da 222 nima prijateljev."""

sestevek1 = 0
stevilo1 = int(input("vpisi stevilo: "))
for deljitelji in range(1, stevilo1):
    if stevilo1 % deljitelji == 0:
        sestevek1 += deljitelji
sestevek2 = 0
stevilo2 = int(input("vpisi stevilo: "))
for deljitelji in range(1, stevilo2):
    if stevilo2 % deljitelji == 0:
        sestevek2 += deljitelji
if sestevek1 == stevilo2 or sestevek2 == stevilo1:
    print("stevili sta prijatelja :D")
