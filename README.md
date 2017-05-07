# Seminarska naloga pri podatkovnem rudarjenju  
**Matic Vrtačnik  
Jernej Habjan**  

## 2. Vmesno poročilo o opravljenem delu
Delo sva si razdelila na 2 večji področji.



### 2.1 Prikazovalnik na spletu:
Prvo je prikazovalnik na spletu. Na njem lahko vnesemo 
točke, kjer hočemo potovati. Skozi mesta ki bomo potovali se bodo pa pokazala mesta, kjer lahko najamemo
prebivališče. Lahko obkljukamo preference, ki omejijo rezultate iskanja prebivališča.
Lahko vnesemo tudi okvirno ceno.

Trenutne perference:
```
Allow pets
Require shower
Allow camping locations
Require breakfast
Require larger room
```


### 2.2 Obdelava datotek:
Drugo področje pa je priprava datotek za obdelavo v pythonu. 

Atribute je bilo potrebno generalizirati, da so bili skozi vsa mesta enaki in niso manjkali. Potrebno je bilo tudi 
zbrisati vse nepotrebne atribute, ki pri delu ne koristijo.

Ostale atribute je bilo potrebno reformatirati da so primerni za kalkulacijo. Nekatere tekstovne atribute se je
spremenilo v mero, ki predstavlja sentimentalno vsebino tega atributa (npr. atribut "transit"). Tako atributi
zasedejo veliko manj prostora, prav tako se pa da z njimi računati končno oceno prebivališča.

Prav tako je bilo potrebno prevesti komentarje in opise, ki so bili postavljeni v jeziku ki ni angleščina, saj
mera, ki vrača točke sentimentalne vsebine, zna prepoznavati samo angleška besedila. Za to je uporabljena 
knjižnica goslate.

Za sentimentalno analizo je uporablen sentimenal analyzer VADER iz nltk knjižnice. Ko analiziramo besedilo, vrne
naslednje mere:
```
neg - procent negativno označenih besed
neu - procent nevtralno označenih besed
pos - procent pozitivno označenih besed
compound - agregirana ocena
```
Compound ocena je normalizirana ocena vsot in te vsote so vsote hevristik in sentimentalne intenzitete.

Dodanih je nekaj izračunanih atributov:
```
avg_comment_score -mediana compound ocene vseh komentarjev za to prebivališče
comments_scores_5 -5 datumov in median ocen enakomerno razdeljeni med 
                   komentarjemi. 
                  -Za prikaz grafa naraščanja ali padanja ocene
comments_scores_though_time - ocena padanja ali naraščanja ocene prebivališča
                              skozi čas
```
Ocene so izračunane iz več atributov, pri katerih je vsak atribut utežen z neko utežjo, ki je ročno določena.
To je stvar jo je treba še izpopolniti, saj je sedaj mogoče dana prevelika teža napačnim atributom,
mogoče so preveč izpostavljeni novejši komentarji ipd.

Potrebno je dodati še več izračunanih atributov, atribute spremeniti ter ponovno preveriti sentimentalno analizo.