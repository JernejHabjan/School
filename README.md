# Seminarska naloga pri podatkovnem rudarjenju  
**Matic Vrtačnik  
Jernej Habjan**  

## 2. Vmesno poročilo o opravljenem delu
Delo sva si razdelila na 2 večji področji.



### 2.1 Prikazovalnik na spletu (index.html, style.css, script.js, query.js):
Prvo je prikazovalnik na spletu. Na njem lahko vnesemo točke, kjer hočemo potovati in tako dobimo razdalje ter čas potovanja po zaporednih lokacijah. Na voljo so nam časi in razdalje za hojo in vožnjo z avtomobilom. Za vsako posamezno točko lahko prikažemo prebivališča, ki ustrezajo našim preferencam, ki si jih lahko izberemo iz menija, in tako prikažemo omejene rezultate.

Trenutne preference:
```
Allow pets (Dovolimo živali pri prenošču)
Require heating (Prenočišče ima gretje)
Require house (Prenočišče je hiša)
Require breakfast (Gost ponuja zajtrk)
Require family friendly (Prenočišče je prijazno za družino)
```

[[https://github.com/darkneess10/PR17_MV_JH/blob/master/img/distribution.png|alt=distribution]]

Ob spremembi preferenc ali izbiri točke zanimanja, se prikaže graf distribucije ocen vseh prebivališč 
na seznamu. Ta je načeloma podobna normalni. Vse informacije o prebivališču predhodno pretvorimo v pravilno obliko za končno uporabo (branje in prikaz na spletni strani). Več o pretvorbi v točki 2.2. S pomočjo spletnega strežnika kličemo preko skripte python/query.php skripto za branje podatkov iz najbližjega mesta (datoteka 04_query_entries.py), ki so shranjeni v direktoriju "src/mesto/" (v tej fazi imamo za testiranje samo eno mesto). Od tu dobimo atribute potrebne za
prikaz informacij o prenočišču(slike, opis, koordinate, tip hiše, score), iz njih pa v skripti pogledamo tudi, če prebivališče ustreza našim preferencam. Vnos prikažemo v HTML tabeli.

[[https://github.com/darkneess10/PR17_MV_JH/blob/master/img/groups.png|alt=groups]]

Od tu vidimo približne razdalje in čas potovanja od središča izbranega mesta do določenega Airbnb prebivališča. Na zemljevidu pa se pojavijo oznake vsakega prenočišča, na katere lahko kliknemo za več informacij. Označba se obarva zeleno, če je prebivališče na tej lokaciji pridobilo točke kvalitete (score). V nasprotnem primeru, če je te izgubilo, potem se ta obarva z rdečo barvo. Tako ponekod lahko opazimo skupine prenočišč, ki so se izboljšale.

[[https://github.com/darkneess10/PR17_MV_JH/blob/master/img/score_over_time.png|alt=score_over_time]]

S klikom na gumb "Show" prikažemo kratek opis in sliko posameznega prebivališča. Pokaže se nam tudi graf, ki prikazuje spremembo točk kvalitete skozi čas. Po tem lahko tudi vidimo ali so se te drastično zvišale ali spustile skozi čas. Načeloma te ostajajo v nekem majhnem območju, so pa tudi izjeme.


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