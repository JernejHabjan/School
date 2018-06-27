//
// Created by Jernej Habjan on 2. 03. 2017.
//

#include <stdio.h>

int vsotaPoModulu() {
    int a = 0;
    int b = 0;
    scanf("%d %d", &a, &b);

    int modul = 10;

    return (a + b) % modul;

}

int enakaStevila() {

    int sameNumbers = 1;
    int firstNum = 0;

    scanf("%d", &firstNum);

    int first = 0;
    for (int i = 0; i < firstNum; i++) {
        int curr = 0;
        scanf("%d", &curr);
        if (i == 0) {
            first = curr;
        } else {
            if (curr != first) {
                sameNumbers = 0;
            }
        }
    }
    return sameNumbers;

}


int drugoNajvecje() {

    int firstNum = 0;

    int max = 0;
    int secMax = 0;

    scanf("%d", &firstNum);


    for (int i = 0; i < firstNum; i++) {
        int num = 0;
        scanf("%d", &num);

        if (num == max) {
            secMax = num;
        }

        if (num > max) {
            secMax = max;
            max = num;

        }
        if (num > secMax && num < max) {
            secMax = num;
        }

    }
    return secMax;

}

int vaje01() {
    //printf("vsota: %d", vsotaPoModulu());
    //printf("enaka: %d", enakaStevila());
    //printf("drugoMax: %d", drugoNajvecje());
    return 0;
}