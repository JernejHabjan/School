//
// Created by Jernej Habjan on 7. 03. 2017.
//


///Struktura programa
//V dat. -deklaracije
//-definicije
//  Z njima lahko definiramo
//       -podatkovne tipe
//       -funkcije
//      -spremenljivke
//Vsaka mora biti vnaprej definirana in sele potem deklarirana


///Funkcije
//-deklaracija funkcije
//      <Tip rezultata><ime f.> (<Seznam arg>);
//      Primeri
//         Int pow(int m, int n);
//         Ali pow(int m, int n);
//         Ali  pow(int, int);

//-definicija funkcije
//     <Tip rezultata><ime f.> (<Seznam arg>){ <Koda> }
//    Primer:
//   Int Pow(int m, int n){koda}
//   Ali pow(int m, int n){koda}
//   Ali pow(n, m) int n int m{koda}


///Spremenljivke
//Deklaracija
//  <Tip><ime spremenljivke>;
// Primer int a;
//              Int i,j,k;
//Definicija
// <Tip><ime spremenljivke>=<zacetna vrednost>;
//Primer int a = 5


//Vrste spremenljivk
//    1. Avtomatske spremenljivke
//        (So fizicno shranjenje na skladu)
//        -obicaje lokalne spremenljivke v funkcijah
//        -parametri funkcij
//    2. Zunanje spremenljivke
//       (So shranjenje v staticnem delu pomnilnika)
//        -obicajne spremenljivke zunaj funkcije
//        -spremenljivke ki so definirane zunaj datoteke.c

//        Primer
//        Int k=0; //k je viden povsod v programu//
//        Int fun1(){
//           k++;
//        }
//        Int main(){
//            fun1();
//            fun1();
//         // Printf("%d",k);//ne moremo ker je vidno le v fun1
//           Return 0;
//        }// -izpise 2

//        Kako vidimo "globaln" var v drugmu c filu
//        Extern int k;

//    3. Staticne spremenljivke
//        (So shranjenje v staticnem delu pomnilnika)
//        -znotraj funkcije obdrzijo svojo vrednost med klici

//        Int fun1(){
//           static int k=0; //staticna sprem
//          //Tukaj je k skrit v "fun1"
//          //Prirejanje nicle se naredi se preden se prvic
//         //poklice fun1()
//           k++;
//        }
//        Int main(){
//         fun1(); fun1();
//          //Printf("%d",k);
//           Return 0;
//       }// -izpise 2

//       -zunaj funkcije a znotraj svoje datoteke

//       Static int k=0; //vidna le v tej datoteki
//       Int fun1(){}...

//   4. Registrske spremenljivke
//        //Registrov reda 10
//       register int i;   //brez vsakega smisla

///Razdelitev pom
//spodaj -koda programa(strojna koda)
//Nad tem -kos pomnilnika namenjen staticnim spremenljivkam
//   (Ta del je razmeroma staticen)
//Ostai del je dinamicen in vsebuje
//   -procesorski sklad(za rekurzijo..) z vrha dol (prostora je malo zato ne smemo imeti prevec avtomatskih spremenljivk -npr tabela z 10k spremenljivk)(lahko dela na wins in ne na Linux saj Wins lahko dodelijo programu vecji sklad)
//   -heap (od spodaj gor) (prostora je veliko)
//      (Tukaj moramo deletati spremenljivke ker nima garbage collectorja, drugje jih nemormo, tle pa mormo!)

///Naloga:
//Stevila
//13195
//Prafaktoeji
//5,7,13,29

//Kateri je najvecji prafaktor stevila
//        N=600851475143

//rešitev:
//    Gres z  i od 2-n
//            Delis z ijem to st. in ce je ostanek 0, shranis to cifro
//            N/=i
//            If i !=2 i+=2 else i++


#include <stdio.h>

void getMaxPrafactor() {

    long long n;
    n = 600851475143;
    //n =88;

    int deljitelj = 2;
    int maxPrafactor = 1;
    while (n != 1) {
        while (n % deljitelj == 0) {
            maxPrafactor = deljitelj;
            n /= deljitelj;
        }
        if (deljitelj != 2) deljitelj += 2;
        else deljitelj++;

    }
    printf("%d", maxPrafactor);
}

int pred03() {

    getMaxPrafactor();
    return 0;
}