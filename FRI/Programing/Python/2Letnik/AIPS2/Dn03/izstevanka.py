from random import randint
def incM(_m, n):  # increase m
    _m += 1
    if _m >= n:
        _m %= n
    return _m
def init():
    m, n = input("vpisite m in n\n").split()
    m, n = int(m), int(n)  # m je zmanjšan za 1 da dela lepo z indexi
    ##INIT ARRAYS
    ax_bool = []
    for i in range(n):  # init empty array with indices
        ax_bool.append(True)
    return m, n, ax_bool
def izstevanka(randm=False):
    koef = 2

    m, n, ax_bool = init()
    _n = n
    _m = m - 1
    while _n:  ##ODSTEVAJ
        ax_bool[_m] = False
        print(_m)  # taj biv izlocen

        if randm:  ##IZBERE NAKLJUČEN M.... LAHKO TUDI PREKO USER INPU AMPAK... TOO SLOW
            _m = randint(0, 100)  # predpostavka da otroci znajo steti do 100
            # _m = input("izpadli otrok, vpisi nov M") # <- z user inputom
            if _m >= n:
                _m %= n
        _n -= 1
        move_count = 0
        while (move_count < m):  # mormo premaknt vsaj m-krat
            _m = incM(_m, n)
            if ax_bool[_m] != False:  # izpadle otroke ignoriramo
                move_count += 1
            if _n == 0:
                return
        ################ IMPROVEMENT -> VSAKO POLOVICO ARRAYA GA PREPIŠEMO V NOVEGA#############
        if int(n / koef) > 20 and _n == int(n / koef): ##na polovici, potem četrtini, potem osmini še enkrat prepiše array
            ax_bool1 = []
            for i in range(int(n / koef)):  # init empty array with indices
                ax_bool1.append(True)
            n = len(ax_bool1)
            ax_bool = ax_bool1
            _m %= n  # še modulam da nebo out of bounds

            koef *=2 # da dobimo delitve še na četrtine itd
def main():
    izstevanka()
    izstevanka(True)
main()
