from random import randint


class Autohisa():
    def __init__(self):
        self.prostorov = 100
        self.nadstropij = 2
        self.zasedeni = 0
        self.alarm = False
        self.stanje = 100
        self.vozila = []
        self.varovanje = 50

    def izpis_parkirnine(self, avto):
        print(
            "vasa parkirnina znasa {}".format(self.parkirnina))  ####to je podano pri vsakem prevoznem sredstvu posebej

    def izpis_vozil(self):
        return self.vozila

    def izpis_stanja(self):
        if self.stanje == 100:
            print("Garage is like new {} life".format(self.stanje))
        if self.stanje > 70:
            print("gosh... better clean this mess {} life".format(self.stanje))
        if self.stanje > 30:
            print("nono its critical {} life".format(self.stanje))
        if self.stanje > 0:
            print("ure going far with this{} life".format(self.stanje))
        if self.stanje <= 0:
            print("andd its dead {} life".format(self.stanje))

    def preveri_zasedenost(self):
        if self.zasedeni < self.nadstropij * self.prostorov:
            print("zasedenih je:", self.nadstropij * self.prostorov - self.zasedeni)


class Avtomobil(Autohisa):
    def __init__(self):
        Autohisa.__init__(self)

    def zagori(self):
        self.alarm = True
        print("wiu wiu")
        self.stanje -= 30
        print("stanje je {}".format(self.stanje))

    def izhod(self):
        self.vozila.remove(self)

    def vhod(self):
        self.vozila.append(Avtomobil())
        self.parkirnina = 5


class Tovornjak(Avtomobil):
    def __init__(self):
        super().__init__()


class Gasilci(Tovornjak):
    def __init__(self):
        self.parkirnina = 0

    def pogasi(self):
        self.alarm = False
        print("alarm je ugasnjen")

    def vhod(self):
        self.parkirnina = 10


class Ropar(Autohisa):
    def __init__(self):
        Autohisa.__init__(self)
        self.chance = randint(30, 100)
        self.vozilo = None

    def ukradi(self, vozilo):
        if self.chance > 50:
            if self.varovanje < self.chance:
                self.zasedeni -= 1
                self.vozilo = vozilo
                self.vozila.remove(vozilo)


class Policaj(Ropar):
    def __init__(self):
        self.pridnost = randint(0, 50)
        self.varovanje += self.pridnost

    def ujemi(self):
        if self.pridnost == 0:
            print("rrr")
        if self.chance < self.pridnost:
            print("ujel sem roparja")  ############kako zbrisati roparja


a = Autohisa()
clio = Avtomobil()
clio.vhod()
mercedes = Tovornjak()
mercedes.vhod()
mercedes.zagori()
polanci = Gasilci()
polanci.pogasi()
a.izpis_vozil()
nigger = Ropar()

nigger.ukradi(clio)
a.izpis_vozil()
a.izpis_parkirnine(clio)
