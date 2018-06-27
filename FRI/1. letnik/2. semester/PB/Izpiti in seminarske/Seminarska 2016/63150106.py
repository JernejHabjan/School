import pyodbc
import time

def clearTables():
        string0= "DROP TABLE IF EXISTS ExtraData"
        y.commit()
        y.execute(string0)

        string0= "DROP TABLE IF EXISTS ExtraDataNames"
        y.commit()
        y.execute(string0)

def prva():
        query= "SELECT AlbumName FROM Album WHERE AlbumName LIKE '%[%]%';"
        y.execute(query)

        result=y.fetchall()


        for r in result:
                cool=0
                for crka in r:
                        seznam=crka.split("[")
                        beseda=seznam[1][:-1]
                        seznam2=beseda.split("]")
                        beseda2=seznam2[0]
                        if beseda2 not in MyList:

                                MyList.append(beseda2)

        MyList.sort()
        for element in MyList:
                print(element)
        print("PRVA FINISHED")
        time.sleep(1)

def druga():

        string = "CREATE TABLE ExtraDataNames(ExtraID int, ExtraName varchar(50),  PRIMARY KEY (ExtraID)  )"
        y.commit()
        y.execute(string)

        i=1
        for dodatnoDolocilo in MyList:
                print('INSERT INTO ExtraDataNames(ExtraID, ExtraName) values ({0},{1})'.format(i, dodatnoDolocilo))
                y.execute("INSERT INTO ExtraDataNames(ExtraID, ExtraName) values (?,?)", (i, dodatnoDolocilo))
                i+=1
        y.commit()
        print("###############################")
        print("#ExtraDataNames TABLE FINISHED#")
        print("###############################")
        time.sleep(1)

def tretja():
        string = "CREATE TABLE ExtraData(AlbumID int , ExtraID int, FOREIGN KEY (AlbumID) REFERENCES Album(AlbumID),FOREIGN KEY (ExtraID) REFERENCES ExtraDataNames(ExtraID)   )"
        y.commit()
        y.execute(string)

        dictionary={} #NAREDIMO DICT
        i=1
        for dodatnoDolocilo in MyList:#MAMO DICT INDEX PA DODATNO DOLOCILO
                dictionary[str(i)]=dodatnoDolocilo
                i+=1

        #DOBIT VSE ALBUM ID
        query= "SELECT AlbumID, AlbumName FROM Album;"
        y.execute(query)
        result=y.fetchall()
        dictionary2={}
        for r in result:
                beseda2=""
                seznam=r[1].split("[")
                if len(seznam)>1:
                        beseda=seznam[1][:-1]
                        seznam2=beseda.split("]")
                        beseda2=seznam2[0]

                else:
                        beseda2="NULL"


                dictionary2[str(r[0])]=beseda2


        #INSERT V TABELO
        for key1, value1 in dictionary2.items(): #skoz vse
                for key2, value2 in dictionary.items(): #skoz imena
                        if value1==value2:

                                statement='INSERT INTO ExtraData(AlbumID, ExtraID) values ("'+key1+'", "'+key2+'")'
                                print(statement)
                                y.execute(statement)

        y.commit()

        print("##########################")
        print("#ExtraData TABLE FINISHED#")
        print("##########################")
        time.sleep(1)

#______________________________________________________________________
c=pyodbc.connect('DSN=seminarskafinal; UID=root')
y=c.cursor()
clearTables()
MyList=[]
prva()
druga()
tretja()
c.close()
