# domaca naloga calculator
class Oseba:
    def __init__(self, ime, spol):
        self.ime = ime
        self.spol = spol

    # ben=Oseba("Benjamin","M")
    def pozdravi(self):
        print("jaz sem {} in sem {}".format((self.ime), self.naziv()))

    # ben=Oseba("Benjamin","M")
    # ben.pozdravi()
    def naziv(self):
        return "gospod" if self.spol == "M"else "gospa"


class Student(Oseba):  # oseba je tle dedovanje
    def __init__(self, ime, spol):
        super().__init__(ime, spol)
        self.ocene = {}

    """def pozdravi(self):#ta pozdrav povozi metodo iz osebe
        super().pozdravi()#vrne objekt prejšnega razreda
        print("in sem studentka")"""

    def naziv(self):
        return "studentka" if self.spol == "Ž" else "študent"

    def prejmi_oceno(self, predmet, ocena):
        self.ocene[predmet] = ocena


cilka = Student("Cilka", "Ž")
"""cilka.prejmi_oceno("mat",6)
cilka.pozdravi()
print(cilka.ocene)"""


class Ucitelj(Oseba):
    def __init__(self, ime, spol, naziv):
        super().__init__(ime, spol)
        self.habilitacija = naziv

    def naziv(self):
        return self.habilitacija


dani = Ucitelj("Dani", "M", "docent")


# dani.pozdravi()

class Snazilka(Oseba):
    def __init__(self, ime):
        super().__init__(ime, "Ž")


fata = Snazilka("Fata")


class Hisnik(Oseba):
    def __init__(self, ime):
        super().__init__(ime, "M")


peter = Hisnik("Peter")

eva = Student("eva", "Ž")
franc = Student("franc", "M")
studenti = [eva, cilka, franc, dani, fata, peter]
for student in studenti:
    student.pozdravi()
    # student.prejmi_oceno("p1",7)#ne dela ker dani ni student-nima atributa prejmi oceno
