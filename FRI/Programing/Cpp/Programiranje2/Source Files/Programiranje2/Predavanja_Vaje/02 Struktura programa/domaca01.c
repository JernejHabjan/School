//
// Created by Jernej Habjan on 7. 03. 2017.
//
#include <stdio.h>

int reverseInt(int a) {
    int b = a;
    int d = 0;
    int i = 0;
    while (b != 0) {
        if (i != 0)
            d *= 10;
        d += b % 10;
        i++;
        b = b / 10;

    }return d;
}

int lenDN1(int b) {
    int a = b;
    int l = 0;
    while (a != 0) {
        a /= 10;
        l++;

    }return l;
}

int domaca01() {
    int a, b;

    scanf("%d %d", &a, &b);
    int c = reverseInt(a);
    int d = reverseInt(b);

    int lend = lenDN1(d);

    for (int i = 0; i < lend; i++) {
        int digit = d % 10;
        d = d / 10;

        for (int i = 0; i < digit; i++) {
            int stevilo = c % 10;
            c = c / 10;

            printf("%d", stevilo);
        }printf("\n");
    }return 0;
}


