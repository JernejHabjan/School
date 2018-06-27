__author__ = 'Roglan'
# TODO https://ucilnica.fri.uni-lj.si/mod/page/view.php?id=7640

seznam = [int(x) for x in input("Seznam: ").split()]
for stevilo in seznam:
    if stevilo % 2 != 0:
        liho = "da, otrok je ostal brez bonbonov"
        break
    else:
        liho = "Ne"

print(liho)
