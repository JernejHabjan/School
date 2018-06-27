__author__ = 'Roglan'
"""program omogoča vnos podatkov dijakov
ko maš vse vnesene, naključno zgenerira končno oceno dijaka pri 5ih predmetih
izpiše koliko dijakovo povprečje odstopa od povprečnega povprečja ocen drugih dijakov
"""
redovalnica = []
dijak = []
skupna_tabela = []
max_tabela = []
index = 0
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
    for ime in redovalnica:
        skupna_tabela = []
        if iskano_ime == ime:
            skupna_tabela.append(ime)
            while 1:
                nova_ocena = input("Dobil/a je oceno :\n")
                if nova_ocena == "":
                    break
                predmet = input("katerem predmetu je dobil oceno? \n")
                if predmet == "slovenscina":
                    skupna_tabela.append(nova_ocena)

                elif predmet == "anglescina":
                    skupna_tabela.append(nova_ocena)
                elif predmet == "matematika":
                    skupna_tabela.append(nova_ocena)
            max_tabela.append(skupna_tabela)

print(max_tabela)

"""for i,ime in enumerate(redovalnica):
    if(ime == iskano_ime):
        index = i

insert(index, ime)"""
