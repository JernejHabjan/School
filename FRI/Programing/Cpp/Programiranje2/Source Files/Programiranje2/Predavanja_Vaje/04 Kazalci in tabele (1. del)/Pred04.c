
#include<stdio.h>

///Osnovni podatkovni tipi in pointerji


//char-min enota v pom ki lahko samostojno naslavlamo (8bit max)
//int(vsaj 16bit -> -2**8 do 2**8-1),
//float (doloca procesor)
//double(doloca procesor)

///Celostevilski tipi
///int (32 bit) ==signed int
//Short int
//Long int  == long (spuscen int oznaka)(64 bit)
///Long long int
//Signed int
//Unsigned int
//Signed short int
//Unsigned short int
//Signed long int
//Unsigned long int
//Signed long long int
//Unsigned long long int
//Char                c=:A' //v resnici c je 65
///Unsigned char
//Signed char

//Logicni tipi in izrazi
//V javi se izracuna i>j v true ali false
//V c -- while (a) ... Dokler je  !=0 ... Lahko je tud minus!!

//_Bool ... Tko je def v cju
#include <stdlib.h>
//Imamo bool  s konstantama true in false

//Kako zves kolko dovg je
//printf("%lu", sizeof(int)); vrne long unsigned v bajtih (ne dobimo 32 ampak 4)

///Pointers
//Kazalec je sprem ki vsebuje pom. naslov
//Int i;
//Int *pi; //pi je kazalec na i ,,, *pi je int
//Pointer je tok velk kokr mamo velk pomnilnik
//Ce 2**64 pomnilnik -- 64 bit ==8B velk pointer

//i = 10
//pi =&i; -- v pi kaze pomnilnisko lokacijo kje je spravlen
//printf(%d, *pi) // gre gledat na naslov od pi in vzame kazalc kar je gor..

//Pol pa
//int j = 7;
//pi=&j; -- spremenimo kazalc

//Pointas lah po svojmu kosu pomnilnika in ne na druge procese


//Ob predpostavki
//Sizeof(int)=4;
//Sizeof(int*)=8;
//Sizeof(long)=8;
// long l=(long) pi;
//printf("%ld",l); -- long decimal
//Izpise naslov spremenljivke i

///   &
//&Vzemi var in vrni naslov
///   *
//*Vrne vrednost na naslovu

//Scanf-u damo naslov kamor shranmo spremenljivko

//void neki(int *a){
//*a=*a+5;
//} --tuki se vid da c nima reference
//Klic-- neki(&a);



///Kazalci in tabele

//1d tabele
//int a[10];
//Razlika
//a[10] --index out of bounds v javi
//- v cju to ni error

//Program erastostenovo sito

int primes[
        100 + 1]; // primes od nic do 100 deklarirano zunaj na staticnem pomnilniku in ne znotraj v funkciji(na stacku)
void erastostenovoSito() {

    primes[0] = primes[1] = 0;
    for (int i = 2; i <= 100; i++) {
        primes[i] = 1; //privzamemo da so vsi prastevila
    }
    for (int i = 2; i <= 100; i++) {
        if (primes[i] == 1) {
            for (int j = 2 * i; j <= 100; j = j + i) {
                primes[i] = 0;
            }
        }
    }
    for (int i = 2; i < 101; i++) {
        if (primes[i] == 1)
            printf("%d ", i);
    }

}


//Tiste spremenlivke ki so deklarirane zunaj imajo default vrednost 0 !!!!!!!

//If stavki
// If (c==1) // ta pravi ali je c 1
// If(c) //ta pravi ali c NI 0

///dn
//Napisi ta program z
//char primes[12]; //da bitke vn vzemas

///Deklaracija tabele
//Int main()
// static int primes[100000001];// zdaj je tabela vidna samo tej funkciji in je deklarirana v staticnem pomnilniku -good

//Deklariraj zunej.. je v static ramu pa se default je 0

///S pointerji
// int *p =&a[0] // -> *p je isto kot a[0]
// Lahko recemo pol tud p[0]

///Kopica
//(So v stdlib te trije ukazi)
//void *malloc(set numbytes)// na kopici rezervira pa vrne kazalv
//void *realloc(void *ptr, numbytesnew)// vrne kazalc na nek nov kos pomnilnika
//void *free(void *ptr)

//Int *primes           //potrebujem za 101 int prostora na kopici
//Primes = (int*) malloc((100+1) * sizeof(int))
//To lahko napisemo v funkcijo main
//Typeast na int pointer.. da conpiler ve
//Primes pointer zasede 4B.. blok na kerga kaze pa zasede 404B

//Kaj ce malloc nmor alocatat
//If primes == NULL
//Print(ni dovolj pomnilnika)
//Zdaj lahko recem primes[0]...

//Free
//free(primes) --to damo npr pred return 0

//Vedno ko naredim malloc naredim tud free

//primer:

int primes_example() {
    int *primes;
    primes = (int *) malloc(101 * sizeof(int));//na skladu mam 4B na kopici pa 404B
    //Nmors napisat primes.length ampak mors vedt kok si allocatov
    if (primes != NULL) {

        //Zdaj lahko
        //primes[0]....ali
        primes[0] = 1;
        //*(p+5)... Toj isto kot p[5]... Ne pisat prve vrjante
        //  p++ -> poveca za en type pointer (npr premakne za 1 int naprej)
    }
    free(primes);
    return 0;
}


//---- to tle spodi je isto kot prva funkcija
void podobna_prvi() {
    int *p = &(primes[2]);
    for (int i = 2; i <= 100; i++) {
        *p = 1;
        p++;
    }
}


///Pass v funkcije
int a[10];

int f_pred4(int b[10]) {
    printf("temp");
    return 0;
}//.... tud ce tuki b spreminjam v resnici spreminjam tabelo a..
// ker ko posljem tabelo.. posljem ubistvu naslov
//f_pred4(a);// <- tukaj posljem naslov a z indeksom 0 v funkcijo f

//Lahko pa
int f_pred4_1(int *a) {
    printf("temp");
    return 0;
}

//Lahko pa
int f_pred4_2(int a[]) {
    printf("temp");
    return 0;
}


//sum(&(nums[15]),10);// -- to bo sestel naslednih 10 elementov pri zacetku na 15... Notr posljemo naslov kje je shranjen 15. Element v tabeli



///Nizi v cju
//Enodimenzionalne tabele znakov
//Niz znakov je zakljucen z znakom \0

char niz[10]; // niz lahko vsebuje nize ki so sestavljeni iz max 9 znakov.. ker je zadnji znak \0

//"ABC" spremeni pr compilanju v 656667,0.. v pom so 4B

char niz[10] = "ABC";//.. kar je tukaj od 4 bajta naprej je odvjsno aj v static ram al ne

//printf("%s", niz); //... Printf bo izpisvov doker ne pride do \0
//Ce ni nakonc \0.. printf izpisuje kr naprej.. kr po pom naprej

//V cju tud obstaja #include <string.h>


int pred04() {
    erastostenovoSito();

    return 0;
}