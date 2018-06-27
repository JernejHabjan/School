__author__ = 'Roglan'
xs = [42, 5, 4, -7, 2, 12, -3, -4, 11, 42, 2]
nov_array = []
povecuje_veckratnik = 1
for stevec in xs:
    veckratnik = stevec * povecuje_veckratnik
    tester = 42 * povecuje_veckratnik
    if tester == veckratnik:
        nov_array.append(stevec)
    povecuje_veckratnik += 1
print(len(nov_array) == len(xs))
