//
// Created by Jernej Habjan on 7. 03. 2017.
//

#include <stdio.h>
#include <math.h>

typedef long long llong;

llong getVsota(llong stevilo) {
    llong vsota = 0;

    int i;
    for (i = 1; i < stevilo; i++) {
        if (stevilo % i == 0) {
            //printf("%i", i);
            vsota += i;
        }
    }

    return vsota;
}

void prijateljskaStevila() {
    llong stevilo;
    scanf("%lli", &stevilo);

    llong vsotaStevila = getVsota(stevilo);

    llong vsotaStevila2 = getVsota(vsotaStevila);

    if (stevilo == vsotaStevila2)
        printf("%lli ", vsotaStevila);
    else
        printf("NIMA");

}

int popolno(int n) {
    int vsota = 1;
    int meja = (int) ceil(sqrt(n));
    int i;
    for (i = 2; i < meja; i++) {
        if (n % i == 0) {
            vsota += i;
            int q = n / i;
            if (q > i)
                vsota += q;
        }
    }
    if (vsota == n)
        return 1;
    else
        return 0;
}

void popolnaStevila() {
    llong doStevila;
    scanf("%lli", &doStevila);

    int i;
    for (i = 1; i < doStevila; i++) {
        if (popolno(i))printf("%d ", i);
    }

}

int geLenZaporedje(llong num) {
    llong currNumber = num;
    int lenZaporedje = 1;

    while (currNumber != 1) {

        if (currNumber % 2 == 0) {
            currNumber /= 2;
            ++lenZaporedje;
        } else { //stevilo je liho
            currNumber = 3 * currNumber + 1;
            ++lenZaporedje;
        }
    }
    return lenZaporedje;
}

void collatzovaDomneva() {
    llong doStevila;
    scanf("%lli", &doStevila);

    llong maxNum = 0;
    llong maxZaporedje = 0;

    llong i;
    for (i = 1; i <= doStevila; ++i) {
        llong lenZaporedje = geLenZaporedje(i);
        if (lenZaporedje > maxZaporedje) {
            maxZaporedje = lenZaporedje;
            maxNum = i;
        }


    }

    printf("%lli %lli", maxNum, maxZaporedje);
}

int vaje03() {
    popolnaStevila();
    //prijateljskaStevila();
    //collatzovaDomneva();
    return 0;
}