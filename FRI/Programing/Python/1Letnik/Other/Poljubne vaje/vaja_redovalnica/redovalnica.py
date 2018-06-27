__author__ = 'Roglan'
import random
from random import *

"""program omogoča vnos podatkov dijakov
ko maš vse vnesene, naključno zgenerira končno oceno dijaka pri 5ih predmetih
izpiše koliko dijakovo povprečje odstopa od povprečnega povprečja ocen drugih dijakov
"""
redovalnica = []
dijak = []
skupna_tabela = []
max_tabela = []
index = 0
vsi_predmeti = ["slovenscina", "matematika", "anglescina"]
print("Če hočete preklicati kateri koli vnos, pustite prazno polje.")
st_dijakov = 0
while 1:
    oseba = str(input("vpišite ime dijaka: \n"))
    if (oseba == ""):
        break
    else:
        st_dijakov += 1
        redovalnica.append(oseba)

while 1:
    skupna_tabela = []
    iskano_ime = input("Vpisite ime dijaka, ki je dobil oceno:\n")
    if iskano_ime == "":
        break
    slovenscina = []
    anglescina = []
    matematika = []
    for ime in redovalnica:
        counter = 0
        skupna_tabela = []
        if iskano_ime == ime:
            skupna_tabela.append(ime)
            while counter < 5:
                print("Dobil/a je oceno :\n")
                nova_ocena = randint(1, 5)
                print(nova_ocena)
                counter += 1
                print("pri katerem predmetu je dobil oceno?")
                predmet = random.choice(vsi_predmeti)
                if predmet == "slovenscina":
                    print("pri slovenscini")
                    slovenscina.append(nova_ocena)
                elif predmet == "anglescina":
                    print("pri anglescini")
                    anglescina.append(nova_ocena)
                elif predmet == "matematika":
                    print("pri matematiki")
                    matematika.append(nova_ocena)
            skupna_tabela.append(slovenscina)
            skupna_tabela.append(anglescina)
            skupna_tabela.append(matematika)
        max_tabela.append(skupna_tabela)

print(max_tabela)

"""for i,ime in enumerate(redovalnica):
    if(ime == iskano_ime):
        index = i

insert(index, ime)"""
