# predavanje 20.1.2016

# SESTAVLJANJE UPORABNIŠKIH VMESNIKOV"

# creating gumb in pyqt


# qt designer - installed with PYQT

"""
from PyQt4 import QtGui,uic
app=QtGui.QApplication([])

dlg = uic.loadUi("mojfile,ui")
dlg.show()
#dlg predstavlja dialog... ima dlg.mojalabelaIZRACUN..... lg.mojIZRACUN.txt().... dlg.mojIZRACUN.setText("lololol")... dlg.mojIZRACUN.setText("kroglo bo neslo {} metrov".format(izracun))
def f():
    v=float(dlg.leHitrost.text())
    ###še naprej koda funkcija
    print("lolo")
dlg.mojIZRACUN.clicked.connect(f)
app.exec()#toje risar.stoj()
"""

#########################################################################
"""import math
from PyQt4 import QtGui, uic

class Top:
    def __init__(self):
        self.app = QtGui.QApplication([])
        self.dlg = uic.loadUi("top.ui")
        self.dlg.btIzracun.clicked.connect(self.izracun)
        dlg.show()
    def izracun(self):
        hitrost = float(dlg.leHitrost.text())
        kot = float(dlg.leKot.text())
        razdalja = hitrost ** 2 * math.sin(2 * math.radians(kot)) / 9.81
        dlg.lbIzracun.setText("Krogla bo preletela {:.2f} metrov".format(razdalja))

#not finished
app.exec()
"""

##############################################################################
# s=[2,45]
# print(float(s[0].replace(",",".")))
# replace na bytih ne dela
# s.decode("ascii")
# s=s.split("\n"")

"""try:
      newline bla bla

    except:
        llolol


import urllib.request

tecaji = {}
socket = urllib.request.urlopen("http://www.nlb.si/?a=tecajnica&type=individuals&format=txt")
for line in socket:
    line = line.decode("ascii")
    if line.startswith("001"):
        podatki = line.split()
        valuta = podatki[5]
        tecaj = float(podatki[6].replace(",", "."))
        tecaji[valuta] = tecaj


#combobox je dropdown menu
#label je za pisat noter


#če naredimo list(slovar) vrne v list ključe od slovarja in ne vrednosti oz pare
"""
from PyQt4 import QtCore, QtGui, uic


class Odstevalnik:
    def __init__(self):
        self.sound = QtGui.QSound("ringing.wav")  # nimam fila noter

        self.timer = QtCore.QTimer()
        self.timer.timeout.connect(self.odstejSekundo)

        self.dlg = uic.loadUi("odstevalnik.ui")
        self.dlg.btZacni.clicked.connect(self.zacni)
        self.dlg.btZvok.clicked.connect(self.izberiZvok)
        self.dlg.show()

    def odstejSekundo(self):
        sec = self.dlg.sbSekunde.value()
        if sec > 0:
            self.dlg.sbSekunde.setValue(sec - 1)
        else:
            min = self.dlg.sbMinute.value()
            if min == 0:
                self.timer.stop()
                self.sound.play()
            else:
                self.dlg.sbMinute.setValue(min - 1)
                self.dlg.sbSekunde.setValue(59)

    def izberiZvok(self):
        fn = QtGui.QFileDialog.getOpenFileName(
            None, "Izberi zvocno datoteko", ".",
            "Zvocne datoteke (*.wav *.mp3)")
        if fn:
            self.sound = QtGui.QSound(fn)

    def zacni(self):
        self.timer.start(1000)


app = QtGui.QApplication([])
odstevalnik = Odstevalnik()
app.exec()
# t.setInterval(1000)-vsakih 1000ms sproži signal timeout.
