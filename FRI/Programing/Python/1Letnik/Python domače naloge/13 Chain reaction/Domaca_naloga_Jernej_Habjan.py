from math import sqrt
from random import randint

from Modules import risar


def chainReaction(n, time):
    krogci = []
    premik_po_x = []
    premik_po_y = []
    obroba = []
    risar.barvaOzadja(risar.barva(16, 36, 27))  # ozadje

    for element in range(n):
        kdiameter = 10
        color = risar.barva(randint(100, 255), randint(100, 255), randint(100, 255))
        sirina = 5
        krogec = risar.krog(randint(kdiameter + sirina, risar.maxX - (kdiameter + sirina)),
                            randint(kdiameter + sirina, risar.maxY - (kdiameter + sirina)), kdiameter - sirina / 2,
                            color, sirina)
        krogci.append(krogec)
        premik_po_x.append(randint(-5, 5))
        premik_po_y.append(randint(-5, 5))
        obroba.append(kdiameter + sirina)

    for i in range(time):
        miska_x, miska_y = risar.miska
        if risar.klik != True:
            dia = 30
            moj_krogec = risar.krog(miska_x, miska_y, dia, risar.barva(255, 255, 100), 5)
        for i in range(len(krogci)):
            krogec = krogci[i]
            if risar.klik == True:

                narisan_x = miska_x
                narisan_y = miska_y
                for j in range(len(krogci)):
                    krogec_nov = krogci[j]
                    d = sqrt((krogec_nov.x() - narisan_x) ** 2 + (krogec_nov.y() - narisan_y) ** 2)
                    if d <= obroba[j] + dia:
                        krogec_nov.setRect(-15, -15, 30, 30)
                        a = risar.QMessageBox.information(None, "Chain reaction",
                                                          "razpocil se je krogec - konec moje igre")
                        risar.stoj()

            krogec.setPos(krogec.x() + premik_po_x[i], krogec.y() + premik_po_y[i])
            if not (obroba[i] < krogec.x() < risar.maxX - obroba[i]):
                premik_po_x[i] = -premik_po_x[i]
            if not (obroba[i] < krogec.y() < risar.maxY - obroba[i]):
                premik_po_y[i] = -premik_po_y[i]

        risar.cakaj(0.02)
        if risar.klik != True:
            moj_krogec.hide()

    risar.stoj()


print(chainReaction(30, 1000))
