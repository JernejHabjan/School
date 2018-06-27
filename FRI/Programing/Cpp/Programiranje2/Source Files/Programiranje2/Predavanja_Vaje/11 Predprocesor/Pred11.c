//
// Created by Jernej Habjan on 10. 03. 2017.
//


#include <stdio.h>
#include <stdlib.h>

// naloga kopica z lani
typedef struct _heap {
    int value;
    struct _heap *left;
    struct _heap *right;
} heap;

int sums1(FILE *file, heap *ele) {
    if (ele == NULL) return 0;
    int left = sums1(file, ele->left);
    int right = sums1(file, ele->right);
    fprintf(file, "%d", left + right);
    return left + right + ele->value;
}


void izpis(char *f, heap *ele) {
    FILE *file;
    if (f == NULL) {
        file = stdout;
    } else {
        file = fopen(f, "w");
        if (file == NULL) exit(1);

    }
    sums1(file, ele);

    if (f != NULL)
        fclose(file);
    return;
}

//////Kopice nebo na kolokviju
//////End heap naloga ///////

/////Predprocesor////
//Program v datoteki main.c
//        $ gcc -o main main.c -- rezultat je izvrsliva datoteka main
//        $ ./main

//$ gcc -c main.c --//temu se rece compiling
// rezultat je prevedena datoteka main.o (na win main.obj) //program je ze v obliki strojne kode
//Ni pa recimo funkcij printf, malloc, free..

//$ gcc -o main main.o //rezultat je izvrsljiva ---temu se rece linking
// datoteka main.. ki jo lahko pozenemo


//Main.c gre skozi predprocesor in porihta vrstice ki se zacnejo s # in jih "pomece ven iz datoteke". Potem se gre to prevajati(compilat)

//Kaj lahko pocnemo s predprocesorjem

//        main.c:
//        ....
//Se 20 vrstic

//Kopira kodo iz stdio.h v tvoj file in iz stdlib.h.. nakonc je pa nasih 20 vrstic


/// Katere ukaze imam:
///  1)---#include <ime_dat> //vkljuci dat ki jo najdes na sistemskem dir.
///  2)---#include "ime_dat" //ukljuci dat. Ki jo najdes na trenutnem direktoriju

//---#define ime_makroja vrednost_makroja --od mesta definicije nadomesti ime makroja z vrednostjo makroja
#define N 100



///  3)#define STRSIZE 120
#define STRSIZE 120
char str1[STRSIZE];

#define STRSIZE 120+1 //izracuna v 121
char str2[STRSIZE];

char doublestr[STRSIZE * 2]; // v tem primeru 120 +1*2 =122!!!
//pravilno:
#define STRSIZE (120+1) //dodaj oklepaje
char str3[STRSIZE];

//Z define definiramo konstantne var

#define imeMacroja(arg1, arg2)  vrednostMakroja
///  4)#define MAX(a, b) a>b? a:b
#define MAX(a, b) a>b? a:b
intx = MAX(4, 5);
//se prevede v int x = MAX(a>b?a:b);
//Tukaj je isto problem z 2* ___
//Resitev:
#define MAX(a, b) ((a>b)? a:b)

//Kako preverit ce smo use releasal

void *mymalloc(size_t size) {
    void *ptr = malloc(size);
    fprintf(stderr, "%p, velikost: %zu", ptr, size);
    return ptr;

}

void myfree(void *ptr) {
    //fprintf(stderr, pointer, ptr)
    //free(ptr);
}


int pred11() {
    return 0;
}