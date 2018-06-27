# vsi zapiski https://ucilnica.fri.uni-lj.si/mod/page/view.php?id=13583


# eval ->>
a = {'a': 'a', 'b': 'b'}
eval('a == "a"', a)
# returns True


print(eval("5+6"))

exec("c=5&3")

# imenski prostori -> kot pointerji na številke itd...

# vezanje na x
# bind -> x=42
# import modul as x
# from modul import x
# import x-> če se modul imenuje x
# for x in range..
# def f(x) ->kot argument
# def x():
# class x:
# except indexError as x
# spremenljivke v slovarju
x = 3
print(globals())
# vse spremenljivke so v globals()
print(hash(x))  # ko mi printamo x... je to ubistvu to
print(hash("lol"))  # random cifra->tako delajo slovarji


# z=7 == globals()[z]=7
def f(x):
    if x == 0:
        return 1
    n = x
    t = f(n - 1)
    print(locals())  # izpiše lokalne spremenlivke
    return x * t


print(f(10))
print(f)  # izpiše kje se nahaja
# globals ne izpiše spremenljivke ki so v funkcijah
# KROŽNIKI IN MIZE
p = 1


def x():
    print(p)

    def y():
        z = 2
        print(z)
        print(p)

    y()


x()  # ce ni v lokals pjt pogledat v globals


# primer z indeksi
def indeks(s, x):
    from random import randint
    while True:
        i = randint(0, len(s) - 1)
        if s[i] == x:
            return i


t = list(range(1000))
print(indeks(t, 500))

n = 1


def g():
    print(n)
    # n=2 POTEM NE DELA


g()

nonlocal lol  # gleda vse lol iz tega "krožnika" -če maš gnezdene... išče local-toliko da jo najde... toliko za nazaj

# import lahko damo v funkcijo kot ali jo kdaj sploh kličemo in če uporabnik nima installanga itd...
# importaš lah kot import mymodul če je v istmu direktoriju
# def f():
#   from stevila import popolno
# f() ->dela import

# ko se modul uvozi-se modul izvede

# modul ima svoj prostor... lahko pogledamo kot print(myModul.__dict__["myfunction"]) ...vsak modul je svoja miza
# myModul.__dict__["__name__"] -pove ime modula
# glavna funkcija je __main__

# mymodul.karkolJeŽeKotImeNoter lahko to pokličemo ==myModul.__dict__["myfunction"]... namesto myFunction je lahko tudi spremeljivka
a = 3
b = 4
print(eval("a+b"))

# se slovar noter
t = {"a": 2}
eval("a+b", t)
