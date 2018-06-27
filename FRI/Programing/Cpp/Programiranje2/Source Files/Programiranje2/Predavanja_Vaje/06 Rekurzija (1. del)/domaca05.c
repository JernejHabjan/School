//
// Created by Jernej Habjan on 9. 03. 2017.
//
#include <stdlib.h>
#include <stdio.h>

typedef struct plosca {
    int width;
    int height;

    int count;
} plosca, *pptr;

int domaca05() {

    int n;
    scanf("%d", &n);

    //init n potencialnih plosc
    pptr *plosce = malloc(n * sizeof(pptr));
    for (int i = 0; i < n; i++) {
        plosce[i] = malloc(sizeof(plosca));
        plosce[i]->width = 100000;
        plosce[i]->height = 100000;
        plosce[i]->count = 0;
    }

    for (int i = 0; i < n; i++) {
        int a, b;
        scanf("%d %d", &a, &b);

        //gremo skozi vse plosce
        for (int j = 0; j < n; j++) {

            //ce je trenutna sirina manjsa in trenutna visina manjsa jo damo gor
            if (a < plosce[j]->width && b < plosce[j]->height) {
                plosce[j]->width = a;
                plosce[j]->height = b;
                plosce[j]->count++;
            }
        }
    }

    int max = 0;
    for (int i = 0; i < n; i++) {
        if (plosce[i]->count > max) {
            max = plosce[i]->count;
        }

    }
    printf("%d\n", max);

    //free plosce
    for (int i = 0; i < n; i++) {
        free(plosce[i]);
    }
    free(plosce);

    return 0;

}
