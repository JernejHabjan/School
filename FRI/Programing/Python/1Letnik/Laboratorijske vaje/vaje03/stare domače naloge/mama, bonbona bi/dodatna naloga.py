__author__ = 'Roglan'
# TODO https://ucilnica.fri.uni-lj.si/0mod/page/view.php?id=7640
delitev = [int(x) for x in input("Seznam: ").split()]
goltanje = [int(x) for x in input("Goltanje: ").split()]
for stevilo in delitev:
    if stevilo % 2 != 0:
        liho = "da, otrok je ostal brez bonbonov"
        break
    else:
        liho = "Ne"

print(liho)

# TODO
