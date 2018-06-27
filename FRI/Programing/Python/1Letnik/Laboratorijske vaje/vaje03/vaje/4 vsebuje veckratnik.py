__author__ = 'Roglan'
povecuje_veckratnik = 1
xs = [42, 5, 4, -7, 2, 12, -3, -4, 11, 42, 2]
for stevec in xs:
    veckratnik = stevec * povecuje_veckratnik
    tester = 42 * povecuje_veckratnik
    if tester == veckratnik:
        print(True)
        break
    else:
        print(False)
        break
    povecuje_veckratnik += 1
