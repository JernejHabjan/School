//
// Created by Jernej Habjan on 9. 03. 2017.
//


#include <stdio.h>

int pred09() {

//majnkov sem prvo uro

    //######### Kombinacije #########################
    /*
    n števil 0,1,... n-1
     kombinacije k števil -> n^k
     permutacije -> n!
     */
    int *A; //tabela n intov (z mallovom alociramo)
    int N;//velikost permutacije
    int K;

    return 0;
}


//iterativna
void combs1(int *A, int N, int K) {

    for (int i1 = 0; i1 < N; i1++) {
        for (int i2 = 0; i2 < N; i2++) {
            for (int i3 = 0; i3 < N; i3++) {
                //k krat zanka
                for (int ik = 0; ik < N; ik++) {
                    for (int i = 0; i < K; i++) {
                        printf("%3d", A[i]);
                    }
                    printf("\n");
                }
            }
        }
    }
}

//rekurzivna
void combs(int *A, int N, int K) {
    if (K == 0) {
        //izpis
        for (int i = 0; i < K; i++) {
            printf("%3d", A[i]);
        }
        printf("\n");
    } else {
        for (int i = 0; i < N; i++) {
            A[0] = i;
            combs(&A[i], N, K - 1);
        }
    }
}

//combs(A,3,2);










//Zdaj smo generirali kombinacije
//Zdaj bomo permutacije
//Kako preveris ce je permutacija tudi kombinacija. Ce se vsak element natanko enkrat pojavi(se ena tabelca da si shranjujem kere sm ze vidu)

//######### Permutacije #########################
//tabela A ostaja
//N še vedno ostaja


//for ( int i = 0; i<N; i++) {
//    A[i] = i;
//}

//perm(A,5); //pri N>5 // izpisi mi perm prvih 5

//Posebni prmer: N=0: //jih ni
void permutations1(int *A, int N) {}

//Za primer n=1:
void permutations2(int *A, int N) { printf("%3d", N); }

//Za primer n=2:
void permutations3(int *A, int n) {
    printf("%3d %3d\n", A[0], A[1]);
    printf("%3d %3d\n", A[1], A[0]);
    //Prvo grem skozi vse elemente in drugje izpisujem vse kae mi je ostalo
}

void permutations4(int *A, int n) {//se vedno dela samo za n=2
    for (int i = 0; i < n; i++) {
        printf("%3d %3d\n", A[i], A[(i + 1) % 2]);//izpisem prvega in preostalega
    }
}

//Lahko tudi
void permutations5(int *A, int N) {//se
    for (int i = 0; i < N; i++) {
        int temp = A[0];
        A[0] = A[i];
        A[i] = temp;
        printf("%3d %3d\n", A[0], A[1]);
    }
}

void permutations6(int *A, int N);

void permutations7(int *A, int N) {//ta zdj je pa skoraj za vsa stevila
    for (int i = 0; i < N; i++) {
        //Najprej zamenjam iti element z 0 elementom
        //Izpisem lrvega na item mestu
        int temp = A[0];
        A[0] = A[i];
        A[i] = temp;
        printf(" %3d\n", A[0]);
        permutations7(&A[i], N - 1); //PAZI TOOO
    }
}

void perm3(int *A, int n);

void perm2(int *A, int n);

void perm1(int *A, int n);

void perm3(int *A, int n) {
    for (int i = 0; i < n; i++) {
        int temp = A[0];
        A[0] = A[i];
        A[i] = temp;
        perm2(&A[i], 2);
    }
}

void perm2(int *A, int n) {
    for (int i = 0; i < n; i++) {
        int temp = A[0];
        A[0] = A[i];
        A[i] = temp;
        perm1(&A[i], 1);

    }
}

void perm1(int *A, int N) {//izpise celo vrstico tabele A z dolzino N
    for (int i = 0; i < N; i++) {//veliki N--cela vrstica
        printf("%3d", A[i]);
    }
    printf("\n");
}


////Splosna
void permN(int *A, int N) {
    if (N == 1) {
        for (int i = 0; i < N; i++) {//veliki N--cela vrstica
            printf("%3d", A[i]);
        }
        printf("\n");
    } else {
        for (int i = 0; i < N; i++) {
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            permN(&A[i], 2);
        }
    }
}

//Pazi kolokvij.... Rekurzija 100%.. pa kraljice ni nben resu tko da zna bit to !!!!!!!!!!!

//______________________________
//IO Chapter
//Podatkovni tip FILE //z veliko.. to je povezava med programom in datoteko na disku

//FILE *f;//file je struct
//f = fopen("ime.txt", "r"); //r, w
//Ce ni datoteke jo naredi
//f je predstavitev datoteke

//Kako zvemo ali je uredu pri odpiranju
//if (f == NULL) printf("potem imam napako");


//printf(formatni string, parametri)
//fprintf(f, formatni string, parametri)//tko se pise v file

//fscanf(f, formatni string, parametri z naslovi);

//prntf naredi kazalec na FILE *stdout
//isto scanf

//Zapremo file
//int closeStatus = fclose(f);
//if (closeStatus != 0) {printf("imam napako pri zapiranju");
//ce ne damo close lahko kaj ostane v ramu in se ne zapise
//return 0;
//}