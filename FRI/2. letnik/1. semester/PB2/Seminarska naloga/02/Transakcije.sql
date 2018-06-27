--1. Število udeležencev v posameznem ogledu: Entitetni tip: Ogled(datum ogleda, začetek, število udeležencev),
  SELECT ID_OGLEDA, COUNT(IDV) AS UDELEZENCEV
  FROM VSTOPNICA
  GROUP BY ID_OGLEDA;

--2. Kateri primerki živali živijo v posameznem prebivališču: Entitetni tip:Prebivališče(ime), Žival(ime)
  SELECT PREBIVALISCE.IME_PREBIVALISCA, ZIVAL.IME
  FROM PREBIVALISCE JOIN ZIVAL ON(PREBIVALISCE.IME_PREBIVALISCA = ZIVAL.IME_PREBIVALISCA);

--3. Količina hrane, ki jo poje določena žival na dan: Entitetni tip: Žival(ime živali), Živalska vrsta(ime vrste), Hranjenje(količina)
  SELECT ZIVAL.IME, HRANJENJE.KOLICINA
  FROM ZIVAL JOIN ZIVALSKA_VRSTA ON (ZIVAL.IME_VRSTE = ZIVALSKA_VRSTA.IME_VRSTE)
			 JOIN HRANJENJE ON  (ZIVALSKA_VRSTA.IME_VRSTE = HRANJENJE.IME_VRSTE);
			 
--4. Izpis bolnih živali in njihovih prebivališč: Entitetni tip: Žival(ime), Prebivališče(ime prebivališča)
  SELECT ZIVAL.ID_ZIVALI, ZIVAL.IME, PREBIVALISCE.IME_PREBIVALISCA
  FROM ZIVAL JOIN PREBIVALISCE ON (ZIVAL.IME_PREBIVALISCA = PREBIVALISCE.IME_PREBIVALISCA)
  WHERE ZIVAL.STANJE = '2'; --2 je bolna zival

--5. V katerem skladišču se nahaja določena hrana: Entitetni tip: Zaloga hrane(naziv hrane), Skladišče(ime skladišča, lokacija)
  SELECT NAZIV_HRANE, IME_SKALDISCA FROM ZALOGA_HRANE;

--6. Katero prebivališče je očistil določen uslužbenec: Entitetni tip: Osebje(ime, priimek), Čiščenje prebivališča(datum), Prebivališče(ime prebivališča)
  SELECT EMSO, IME_PREBIVALISCA FROM CISCENJE_PREBIVALISCA;
  
--7. Katere vzorce smo vzeli: Entitetni tip: Žival(ime živali), Vzorec(datum vzorca, komentar), Osebje(ime, priimek)
  SELECT ZIVAL.IME, VZOREC.DATUM_VZORCA, VZOREC.KOMENTAR, OSEBJE.IME, OSEBJE.PRIIMEK
  FROM ZIVAL JOIN VZOREC ON (ZIVAL.ID_ZIVALI = VZOREC.ID_ZIVALI)
			 JOIN OSEBJE ON (VZOREC.EMSO = OSEBJE.EMSO);
			 
--8. Kdo sta starša živali: Entitetni tip: Žival(ime, oče, mati)
  SELECT ZIVAL.IME AS IME_ZIVALI, OCE_TABELA.IME AS IME_OCETA, MATI_TABELA.IME AS IME_MATERE
  FROM ZIVAL JOIN ZIVAL OCE_TABELA ON(ZIVAL.ID_ZIVALI = OCE_TABELA.ID_OCETA)
			 JOIN ZIVAL MATI_TABELA ON(ZIVAL.ID_ZIVALI = MATI_TABELA.ID_MATERE);
			 
--9. V katerih jezikih je možen ogled Entitetni tip: Jeziki(Naziv jezika)
  SELECT * FROM JEZIKI;
  
--10. Koliko vstopnic smo prodali danes: Entitetni tip: vstopnice(število vstopnic).
  select to_char(DATUM_OGLEDA, 'YYYY-MM-DD') AS DAN_OGLEDA, COUNT(*) AS STEVILO
			from OGLED
			group by to_char(DATUM_OGLEDA, 'YYYY-MM-DD')
			order by 1;
