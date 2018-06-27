//
// Created by Jernej Habjan on 10. 03. 2017.
//


#include <stdio.h>
#include <stdlib.h>

//DN -- z eno zanko kombinacije --nben naredu
//DN -- 8 dam na sahovsko  -- Rek + pogoji( vrstica, stolpci, obe diag)
//DN -- sudoku - dobra vaja


long int fibIterative(int N) {
    long int f1 = 1;
    long int f2 = 1;
    for (int i = 1; i < N; i++) {
        long int f = f1;
        f1 = f2;
        f2 = f2 + f;
    }
    return f2;
}

long int fibRek(int n) {
    if (n == 0) return 1;
    if (n == 1) return 1;
    return fibRek(n - 2) + fibRek(n - 1);
}

//Iterativna traja O(n) -- fibIterativne(50);
//Rekurzivna pa traja -- fibRek(50);

// Zato tabela

long int fib[4];

long int fibRecMemo(int n) {
    if (n == 0) return 1;
    if (n == 1) return 1;
    static long int fib[4] = {1, 1,};//pazi poglej kko to narest.. ker je static so nicle notr
    if (n > 4) {
        return fibRecMemo(n - 2) + fibRecMemo(n - 1);

    }
    if (fib[n == 0])
        fib[n] = fibRecMemo(n - 2) + fibRecMemo(n - 1);

    return fib[n];
}//Ce je tabela mejhna porezem majhna drevesca

//Ce vec iteracij... naredimo z malloc tabelo za 1000, pol k to presezemo naredimo realloc za 2* 1000.. --nared to

//IO --100 fib na datoteko
//Ce ze obataja dat se zbrise in nova, ce ne se pa uatvari

int main_example2() {
    FILE *f;
    f = fopen("fib.txt", "w");
    if (f == NULL) {
        printf("napaka-nemormo odpreti dat");
        return 1;
    }
    for (int i = 0; i < 100; i++) {
        fprintf(f, "%ld\n", fibIterative(i));//ld je long int
        //Ko dobi \n takoj zapise na disk... Isto pazi pr printf...notr dodejej \n  da izpise predn crasha program
        //Isto bi naredu fflush(f);-- takoj izpise na file

    }
    fclose(f);
    return 0;
}

//Sumnikov nikol v source file v c!!

//Poganjanje z ukazne vrstice
//   ./fib fib_text.txt

int main_example(int argc, char *argv[]) {//argv je tabela kazalcev na characterje
//Argv je tabela nizov. Vsak niz je 1 argument ukazni vrstici

    if (argc < 2) {
        printf("napaka");
        exit(1);
    }
    //Zakaj 2 ukaza -- nicti argument je ime c programa
    //  ./fib lol.txt -> argv[0] je ./fib
    //argv[1]  je lol.txt

    FILE *f;
    f = fopen(argv[1], "w");//prvi arg je lokacija
    if (f == NULL) {
        fprintf(stderr, "nemorem odpreti datoteke '%s' .", argv[1]);
        exit(1);
    }
    for (int i = 0; i < 100; i++) {
        fprintf(f, "%ld\n", fibIterative(i));
    }
    fclose(f);
    return 0;
}


int pred10() {
    //Izpis napak:
    //Je tudi stdout//stderr leti tudi na zaslon
    fprintf(stderr, "nimam imena dat");

    // Exit je v stdlib.h
    //Exit zapre odprte file

    //Branje
    FILE *f;
    f = fopen("fib.txt", "r");
    for (int i = 0; i < 100; i++) {
        long int fib;

        fscanf(f, "%ld\n", &fib);
        //while(fscanf... != '\n') //beri besedilo po znak in znak in testiraj
        printf("%ld\n", fib);
    }
    fclose(f); //return 0;

    //Test eof
    if (feof(f))printf("smo na koncu datoteke");


    return 0;
}