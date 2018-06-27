//
// Created by Jernej Habjan on 10. 03. 2017.
//


//#define MAX_STR_SIZE 17;

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

void izpisiVrstico(char *vrstica, int dolzinaVrstice, int ciljnaDolzinaVrstice) {
    int zacetniPresledki = (ciljnaDolzinaVrstice - dolzinaVrstice) / 2;
    for (int i = 0; i < zacetniPresledki; i++) {
        printf(" ");
    }
    printf("%s\n", vrstica);
}


void dodajVVrstico(char *vrstica, int dolzinaVrstice, char *beseda) {
    /*
Doda besedo na konec vrstice.
vrstica -- kazalec na začetek vrstice
dolzinaVrstice -- dolžina vrstice (v bistvu odveč, ker jo lahko dobimo s strlen)
beseda -- kazalec na začetek besede, ki jo bomo dodali na konec vrstice
*/


    //printf("%d\n", dolzinaVrstice);
    //printf("%d\n", (int) strlen(vrstica));
    //printf("%s", vrstica);



    char *izvor = beseda;
    char *cilj = vrstica + dolzinaVrstice;  // cilj kaže na končni znak '\0' v nizu <vrstica>

    while (*izvor != '\0') {
        *cilj = *izvor;
        izvor++;
        cilj++;
    }
    *cilj = '\0';
}

int sredinskaPoravnava() {
    int ciljnaDolzinaVrstice;
    scanf("%d", &ciljnaDolzinaVrstice);

    // ta tabela bo hranila pravkar prebrano besedo
    char *beseda = malloc((ciljnaDolzinaVrstice + 1) * sizeof(char));

    // ta tabela bo hranila trenutno vrstico (vanjo sproti dodajamo besede,
    // ko doseže ciljno dolžino, pa jo izpišemo skupaj z začetnimi presledki)
    char *vrstica = malloc((ciljnaDolzinaVrstice + 1) * sizeof(char));

    int dolzinaVrstice = 0;
    bool vrsticaPrazna = true;

    while (scanf("%s", beseda) == 1) {
        //printf("%s\n",beseda);
        int dolzinaBesede = (int) strlen(beseda);

        // izračunamo, kako dolga bi bila vrstica, če bi vanjo dodali
        // pravkar prebrano besedo

        int novaDolzinaVrstice = dolzinaVrstice + dolzinaBesede;
        if (!vrsticaPrazna) {
            // če vrstica ni prazna, moramo pred besedo dodati še presledek
            novaDolzinaVrstice++;
        }

        if (novaDolzinaVrstice > ciljnaDolzinaVrstice) {
            // besede v trenutno vrstico ne moremo več dodati, saj bi presegli
            // vrednost ciljnaDolzinaVrstice

            // vrstico izpišemo (skupaj z začetnimi presledki) in
            // izpraznimo tabelo <vrstica>
            izpisiVrstico(vrstica, dolzinaVrstice, ciljnaDolzinaVrstice);
            vrstica[0] = '\0';
            vrsticaPrazna = true;
            dolzinaVrstice = 0;
        }

        if (!vrsticaPrazna) {
            // v vrstico dodamo presledek ...
            dodajVVrstico(vrstica, dolzinaVrstice, " ");  // ali strcat(...)
            dolzinaVrstice++;
        }
        // ... in prebrano besedo
        dodajVVrstico(vrstica, dolzinaVrstice, beseda);
        dolzinaVrstice += dolzinaBesede;
        vrsticaPrazna = false;
    }

    // izpišemo, kar je še treba izpisati
    if (!vrsticaPrazna) {
        izpisiVrstico(vrstica, dolzinaVrstice, ciljnaDolzinaVrstice);
    }

    free(beseda);
    free(vrstica);

    return 0;


}

void bubbleSort(char **ax, int N, int *aNagrade) {

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            if (aNagrade[i] > aNagrade[j]) {
                int tempNum = aNagrade[j];
                aNagrade[j] = aNagrade[i];
                aNagrade[i] = tempNum;

                char *tempchar = ax[j];
                ax[j] = ax[i];
                ax[i] = tempchar;
                //free(tempchar); // NI TREBA DAT FREE KER JE NAVADN KAZALC IN NI BIL Z MALLOC!!!!!!

            }
        }
    }

}

void insertionSort(char **ax, int N, int *aNagrade) {
    for (int i = 0; i < N; ++i) {
        int x = aNagrade[i];
        char *tempChar = ax[i];
        int j = i;
        while (j > 0 && aNagrade[j - 1] < x) {
            aNagrade[j] = aNagrade[j - 1];
            ax[j] = ax[j - 1];

            j--;
        }
        aNagrade[j] = x;
        ax[j] = tempChar;
    }
}


void releaseVaje05_b(char **ax, int N, int *aNagrade) {
    for (int i = 0; i < N; ++i) {
        free(ax[i]);
    }
    free(ax);

    free(aNagrade);
}

void vaje5_bPart_bralna_znacka() {

    int MAX_STR_SIZE = 16 + 1;

    int n, k; //k je stevilo nagrad ki jih prikazemo
    scanf("%d%d", &n, &k);

    char **ax;
    ax = (char **) malloc(n * sizeof(char *));// ax kaze na 1d tabelo--tuki not so kazalci na char
    for (int i = 0; i < n; ++i) {

        ax[i] = (char *) malloc(MAX_STR_SIZE *
                                sizeof(char));//tukaj ni nujno da je N ampak je lahko druge dolzine... Se vc.. vsaka dolzina teh tabel ie lah drgacna
        scanf("%s", ax[i]); //preberemo string
    }

    int *aNagrade;
    aNagrade = (int *) malloc(n * sizeof(int)); //za nagrade storat
    for (int i = 0; i < n; ++i) {
        scanf("%d", &aNagrade[i]); //PAZI TO!!!!!!!!!!!!!!!!!!!!!!
    }


    //bubbleSort(ax, n, aNagrade);
    insertionSort(ax, n, aNagrade);

    int stPodeljenihNagrad = n < k ? n : k;
    int i = 0;       // indeks učenca
    int mesto = 1;   // trenutno mesto (ni nujno enako i + 1, saj lahko imam več istouvrščenih učencev)
    while (mesto <= stPodeljenihNagrad) {
        printf("%d. %s (%d)\n", mesto, ax[i], aNagrade[i]);
        i++;
        if (i >= n || aNagrade[i] != aNagrade[i - 1]) {
            mesto = i + 1;
        }
    }


    releaseVaje05_b(ax, n, aNagrade);

}

int vaje05() {
    sredinskaPoravnava();

    //vaje5_bPart_bralna_znacka();

    return 0;
}