//
// Created by Jernej Habjan on 3. 03. 2017.
//


/*
 PAZI TUKAJ JE PRIMER KAKO UPORABLJATI RANDOM
 */


#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int vzemi(int preostalih, int k, int thisNumber) {

    printf("%d - %d = %d\n", preostalih, thisNumber, preostalih - thisNumber);
    preostalih -= thisNumber;

    return preostalih;
}

void nim() {
    long int seed;
    long int k;
    long int preostalih;

    scanf("%li", &seed);
    scanf("%li %li", &k, &preostalih);


    srand((unsigned int) seed); //nastavimo seed

    while (preostalih) {
        //naprej povejo stevila koliko odvzame clovek predmetov

        //poglej ce nasledn scan ni EOF

        long int thisNumber = 0;

        scanf("%li", &thisNumber);

        //human
        preostalih = vzemi((int) preostalih, (int) k, (int) thisNumber);
        if (preostalih == 0) {
            printf("RACUNALNIK\n");
            return;
        }

        //computer
        thisNumber = (rand() % k) + 1;

        preostalih = vzemi((int) preostalih, (int) k, (int) thisNumber); //dunno

        if (preostalih == 0) {
            printf("IGRALEC\n");
            return;
        }

    }


}


void pitagorejskaTrojica() {
    int a;
    int b = 0;
    int c = 0;

    int m = 0;
    int n = 0;


    scanf("%d %d", &m, &n);

    int stevilo = 0;

    if (m >= n) return;
    c = m;
    while (c <= n) {
        double l = c / sqrt(2);
        for (a = 1; a <= l; a++) {
            b = (int) sqrt(pow(c, 2) - pow(a, 2));

            if (pow(c, 2) == pow(a, 2) + pow(b, 2)) {
                stevilo++;
                break;
            }
        }
        c++;
    }
    printf("%d", stevilo);
}

int vaje02() {
    //nim();
    //pitagorejskaTrojica();
    return 0;
}