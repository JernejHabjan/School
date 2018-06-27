//
// Created by Jernej Habjan on 29. 03. 2017.
//
#include <stdio.h>
#include <stdlib.h>
#include <memory.h>


int fib(int a) {
    if (a == 0) {
        return 0;
    }
    if (a == 1) {
        return 1;
    }

    return fib(a - 1) + fib(a - 2);
}

int getNumDeljitelji(int n) {
    int delj = 0;
    for (int i = 2; i < n; ++i) {
        if (n % i == 0)
            delj++;
    }
    return delj;
}


int bubblesort(int *ax, int n) {

    for (int i = 0; i < n; ++i) {

        for (int j = 0; j < n; ++j) {

            if (ax[i] < ax[j]) {
                int temp = ax[i];
                ax[i] = ax[j];
                ax[j] = temp;
            }

        };
    }


}


int fibbonacci() {////-todo!!!!!!!!!!!
    int fibNum = fib(5);

    int a = getNumDeljitelji(4);

    printf("%d", a);
}

int sortArrayWhileInserting() { //-todo!!!!!!!!!!!

    int prebranih = 0;

    int *ax;
    int *ax1;

    int n = 1;
    while (n != 0) {

        scanf("%d", &n);

        if (n != 0) {
            prebranih++;
            printf("lol");


            printf("%zu", sizeof(ax));

            memcpy(ax, ax1, sizeof(ax));
            ax = malloc(prebranih * sizeof(int));
            free(ax);

            // bubblesort(ax1, );

        }

    }
}

int kolokvij_1_0() {

    fibbonacci();
    sortArrayWhileInserting();


    return 0;
}


