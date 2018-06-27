//
// Created by Jernej Habjan on 2. 03. 2017.
//
#include <stdio.h>



/* ## predavanje 03.03.2017 ## */
//naloge kakšne na
//PROJECT EULER

//DOLŽINE INT, LONGINT:
//long long je zmeraj 64 bitni - pazi na virtualki -> long - 32 bitni ker so 32 bitni op sistemi
//printf("int : %ld, long: %ld, long long: %ld" , sizeof(int), sizeof(long), sizeof(long long));
//sizeof vrača tip long zato smo dali %ld

//OTHER TYPES:
//#include <inttypes.h>
//printf("int32_t : %d, int64_t: %d" , sizeof(int32_t), sizeof(int64_t));

//IZPIS S PRINTF:
//int -> %d
//long -> %ld
//long long -> %lld

//PISANJE ŠTEVIL
//bolj prov bi bilo
//long long c = 5LL;
//namesto
//long long c = 5;
//moramo pa pisati LL pri velikih številih:
//long long d= 123456789012345678LL;

// 1. naloga:
/*
    Stevilo imamo v desetiškem sistemu, pretvoriti ga moramo v ciljni d-tiški sistem
    od 2-9.
    Izpise naj se v obratnem vr. redu.
     19:2 = 9 ost 1
     9:2 = 4 ost 1
     4:2 = 2 ost 0
     2:2 = 1 ost 0
     1:2 = 0 ost 1
     izpisujemo sproti
*/

void pretvori() {
    //preberemo izvorno število in osnovo ciljnega številskega sistema

    int stevilo = 0;
    char znak = (char) getchar();

    while (znak != ' ') { //ponavljam dokler ne pridemo do presledka
        int stevka = znak - '0';

        stevilo = 10 * stevilo + stevka; // 0*10 + 4 = 4
        // 4*10 +2 = 42
        znak = (char) getchar();
    }
    int sistem = getchar() - '0';
    printf("stevilo = %d, sistem = %d\n", stevilo, sistem);


    while (stevilo > 0) {
        int ostanek = stevilo % sistem;

        putchar(ostanek + '0'); //ascii kodo prištejemo - kar nič v narekovajih
        stevilo /= sistem;

    }
    putchar('\n');
}


// 2. naloga:
/*
Fakulteta:
Napišite program ki prebere praštevilo in pozitivno število ter izpiše najmanjše pozitivno celo število n pri katerem
 je vrednost n! deljavi z vrednostjo p^k
 p^k % n! == 0 -> p^k deli n!
 p = 3 k = 4 -> poišči n da 3^4 /n!
    rezultat je 9 ker je 9! deljiva z 81
*/

typedef long long llong; //da se nebomo zmotili in lahko pišemo llong namesto long long

//Primer1:
void faculty1() {
    //preberemo praštevilo (p) in eksponent (k)

    llong prastevilo, eksponent;
    scanf("%lld %lld", &prastevilo, &eksponent);

    //printf("prastevilo %lld eksponent %lld", prastevilo, eksponent);

    llong potenca = 1;
    //pow -> ne uporabimo pow ker vrača long?
    for (llong i = 1; i <= eksponent; i++) {
        potenca *= prastevilo;
    }
    //printf("potenca = %lld\n", potenca);


    llong n = 1;
    llong fakulteta = 1;
    while (fakulteta % potenca != 0) {
        n++;
        fakulteta *= n;
    }
    printf("%lld\n", n);

    //funkcija faila že pr tretjem testu saj
    //20! prebije mejo 10^18
}

//Primer2:
void faculty2() {
    //ko//kolikokrat se pojavi faktor k krat
    //če vzamemo p = 3 -gledamo faktorje 3, k=4 - gledamo da bodo 4je faktorji
    //1 2 3 4 5 6 7 8 9
    //    3     3     3|3 ---so že 4je faktorji

    llong prastevilo, eksponent;
    scanf("%lld %lld", &prastevilo, &eksponent);

    //koliko faktorjev prastevila imam v trenutni faklulteti
    llong stFaktorjev = 0;
    llong n = 0;
    //zanka se bo izvajala tako dolgo da ne pridemo do k
    while (stFaktorjev < eksponent) {
        //n++;
        //n lahko povečujem za praštevilo -> saj:
        //število n ki ni deljivo s praštevilom ne vsebuje nobenega faktorja p
        n += prastevilo;

        //izracunam kolikokrat lahko stevilo n delim s prastevilom
        //naprimer stevilo 9 lahko 2x delim s prastevilom 3
        llong m = n;
        while (m % prastevilo == 0) {
            m /= prastevilo;
            stFaktorjev++;
        }
        //izpis faktorjev itd
        //printf("n = %lld, stFaktorjev = %lld\n", n, stFaktorjev);
    }
    printf("%lld", n);
}

//Primer3:
void faculty3() {
    //najdl en vzorec
    /*
    n      št fakorjev p v n!
    3      1
    9      3*1 + 1 = 4
    27     3*4 + 1 = 13
    81     3*13 + 1 = 40
   ...
    p      1
    p^2    p^2 + 1
    ..
    p^r    f
    p^r+1  p^f +1

     */




    llong prastevilo, eksponent;
    scanf("%lld %lld", &prastevilo, &eksponent);

    llong preostaloSteviloFaktorjev = eksponent;

    llong n = 0;
    while (preostaloSteviloFaktorjev > 0) {
        //printf("preostalostevilofaktorjev %lld\n", preostaloSteviloFaktorjev);

        //1. poišči največji mejnik ki je prav za naše podatke
        //stevilo oblike p^nekaj ki vsebuje kvečjemu preostaloSteviloFaktorjev faktorjev

        llong stFaktorjevZaMejnik = 1;
        llong mejnik = prastevilo;

        //sledeca zanka potuje po mejnikih
        while (prastevilo * stFaktorjevZaMejnik + 1 <= preostaloSteviloFaktorjev) {
            // izracunam naslednji mejnik in stevilo faktorjev ki ga ta vsebuje
            stFaktorjevZaMejnik = prastevilo * stFaktorjevZaMejnik + 1;
            mejnik *= prastevilo;

            //printf("mejnik = %lld, stevilofaktorjevZaMejnik = %lld\n", mejnik, stFaktorjevZaMejnik);
        }
        //printf("rezultat: mejnik = %lld, stevilofaktorjevZaMejnik = %lld\n", mejnik, stFaktorjevZaMejnik);

        //2. mejnik je sedaj največji mejnik ki vsebuje kvečjemu preostalo številofaktorjevFaktorjev
        //stFaktorjevZaMejnik je ša število faktorjev v tem mejniku


        preostaloSteviloFaktorjev -= stFaktorjevZaMejnik;
        n += mejnik;
    }

    printf("%lld", n);


}

int pred02() {
    //pretvori();
    //faculty1();
    //faculty2();
    //faculty3();

    return 0;
}