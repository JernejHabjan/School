//
// Created by Jernej Habjan on 29. 03. 2017.
//

#include <stdio.h>
#include <stdlib.h>



//
// Created by Jernej Habjan on 10. 04. 2017.
//


float **prejmi() {

    int n;
    scanf("%d", &n);


    float *ax;
    ax = (float *) malloc(n * sizeof(float));

    for (int i = 0; i < n; i++) {

        scanf("%f", &ax[i]);
    }


    float **ax1;
    ax1 = (float **) malloc(n * sizeof(float *));


    for (int i = 0; i < n; i++) {
        float *ptr = &ax[i];
        ax1[i] = ptr;
    }

    return ax1;




    //FREE
    //CHECK IF NOT NULL


}

int stevila[1000];

int getPrime(int n) {
    for (int i = 2; i < n; i++) {
        if (n % 2 == 0) {
            return 0;
        }
    }
    return 1;
}

int getLenZaporedja(int count) {

    int longest = 0;

    for (int zacetek = 0; zacetek < count; zacetek++) {
        int tempMax = 0;
        for (int konec = zacetek + 1; konec < count; konec++) {

            if (getPrime(stevila[konec])) {
                tempMax += 1;
            } else {
                break;
            }


        }
        if (tempMax > longest)
            longest = tempMax;

    }


    return longest;
}


void prastevila() {

    int count = 0;
    while (scanf("%d", &stevila[count])) {
        if (stevila[count] == 0) {
            break;
        }

        count++;
    }

    getLenZaporedja(count);


}

int moznaPlacila[] = {200, 100, 50, 20, 10, 5, 2, 1};
int len = 8;

int numbers[1000];
int added = 0;

int ways_to_pay = 0;


void pay(int n) {

    for (int i = 0; i < len; i++) {
        if (n >= moznaPlacila[i]) {
            numbers[added] = moznaPlacila[i];
            added++;
            pay(n - moznaPlacila[i]);
        }
    }
    ways_to_pay += 1;
}

void placilo() { ///TODO

    int eur;
    scanf("%d", &eur);

    pay(eur);
}


int kolokvij_0() {
    //prejmi();
    //prastevila();
    //placilo();
    return 0;
}