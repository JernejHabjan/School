# Priporočilni sistem za izbiro prenočišč
**Matic Vrtačnik  
Jernej Habjan**  

### 1.1 Airbnb večdnevna izbira prenočišč:
Ko potujemo za dlje časa se moramo vnaprej pozanimati kje so bencinske črpalke, kje so trgovine in znamenitosti, ki nas zanimajo. Prav tako potrebujemo rezervirati prenočišča. Ampak kako narediti to?
		
Ena izmed najbolj znanih portalov za rezervacijo je Airbnb, kjer lahko rezerviramo posamezno prenočišče v hotelu, apartmaju za eno ali več noči. Ta način rezervacije je dober če se nahajamo približno na istem mestu. Kaj pa če želimo kolesariti čez Zdužene države Amerike in hočemo prenočiti udobno več noči zapored pri različnih ljudeh? Kako bomo našli najbolj primerna prenočišča za vsako vas ali mesto kjer se bomo ustavili? To bi morali početi za vsakega posebej, kar bi nam vzelo dolgo časa.
		
S kolegom sva si zamislila pomočnika, ki bi pomagal pri tej izbiri.

S pomočnikom lahko izberemo pot na zemljevidu, ki mogoče traja več dni da pridemo na cilj. V nekaterih mestih na tej poti
lahko izberemo prenočišča z Airbnb ki nam ugajajo. Ta prenočišča so ocenjena z neko oceno, ki jo je izračunal sistem in nas vodijo, katera prenočišča so "boljša" od drugih.
Boljša v smislu višje ocene na podlagi atributov in uteži.


### 1.2 Podatki:
Podatke sva pridobila s spletne strani [http://insideairbnb.com/get-the-data.html] kjer sva izbrala mesta v ZDA:
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
Vsako mesto pa je imelo naslednje datoteke:
```
calendar.csv
listings.csv
neighbourhoods.csv
neighbourhoods.geojson
reviews.csv
```
Uporabila sva datoteki reviews.csv, ki vsebujejo vse komentarje o vseh prebivališčih v posameznem mestu
in datoteko listings.csv, ki vsebuje skoraj vse atribute, ki so povezani s prebivališčem. Datoteki o okolici nisva 
uporabila, saj niso imele veliko koristnih informacij, datoteko calendar.csv bi pa lahko uporabila za točno določitev 
prebivališča na določen dan, ampak na koncu nisva.

### 2.1 Obdelava datotek:
Datoteki reviews.csv in listings.csv sva predobdelala v pythonu.
Najpomembnejši izhodni atribut te predobdelave je atribut SCORE, ki pove končno oceno prebivališča v določenem mestu,
ki je izračunan z vrsto atributov in uteži.

Atribute je bilo potrebno generalizirati, da so bili skozi vsa mesta enaki in niso manjkali. Prav tako je bilo potrebno nekatere zbrisati, ki pri delu niso koristili.

Ostale atribute je bilo potrebno reformatirati da so primerni za kalkulacijo. Nekatere atribute je bilo potrebno
spreminiti v številske atribute, da je bil potem iz njih možen izračun končne ocene SCORE

#### 2.1.1 Sentimentalna analiza VADER

Celotno datoteko reviews.csv sva spremenila v mero, ki predstavlja sentimentalno vsebino tega atributa.
Prav tako sva spremenila v mero ostale smislene tekstovne atribute kot so "transit", "summary" ipd.
Tako atributi zasedejo veliko manj prostora, prav tako se pa da z njimi računati končno oceno prebivališča.

Računanje sentimentalne vsebine:

VADER-Sentiment-Analysis iz nltk knjižnice
VADER (Valence Aware Dictionary and sEntiment Reasoner) je leksikon in orodje za simentalno analizo ki temelji na pravilih, ki je posebej pripravljen za razpoznavanje čustev, izraženih v družabnih medijih



Ko analiziramo besedilo, vrne naslednje mere:
```
neg - procent negativno označenih besed
neu - procent nevtralno označenih besed
pos - procent pozitivno označenih besed
compound - agregirana ocena
```
Compound ocena je normalizirana ocena vsot in te vsote so vsote hevristik in sentimentalne intenzitete.

Podrobnejši opis izračuna agregirane ocene - compound score:
```
compound = normalize(sum_s), kjer je normalize:

def normalize(score, alpha=15):
    norm_score = score/math.sqrt((score*score) + alpha)
    return norm_score
```  
sum_s je vsota sentimentnih argumentov, izračunanih s funkcijo ki razpozna čustva na podlagi pravil.

Compound score ni izpeljanka iz [pos, neg, neu] vektorja

#### 2.1.1 Izračunani atributi
Dodanih je nekaj izračunanih atributov:
```
avg_comment_score
comments_scores_5
comments_scores_though_time
```

avg_comment_score - mediana compound ocene vseh komentarjev za to prebivališče

comments_scores_5 - 5 datumov in median ocen enakomerno razdeljeni med komentarjemi. 
 Za prikaz grafa naraščanja ali padanja ocene
 
comments_scores_though_time - ocena padanja ali naraščanja ocene prebivališča skozi čas


Ocene so izračunane iz več atributov, pri katerih je vsak atribut utežen z neko utežjo, ki je ročno določena, saj
nimava končnega atributa, nad katerim bi lahko zgradila model za boljšo določitev uteži.

```
"cancellation_policy":         0.1 - strogost prekinitve
"description_score":           0.4 - [C] opis
"host_about":                  0.3 - [C] opis oddajalca
"host_acceptance_rate":        0.7 - sprejemanje
"host_identity_verified":      0.1 - vertifikacija oddajatelja
"host_response_time":          0.2 - hitrost odpisa
"host_since":                  0.1 - pričetek oddajanja
"host_total_listings_count":   0.5 - število vseh oddaj
"host_verifications":          0.6 - procent vertifikacij
"is_location_exact":           0.3 - natančnost okolice
"neighborhood_overview":       1.5 - [C] okolica
"notes":                       0.5 - [C] zapiski
"number_of_reviews":           1.5 - število kritik
"review_scores_accuracy":      0.9 - ocena natančnosti
"review_scores_checkin":       0.9 - ocena prijave
"review_scores_cleanliness":   0.9 - ocena čistoče
"review_scores_communication": 0.7 - ocena komunikacije
"review_scores_location":      0.7 - ocena lokacije
"review_scores_rating":        1.2 - ocena kritike
"review_scores_value":         1.2 - vrednost kritike
"space":                       0.4 - [C] opis prostora
"summary":                     0.5 - [C] povzetek
"transit":                     0.8 - [C] prevoz
"comment_comp_score":          2.0 - [C] povprečna sentimentalna vsebina komentarjev    

[C] - compound score - izračunana s Sentimental analyzer VADER
```

Atribute SCORE, comments_scores_5, avg_comment_score sva morala tudi normalizirati.

Obdelava vseh datotek po vseh mestih je trajala približno 1 dan da se je python skripta končala, čeprav sem zagnal
na več ločenih nitih. Trajalo je toliko časa, ker sentimentalna analiza dolgo traja in zaradi števila zapisov (naprimer 
mesto New York ima več kot 1.2 milijona komentarjev)

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

Končen rezultat je funkcionalna aplikacija, kjer lahko stranka hitro določi prebivališča na večdnevni poti. Ta
prebivališča pa so ocenjena z neko predobdelano oceno na podlagi atributov in komentarjev.

\
\
Pregled atributov summary in transit:\
S sentimentalno analizo sta bila izračunana tudi atributa summary in transit, vendar ima transit zelo slabe ocene v primerjavi s summary in ostalimi tekstovnimi atributi.
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/summary_transit_neg.png "Pregled atributov summary in transit")  
Prevoz je stvar na katero lastniki nimajo vpliva, zato je ne morejo tekstovno olepšati, je pa zelo pomembna pri najemanju stanovanja.



Vplivi na končno oceno:\
Na spodnjih dveh grafih se dobro vidi, kako močno konča ocena razdeli slabše okolice od boljših in manj zaupljive oddajalce od bolj zauplivih.
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/SCORE_vplivi.png "Vpliv okolice in zaupljivost oddajalca do končne ocene")  

### 3.1 Distribucije

Distribucija ocen komentarjev:
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/avg_comment_score_distribution.png "Distribucija ocen komentarjev")  
Komentarji so po večini pozitivni, z manjšino pod oceno 4, kar je pričakovano, saj si stranke dobro pregledajo prenočišče, preden ga vzamejo in že v naprej
približno vedo, kaj pričakovati.


\
Distribucija ocen komentarjev skozi čas:
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/comments_scores_through_time_distribution.png "Distribucija ocen komentarjev skozi čas")  
Pri veliki večini prebivališč se ocena komentarjev ne spreminja, pri nekaj pa raste ali pada, zato moramo biti pozorni prav na te.

#### 3.2 Zanimive ugotovitve
Zgleda lahko prenočimo kar v jami ali v hišici na drevesu:\
Distribucija tipa prenočišč
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/property_type.png "Frekvence tipa prenočišč v San Francisco")  

\
ScatterPlot cen varnostnih sefov in ocen okolice:
![Alt text](https://github.com/darkneess10/PR17_MV_JH/blob/master/img/security_deposit_neigbourhood.png "ScatterPlot cen varnostnih sefov in ocen okolice")  
Ta graf me preseneča, saj več varnostnih sefov oddajajo stanovanja, ki so v boljši okolici, kar bi pomenilo da je okolica varnejša.
Pričakoval bi, da bo v okolici, ki je slabše ocenjena več strahu o kraji, zato bi tam pričakoval več varnostnih sefov, vendar ni tako.






