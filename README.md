# Seminarska naloga pri podatkovnem rudarjenju  - Priporočilni sistem za izbiro prenočišč
**Matic Vrtačnik  
Jernej Habjan**  


## 1. Opis problema in podatkov	
Izbrani imava dve množici podatkov in sicer Airbnb večdnevna izbira prenočišč in prvič registrirana vozila po mesecih.
Prva množica podatkov nama je ljubša kot duga in sva si domislila uporabno aplikacijo ki bi jo lahko razvila s pomočjo teh
podatkov, ampak ni v skladu z navodili, saj na spletni strani Kaggle.com ne spada pod rubriko "competition".
	
Spodaj sta opisa idej obeh množic podatkov ampak delala bova samo z eno.

### 1.1 Airbnb večdnevna izbira prenočišč:
Ko potujemo za dlje časa se moramo vnaprej pozanimati kje so bencinske črpalke, kje so trgovine in znamenitosti, ki nas zanimajo. Prav tako potrebujemo rezervirati prenočišča. Ampak kako narediti to?
		
Ena izmed najbolj znanih portalov za rezervacijo je Airbnb, kjer lahko rezerviramo posamezno prenočišče v hotelu, apartmaju za eno ali več noči. Ta način rezervacije je dober če se nahajamo približno na istem mestu. Kaj pa če želimo kolesariti čez Zdužene države Amerike in hočemo prenočiti udobno več noči zapored pri različnih ljudeh? Kako bomo našli najbolj primerna prenočišča za vsako vas ali mesto kjer se bomo ustavili? To bi morali početi za vsakega posebej, kar bi nam vzelo dolgo časa.
		
S kolegom sva si zamislila pomočnika, ki bi pomagal pri tej izbiri.

Ko bi izbirali pot na zemljevidu, ki bi trajala več dni da pridemo na cilj bi se na njem pokazala prenočišča iz Airbnb in povezave na to stran za rezervacijo. Rezervacije bi lahko uporabnik prilagodil sam glede na to koliko luksuzna prenočišča potrebuje in koliko hoče za potovanje največ plačati.

Podatki:
Na kaggle.com sva videla nabor podatkov z airbnb in sicer iz mesta Seattle: https://www.kaggle.com/airbnb/seattle
Podatki so v csv datotekah z manjkajočimi vrednostmi. Vsebujejo tudi opise stanovanja, in povezave do slik, ki jih 
ne bova uporabljala.

S spletne strani http://insideairbnb.com/get-the-data.html bi razširila podatke še za več mest ZDA: 
```
Asheville, North Carolina  
Austin, Texas  
Boston, Massachusetts  
Chicago, Illinois  
Denver, Colorado  
Los Angeles, California  
Nashville, Tennessee  
New Orleans, Louisiana  
New York City, New York  
Oakland, California   
Portland, Oregon  
San Diego, California  
San Francisco, California  
Santa Cruz County, California  
Seattle, Washington
Washington, D.C., District of Columbia  
```
		
Tako da bi popotnik resnično lahko potoval po velikem delu države in imel izpis vseh prenočišč na poti.
		


Delo sva si razdelila na 2 večji področji.


### 2.1 Obdelava datotek:
Prvo področje pa je priprava datotek za obdelavo v pythonu. 

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

!!!!!!!!!TODO
Kako se izračuna agregirana ocena - compound score:


compound = normalize(sum_s), kjer je normalize:

def normalize(score, alpha=15):
    """
    Normalize the score to be between -1 and 1 using an alpha that
    approximates the max expected value
    """
    norm_score = score/math.sqrt((score*score) + alpha)
    return norm_score

Kaj je alpha:

Je hyper-parameter  ????????????

Kaj pa je sum_s
As for the sum_s, it is a sum of the sentiment arguments ???
Sentiment arguments are computed with polarity_scores function
Looking at the polarity_scores function, what it's doing is to iterate through the whole SentiText
 lexicon and checks with the rule-based sentiment_valence() function to assign the valence score to the sentiment


Compound score ni izpeljanka iz [pos, neg, neu] vektorja


!!!!



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

```
"cancellation_policy":         0.1,
"description_score":           0.4,
"host_about":                  0.3, 
"host_acceptance_rate":        0.7,
"host_identity_verified":      0.1,
"host_response_time":          0.2,
"host_since":                  0.1,
"host_total_listings_count":   0.5,
"host_verifications":          0.6,
"is_location_exact":           0.3,
"neighborhood_overview":       1.5,
"notes":                       0.5,
"number_of_reviews":           1.5,
"review_scores_accuracy":      0.9,
"review_scores_checkin":       0.9,
"review_scores_cleanliness":   0.9,
"review_scores_communication": 0.7,
"review_scores_location":      0.7,
"review_scores_rating":        1.2,
"review_scores_value":         1.2,
"space":                       0.4,
"summary":                     0.5,
"transit":                     0.8,
"comment_comp_score":          2.0         
```



!!!!!!!!!!!!!! TODO

normaliziral sem SCORE, comments_scores_5, avg_comment_score tako da sem poiskal maksimalno in minimalno
oceno v tem mestu in to oceno normaliziral glede na min in max TODOOOOO FIXI


!!!!!!!!!!!!!!


### 2.2 Prikazovalnik na spletu (direktorij web):


#### Načrtovanje izleta
Sprednji del celotne aplikacije je spletna stran, ki nam omogoča načrtovanje izleta. Na tej lahko dodajamo mesta ali naslove, skozi katere želimo potovati. Ker je aplikacija mišljena za večdnevna potovanja, te najverjetneje geografsko ne bodo preveč skupaj. Zaradi omejenosti podatkov na Združene Države Amerike, je aplikacija namenjeva potovanju predvsem po zvezdnih državah. Ob urejanju poti lahko menjamo vrsti red postajališč in si tako po želji uredimo pot. Na prikazovalnik zemljevida se nam bo s pomočjo Googlove navigacije pokazala pot po vseh izbranih točkah. Na voljo sta nam načina potovanja z avtomobilom ali peš (pri potovanju s kolesom navigacija ne deluje povsod, zato ta način izpustimo). Ob urejanju poti se nam poleg izbrane lokacije pokaže tudi razdalja in sam čas potovanja od prejšnje do te lokacije.  
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/gui_route.png "Prikaz poti")  


#### Pregled in filtriranje prenočišč
Ob pritisku na gumb "Show" izbrani točki izleta se nam pokažejo vsa Airbnb prenočišča v njeni bližini, ki ustrezajo našim trenutno izbranim preferencam, urejena po oceni prebivališča (opisano zgoraj v 2.1 obdelava datotek - med 0 in 10). Če podatkov za izbrano mesto slučajno nimamo, se bodo prikazala prenočišča, ki so geografsko najbližje željeni potovalni destinaciji (in seveda ustrezajo našim preferencam). Nad seznam prebivališč narišemo še porazdelitev ocen, ki je načeloma normalna.  
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/distribution.png "Distribution")  

Na zemljevid ob tem pogledu označimo vsa prenočišča z barvo odvisno od zadnje spremembe ocene. Torej, če ocena trenutno pada, je oznaka rdeče, če narašča, zelena, v primeru pa, da se ocena več časa ni spremenila, pa modro. Tukaj morda lahko opazimo majhne skupine, kjer se je ocena ali poslabšala ali pa poboljšala.  
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/groups.png "Groups")  

Med pogledom seznama lahko preference spremenimo in tako se nam ob vsaki spremembi seznam osveži. Ker je prenočišč veliko je smiselno imeti vklopljeno preferenco "Show top 10", ki pokaže samo 10 najboljših prenočišč v okolici in nam tako zelo zoža izbiro. Na voljo so nam pa tudi preference, ki nam lahko olajšajo ali pocenijo potovanje.  

##### Seznam vseh preferenc:
```
Show top 10 (Prikaže samo najboljših 10 prenočišč, ki ustrezajo preferencam)
Allow pets (Dovolimo živali pri prenošču)
Require heating (Prenočišče ima gretje)
Require house (Prenočišče je hiša)
Require breakfast (Gost ponuja zajtrk)
Require family friendly (Prenočišče je prijazno za družino)
Require cable TV (Prenočišče omogoča gledanje kabeljske televizije)
Require wireless internet (Prenočišče omogoča povezavo na brezžični internet)
Require air conditioning (Prenočišče omogoča klimatsko napravo)
Require free parking (Prenočišče omogoča zastonj parkiranje)
Require kitchen (Prenočišče omogoča uporabo kuhinje)
Min price (Spodnja meja cene na noč)
Max price (Zgornja meja cene na noč)
```

Tukaj največ prebivališč odstranita preferenci "Require breakfast" in "Require cable TV", saj večinonoma ne ponujajo že pripravljenega zajtrka, ampak kuhinjo, kjer si ga lahko skuhamo sami. Kabeljske televizije pa tudi nimajo vsi, ampak je po večini prenočišč na voljo vsaj navadna televizija.
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/gui_top10_listings.png "Filtriran seznam prenočišč")  


#### Pregled opisa prenočišča
Na seznamu prebivališč lahko kliknemo na gumb "Show", ki prikaže dodatne informacije o prenočišču. S klikom se nam na zemljevidu prikaže pot od središča mesta do izbranega prenočišča, na desni strani pa vidimo povzetek iz prejšnje strani (ime, oceno, ceno/noč, razdaljo od centra mesta, čas potovanje iz centra mesta), pod njim pa graf ocene skozi čas (5 različnih datumov). Tukaj načeloma opazimo, da se ocene skozi čas niso preveč spreminjale, ampak ponekod pa so tudi se. Iz redkejših primerov spremb ocene lahko sklepamo, da ocena morda ni stabilna. Opazimo lahko tudi trend, ali ocena trenutko pada ali narašča (označeno s posebno barvo na zemljevidu - opisano pod pregledom in filtriranjem zgoraj).  
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/score_over_time.png "Score over time")

Pod grafom pa vidimo še sliko, ki jo je priložil ponudnik in pa kratek opis prenočišča. Če je to slikano od zunaj, lahko s pomočjo zemljevidov in funkcionalnosti Googlovega cestnega pogleda pogledamo kako se izgled prenočišča primerja s sliko, a je ta mogoče preveč zastarel. Če pa se je stavba v parih letih precej spremenila, pa lahko vidimo vsaj približno okolico, kjer bi se mogoče odločili prenočiti.  
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/gui_description.png "Opis prenočišča")  


#### Delovanje aplikacije
[Načrtovanje poti izleta (GIF)](https://github.com/darkneess10/PR17_MV_JH/blob/master/gif/planning_route.gif)  
[Urejanje preferenc (GIF)](https://github.com/darkneess10/PR17_MV_JH/blob/master/gif/preferences.gif)  
[Pregled opisa prenočišča (GIF)](https://github.com/darkneess10/PR17_MV_JH/blob/master/gif/description.gif)  
[Uporaba aplikacije (hitra demonstracija) (GIF)](https://github.com/darkneess10/PR17_MV_JH/blob/master/gif/application_usage.gif)  
[Google Street View primerjava s sliko (GIF)](https://github.com/darkneess10/PR17_MV_JH/blob/master/gif/street_view.gif)  


#### Implementacija
Uporabimo spletne tehnologije Javascripta in PHPja (direktorij "web"). Za dodajanje točk izleta uporabimo Googlov API za določanje koordinat na zemljevidu in risanje samega zemljevida. Za risanje poti pa njihovo navigacijsko strežbo. Ob pritisku gumba za prikaz seznama prenočišč najprej po geografski legi izračunamo, katero mesto izmed tistih, ki so na voljo, je najbližje izbrani lokaciji in nato pošljemo v PHP skripto podatke o preferencah, kjer preberemo v direktoriju "src/City_Data_Attributes/mesto/" ("mesto" v direktoriju predstavlja ime najbližjega mesta, ki ga imamo med podatki) atribute potrebne za lociranje posameznega prenočišča na zemljevidu in pa vse podatke, ki jih pozneje pokažemo v aplikaciji, vključno z naslovoma majhne in velike slike prenočišča (datoteka "python/query.php"). 


## 3. Zakjučno poročilo o opravljenem delu


######### TODO GRAFI GREJO TLE

__SCORE_DISTRIB_GROUP_BY_ROOM_TYPE --- density????????????????????????

__score_price_color-neighbourhood_overview ----- manjši kot je score in price,,, slabše je ocenjen neigbourhood


__simple_houses --- distribucija prebivališč- --- simple!!!








