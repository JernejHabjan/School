# Seminarska naloga pri podatkovnem rudarjenju  
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
Washington, D.C., District of Columbia  
```
		
Tako da bi popotnik resnično lahko potoval po velikem delu države in imel izpis vseh prenočišč na poti.
		
### 1.2 Registracije in tehnični pregledi 
Podatke sva našla na podatki.gov.si: https://podatki.gov.si/dataset/prvic-registrirana-vozila-po-mesecih  
Te so zapisani v CSV datoteki in imajo manjkajoče vrednosti. Je pa več datotek in sicer vsaka je za svoj mesec.

S temi podatki bi lahko pridobila socialno stanje prebivalstva v sloveniji po regijah saj avtomobili predstavljajo 
znaten znesek v družini. Tako bova lahko prikazala kateri predeli Slovenije vsebujejo največji delež registriranih 
vozil, saj lahko sklepamo, da si večina lastnikov prevozno sredstvo registrira v najbližji poslovalnici. Lahko bova 
našla kakšne trende s kupovanjem prevoznih sredstev in kdaj je najbolje oglaševati kakšno znamko in komu. 

S podatki iz https://podatki.gov.si/dataset/rezultati-tehnicnih-pregledov-motornih-vozil bi lahko zgradila sistem, ki
bi skupaj z podatki o prvotnih registracijah pomagal voznikom izbor novega vozila glede na potencialne okvare. Uporabila
bi tudi ostale atribute, ki jih je dano vozilo pokazalo na prvem tehničnem pregledu. Tudaj bi dodala še izbor 
cenovnega razreda in ceno samega vzdrževanja vozila.
		
		
		
		
		
