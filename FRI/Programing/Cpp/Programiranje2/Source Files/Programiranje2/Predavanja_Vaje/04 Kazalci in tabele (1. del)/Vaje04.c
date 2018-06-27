//
// Created by Jernej Habjan on 10. 03. 2017.
//

#include <stdio.h>
#include <limits.h>

#define MAKS_DOLZINA 1000000

typedef long long llong;   // sedaj bom lahko namesto 'long long' pisal kar 'llong'

int zaporedje[MAKS_DOLZINA];

void izpisi_zaporedje(int dolzina) {


    for (int i = 0; i < dolzina; i++) {
        scanf("%d", &zaporedje[i]);
    }

    // llong najVsota = zaporedje[0];
    llong najVsota = LLONG_MIN;  // doslej najveÄŤja vsota
    int najZacetek = -1;       // indeks zaÄŤetka doslej najboljĹˇega podzaporedja
    int najDolzina = -1;       // dolĹľina doslej najboljĹˇega podzaporedja

    for (int zacetek = 0; zacetek < dolzina; zacetek++) {
        llong vsota = 0;
        for (int konec = zacetek; konec < dolzina; konec++) {
            vsota += zaporedje[konec];
            if (vsota > najVsota) {
                najVsota = vsota;
                najZacetek = zacetek;
                najDolzina = konec - zacetek + 1;
            }
        }
    }

    printf("%d %d %lld\n", najZacetek, najDolzina, najVsota);

}

void improved(llong dolzina) {
    // vsota elementov od zadnjega "preloma" (točke, ko je tekoča vsota padla
    // pod 0) do trenutnega elementa
    llong tekocaVsota = 0;

    // število elementov, ki tvorijo trenutno tekočo vsoto
    // (tj. število elementov od zadnjega "preloma" do trenutnega elementa)
    llong tekocaDolzina = 0;

    int najZacetek = 0;
    llong najVsota = LLONG_MIN;
    int najDolzina = 0;

    for (int i = 0; i < dolzina; i++) {
        int element;
        scanf("%d", &element);

        if (tekocaVsota < 0) {  // prelom!
            tekocaVsota = 0;
            tekocaDolzina = 0;
        }

        tekocaVsota += element;
        tekocaDolzina++;

        if (tekocaVsota > najVsota) {
            najVsota = tekocaVsota;
            najDolzina = tekocaDolzina;
            najZacetek = i - najDolzina + 1;
        }
    }

    printf("%d %d %lld\n", najZacetek, najDolzina, najVsota);
}

void najvecjePodzaporedje() {

    // int zaporedje[MAKS_DOLZINA];  // bog ne daj!

    int dolzina;
    scanf("%d", &dolzina);

    //izpisi_zaporedje(dolzina);
    improved(dolzina);
}

int vaje04() {

    najvecjePodzaporedje();

    return 0;
}