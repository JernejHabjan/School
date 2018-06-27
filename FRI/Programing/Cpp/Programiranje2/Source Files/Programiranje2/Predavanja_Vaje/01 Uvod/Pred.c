//
// Created by Jernej Habjan on 2. 03. 2017.
//

#include <stdio.h>

/* ## predavanje 24.02.2017 ## */

void printNumChars() {
    int count = 0;
    while (getchar() != EOF) { //EOF V KONZOLO VPISES Z ctrl+Z
        ++count;
    }
    printf("%d\n", count);
    //java ima exceptione, tukaj ne
}

void printBitNumbers(int n) {
    for (int i = 0; i < (1 << n); i++) { //to je od 0 do 2^n - 1  --ampak preskakuje... gre v spodnjem zaporedju
        //		  dvoj    bin
        //1<<0    1       1
        //1<<1    10      2
        //1<<2    100     4

        for (int j = 0; j < n; j++) { //gremo po tem bitu
            if ((i & (1 << n - 1 - j)) == 0) // 1 za j mest -> prizgan j-ti bit z leve
                //"n - 1" predstavla da gleda sprva cisto leve bite -> ker je izpis obrnjen s printom
                printf("0");
            else
                printf("1");
        }
        printf("\n");
    }

}

int pred01() {

    printNumChars();
    printBitNumbers(0);
    return 0;
}