--izpeljan atribut

	CREATE TABLE OGLED_ST_OSEB_MESEC  AS
		select to_char(DATUM_OGLEDA, 'YYYY-MM') AS MESEC_OGLEDA, COUNT(*) AS STEVILO
		from OGLED
		group by to_char(DATUM_OGLEDA, 'YYYY-MM')
		order by 1;
	COMMIT;
	alter table OGLED_ST_OSEB_MESEC
	   add constraint OGLED_ST_OSEB_MESEC_CONSTRAINT primary key (MESEC_OGLEDA);
	COMMIT;
--trigger

	CREATE OR REPLACE TRIGGER OgledTriger
	after INSERT
	ON ogled
	for each row
	declare dd varchar2(7);
	begin 
		dd :='';
		select nvl(mesec_ogleda,null) into dd
			from OGLED_ST_OSEB_MESEC
			where mesec_ogleda = to_char(:NEW.DAtum_OGLEDA, 'YYYY-MM');
		if dd is not null then null ;
		else
			insert into OGLED_ST_OSEB_MESEC 
			values(to_char(:NEW.DAtum_OGLEDA, 'YYYY-MM'),0);
		commit;
		end if;
	end;
	/

	CREATE OR REPLACE TRIGGER UPDATE_IZRACUNAN_ATRIBUT
	AFTER INSERT
	ON VSTOPNICA
	FOR EACH ROW
	declare 
	dd VARCHAR2(7);
	GG NUMBER;
	begin
		dd := '';
		GG := :NEW.ID_OGLEDA;
		SELECT   to_char(A.DATUM_OGLEDA, 'YYYY-MM') into dd 
			FROM OGLED A WHERE A.ID_OGLEDA= GG;
		UPDATE OGLED_ST_OSEB_MESEC 
			SET STEVILO = STEVILO + 1
			WHERE mesec_OGLEDA= DD;
		COMMIT;
	end;
	/
--index
	CREATE INDEX STANJE_ZIVALI ON ZIVAL (STANJE); COMMIT;
		--pri živali je pomembno da vemo njeno stanje za odvzemanje vzorcev in hitro ukrepanje.
	CREATE INDEX TIP_OSEBJA ON OSEBJE (TIP_OSEBJA); COMMIT;
		--da lahko osebje hitro dodelimo na primerna delovna mesta.
	CREATE INDEX DATUM_DOBAVE ON NAROCILO_HRANE (DATUM_DOBAVE); COMMIT;
		--da hitro zvemo kdaj lahko pričakujemo datum dobave.
	CREATE INDEX VSTOPNICA_DATUM ON VSTOPNICA (DATUM); COMMIT;
		--iskanje po datumu je počasnejše, velikokrat pa ravno ta podatek potrebujemo.
	CREATE INDEX DATUM_OGLEDA ON OGLED (DATUM_OGLEDA); COMMIT;
		--podobno kot v zgornjem primeru
		
--view

-- POGLED 1:
	--DROP VIEW VZOREC_VIEW;
	CREATE VIEW VZOREC_VIEW AS
	SELECT ZIVAL.IME AS IME_ZIVALI, ZIVAL.IME_PREBIVALISCA, ZIVAL.SPOL, ZIVAL.STANJE, 
		ZIVAL.TEZA, ZIVAL.DATUM_ROJSTVA, VZOREC.DATUM_VZORCA, VZOREC.KOMENTAR, OSEBJE.IME AS IME_OSEBJA, OSEBJE.PRIIMEK
	FROM OSEBJE JOIN VZOREC ON (OSEBJE.EMSO = VZOREC.EMSO)
				JOIN ZIVAL ON (ZIVAL.ID_ZIVALI = VZOREC.ID_ZIVALI);
	SELECT * FROM VZOREC_VIEW;

-- POGLED 2:
	--DROP VIEW OGLED_VIEW;
	CREATE VIEW OGLED_VIEW AS
	SELECT IME AS IME_VODICA, PRIIMEK AS PRIIMEK_VODICA, OGLED.NAZIV_JEZIKA AS JEZIK_OGLEDA, DATUM_OGLEDA, ZIVALSKA_VRSTA.IME_VRSTE
	FROM OSEBJE JOIN OGLED ON (OGLED.EMSO = OSEBJE.EMSO)
				JOIN JEZIKI ON (JEZIKI.NAZIV_JEZIKA = OGLED.NAZIV_JEZIKA)
				JOIN OGLEDAM_SI ON (OGLED.ID_OGLEDA = OGLEDAM_SI.ID_OGLEDA)
				JOIN ZIVALSKA_VRSTA ON (OGLEDAM_SI.IME_VRSTE = ZIVALSKA_VRSTA.IME_VRSTE);
	SELECT * FROM OGLED_VIEW;		
