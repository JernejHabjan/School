#AVTOR: JERNEJ HABJAN
#1:
#a)
SELECT AlbumID, Price FROM Price;
#b)
SELECT AlbumID, Price
FROM Price
WHERE Price IS NOT NULL;
#c)
SELECT AlbumID, st_medijev,Medium FROM (
	SELECT AlbumID, NumberOfDiscs AS st_medijev, Medium, Price/NumberOfDiscs AS razmerje
	FROM Price JOIN DiscNumber  USING (albumID)
		    JOIN Medium      USING(AlbumID) 
		    JOIN MediumNames USING (MediumID)
	GROUP BY AlbumID
	ORDER BY razmerje) AS T;
#d)
SELECT MIN(Year), MAX(Year)
FROM Year;
#e)
SELECT AuthorID, AuthorName, COUNT(AlbumID) AS st_albumov
	FROM Author JOIN AuthorNames USING (AuthorID)
	GROUP BY AuthorID, AuthorName
	ORDER BY st_albumov DESC;
#f)
DELIMITER //
	DROP PROCEDURE IF EXISTS avgPric //
	CREATE PROCEDURE avgPric(IN cena DOUBLE)
	BEGIN
	SELECT Label, ((SELECT AVG(Price)
				FROM Price JOIN Album USING(AlbumID) JOIN Label USING(AlbumID))-(AVG(Price))
			   ) as Razlika_Te_minus_povprecna
		FROM Price p JOIN Album USING(AlbumID) JOIN Label USING(AlbumID) JOIN LabelNames USING(LabelID)
		WHERE p.Price=cena
		GROUP BY LabelID
	
		HAVING COUNT(AlbumID) >= 10
		ORDER BY Razlika_Te_minus_povprecna DESC;
	END //
	DELIMITER ;
CALL avgPric(5.99);
#g)
SELECT COUNT(*) AS dodatna_dolocila
FROM Album
WHERE AlbumName LIKE '%[%]';
#h)
DROP PROCEDURE IF EXISTS Priporocam;
DELIMITER $
CREATE PROCEDURE Priporocam(IN isci VARCHAR(50))
BEGIN
	SELECT a.AlbumID, a.AlbumName, (SELECT recommended.AlbumName
	FROM Album recommended
	WHERE r.RecommendationID=recommended.AlbumID) AS RecommendedAlbum
		FROM Album a JOIN Recommendation r USING(AlbumID)
		WHERE UPPER(a.AlbumName)=UPPER(isci);
END $
DELIMITER ;
CALL Priporocam("Disturbed - Indestructible");
#i)
SELECT DISTINCT aN2.AuthorName AS Recommended
FROM AuthorNames aN1 JOIN Author a1 ON(aN1.AuthorID=a1.AuthorID) 
			JOIN Recommendation r1 ON(a1.AuthorID=r1.AlbumID)
			JOIN Recommendation r2 ON(r1.RecommendationID=r2.AlbumID) 
			JOIN Author a2 	 ON(r2.RecommendationID=a2.AlbumID)
			JOIN AuthorNames aN2 	 ON(a2.AuthorID=aN2.AuthorID)
WHERE aN1.AuthorName="U2";
#j)
ALTER TABLE Recommendation ADD INDEX(AlbumID);
#######################################################
#2:
#a)
DROP TABLE IF EXISTS razsirjeni_izvajalci;
DROP VIEW IF EXISTS razsirjeni_izvajalci;
CREATE VIEW razsirjeni_izvajalci AS
	SELECT AuthorID, AuthorName, COUNT(AlbumID) AS st_njegovih_albumov
	FROM Author JOIN AuthorNames USING(AuthorID)
	GROUP BY AuthorID, AuthorName;
SELECT * FROM razsirjeni_izvajalci;
#b)
DROP TABLE IF EXISTS razsirjeni_izvajalci;
DROP VIEW IF EXISTS razsirjeni_izvajalci;
CREATE TABLE razsirjeni_izvajalci AS
	SELECT AuthorID, AuthorName, COUNT(AlbumID) AS st_njegovih_albumov
	FROM Author JOIN AuthorNames USING(AuthorID)
	GROUP BY AuthorID, AuthorName;
SELECT * FROM razsirjeni_izvajalci;
#c)
DROP TRIGGER IF EXISTS on_Razsirjeni_Insert;
DELIMITER $
CREATE TRIGGER on_Razsirjeni_Insert
AFTER INSERT ON Album
FOR EACH ROW
BEGIN
	UPDATE Razsirjeni_izvajalci SET 
	
		st_njegovih_albumov = (SELECT COUNT(AlbumID)
									FROM Author
									WHERE AuthorID = AuthorID);									
END $
DELIMITER ;
#______________________________________________
DROP TRIGGER IF EXISTS on_Razsirjeni_Delete;
DELIMITER $
CREATE TRIGGER on_Razsirjeni_Delete
AFTER DELETE ON Album
FOR EACH ROW
BEGIN
	UPDATE Razsirjeni_izvajalci SET 
	
		st_njegovih_albumov = (SELECT COUNT(AlbumID)
									FROM Author
									WHERE AuthorID = AuthorID);									
END $
DELIMITER ;
#######################################################
#3:
DROP TABLE IF EXISTS GroupedData;
CREATE TABLE GroupedData AS

	SELECT y.Year, p.Price, NumberOfDiscs, MediumID
	FROM Year y JOIN Price p    USING (AlbumID)
					JOIN DiscNumber USING (AlbumID)
					JOIN Medium     USING (AlbumID);

SELECT * FROM GroupedData;

DROP TABLE IF EXISTS Year;
DROP TABLE IF EXISTS Price;
DROP TABLE IF EXISTS DiscNumber;
DROP TABLE IF EXISTS Medium;

ALTER TABLE GroupedData ADD FOREIGN KEY(MediumID) REFERENCES MediumNames(MediumID);
