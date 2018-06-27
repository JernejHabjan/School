
#include <stdio.h>
#include <stdlib.h>
/*

 ############## FILE 1 #################
#include <stdio.h>
#include <stdlib.h>
#include "kopicoid.h"

int main() {
    int stOperacij;
    scanf("%d", &stOperacij);

    char operacija[8];
    int vrednost;
    kopica K = NULL;
    for (int i = 0; i < stOperacij; i++) {
        scanf("%s", operacija);
        switch (operacija[0]) {
            case 'i':
                izpisi(K);
                printf("\n");
                break;
            case 'd':
                scanf("%d", &vrednost);
                dodaj(&K, vrednost);
                break;
            case 'o':
                odvzemi(&K);
                break;
        }
    }

    return 0;
}


 ############## FILE 2 #################

#ifndef _KOPICOID_H
#define _KOPICOID_H

typedef struct _kopica {
    int value;
    struct _kopica *left;
    struct _kopica *right;
} *kopica;

void dodaj(kopica *a, int v);

int odvzemi(kopica *a);

void izpisi(kopica a);

#endif



 ############## FILE 3 #################

#include "kopicoid.h"

kopica zdruzi(kopica a, kopica b) {
    if (a == NULL) return b;
    if (b == NULL) return a;

    if (a->value <= b->value) {
        kopica La = a->left;
        kopica Ra = a->right;

        a->right = La;
        a->left = zdruzi(Ra, b);

        return a;
    } else {
        return zdruzi(b, a);
    }
}

void dodaj(kopica *a, int v) {
    kopica b = malloc(sizeof(struct _kopica));
    b->value = v;
    b->left = NULL;
    b->right = NULL;

    *a = zdruzi(*a, b);
}

int odvzemi(kopica *a) {
    if (*a == NULL) {
        return -1;
    }

    kopica tmp = *a;
    int val = tmp->value;
    *a = zdruzi((*a)->left, (*a)->right);
    free(tmp);
    return val;
}

void izpisi(kopica a) {
    if (a == NULL) {
        printf("/");
    } else {
        printf("%d[", a->value);
        izpisi(a->left);
        printf(", ");
        izpisi(a->right);
        printf("]");
    }
}
*/
int podmnozice();
int vaje12() {
    podmnozice();
    //parser(); //// 13 VAJE - parser za mp3

    return 0;
}

///////////// PODMNOZICE /////////////
void izpisi(int *tabela, int stEle){
    for(int i = 0; i < stEle; i++){
        if(i>0){
            printf(" ");
        }printf("%d", tabela[i]);
    }printf("\n");

}

void podmnoziceF(int n, int k, int globina, int *zaporedje){
    if(globina > k) return;
    if(globina > 0){izpisi(zaporedje, globina);}
    int prvo = (globina == 0) ? 1: (zaporedje[globina-1]+1);
    for(int i = prvo; i <= k; i++){
        zaporedje[globina] = i;
        podmnoziceF(n, k, globina+1, zaporedje);
    }
}

int podmnozice(){
    int n, k;
    scanf("%d %d", &n, &k);
    int *zaporedje = malloc(k * sizeof(int));

    podmnoziceF(n, k, 0, zaporedje);
    free(zaporedje);
    return 0;
}

