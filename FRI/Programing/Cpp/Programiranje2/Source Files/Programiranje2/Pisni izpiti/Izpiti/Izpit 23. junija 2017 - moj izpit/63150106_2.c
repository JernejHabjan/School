#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

typedef struct skatla {
    int value;
    int isdeleted;

} crate, *crateptr;

int odstrani(crate **ax, int n, int k, int stevilo) {
    int counter = 0;
    for (int i = n - 1; i >= 0; i--) {
        counter = 0;

        int found = 0;

        for (int j = k - 1; j >= 0; j--) {
            if (stevilo == ax[i][j].value) {
                if (ax[i][j].isdeleted == 0) {
                    counter = (k - j + 1);
                    found = 1;
                    continue;
                    ax[i][j].isdeleted = 1;
                }

            }//if found -skopiraj vse nizje
        }
    }return counter;
}

int main() {
    int n, k, t;

    scanf("%d %d %d", &n, &k, &t);
    //n stevilo kupov
    //k visina
    //5 - st stevilo stevilk:

    //alllocate
    crate **ax = malloc(n * sizeof(crate *));

    for (int i = 0; i < n; i++) {
        ax[i] = malloc(k * sizeof(crate));


    }
    //zapises notr:
    for (int i = n - 1; i >= 0; i--) {
        int stevilo = (i + 1) * k - 1;


        for (int j = k - 1; j >= 0; j--) {

            crateptr st = malloc(sizeof(crate));
            st->value = stevilo;
            st->isdeleted = 0;

            ax[i][j].value = st;
            stevilo--;

        }

    }

    for (int i = n - 1; i >= 0; i--)
        for (int j = k - 1; j >= 0; j--) printf("%d ", ax[i][j]);
        printf("\n");

    int vsota = 0;

    int stevilo;
    while (scanf("%d", &stevilo) != EOF) {
        printf("%d", stevilo);
        vsota += odstrani(ax, n, k, stevilo);
    }

    printf("%d\n", vsota);
    return 0;
}
