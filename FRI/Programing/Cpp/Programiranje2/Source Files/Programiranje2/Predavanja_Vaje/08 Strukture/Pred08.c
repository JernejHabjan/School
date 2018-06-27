
#include <stdio.h>
#include <stdlib.h>


///Strukture


//Nemormo met funkcij

struct {
    double rc;
    double ic;
} c1;//c je spremenljivka
//Pri temu ce bi hotu se 1 spremenljivko bi mogu se 1x to vse napisat

struct complex {
    double rc;
    double ic;
};   //Pazi dvopicje
//struct compex c2;
//struct complex a2;

typedef struct complex1 { //opis strukture complex
    double rc;
    double ic;
} complex1; //definicija tipa complex
complex1 c3;
complex1 a3;

typedef int *ptr_int;// ptr_int je ime podat tipa

void example_complex() {
    complex1 c = {1.0, 2.0};
    printf("%lf %lf \n", c.rc, c.ic); //long float je lf
//Primer inicialzacije ki je tecno
    complex1 *c2 = (complex1 *) malloc(sizeof(complex1));
//Velikost structa je tok kokr je notr podatkov.. ce sta not 2 inta.. pol je 8B.. toj primer

    (*c2).rc = 1.2; //kazem na tisti del pom kamor kaze
    (*c2).rc = 3.9;
    printf("%lf %lf \n", (*c2).rc, (*c2).ic);
//Lahko tudi namesto (*c) //kot struktura
    c2->rc = 1.2; //uporabimo kot kazalec na strukturo
    c2->ic = 4.8;

    complex1 c3 = {1.2, 1.4};
    complex1 *d = &c;
    (*d).rc = 2.0;
//Ali
    d->rc = 2.0;

}

//Grd example
struct complex3 {
    double a;
    double b;
    char name[1000];//dont do dis pls
}; //sizeof je 1016;

void print1(struct complex3 c);//v zgornjem primeru posljemo vseh 1016B
void print2(struct complex3 *c); //posljemo 4B;

//Povezani seznami
typedef struct node {
    int value;
    struct node *next;
} node, *list;//node je tip ki je enak strukturi. //List je pa tip ki je enak kazalcu na strukturo
//Podobno:
//typedef int mojint, *mojkazalecint;

////////////Funkcije/////////////////////


void print(list l) {//funkcija sprejme kazalec na prvi element seznama, vsi ostali ele sledijo
    while (l != NULL) {
        printf("%d ", l->value);
        l = l->next;
    }
    printf("\n");
}//Endfun

void print_rek(list l) {//rekurzivna
    if (l == NULL) printf("\n");
    else {
        printf("%d ", l->value);
        print_rek(l->next);
    }
}

list insertStart(int value, list l) {//prejmemo value in zdajšnji​ zaceten ptr
    list newL = (list) malloc(sizeof(node));//paziiiiiiii
    newL->value = value;
    newL->next = l;
    return newL;
}

list insertEnd(int value, list l) {
    list newL = (list) malloc(sizeof(node));
    newL->value = value;
    newL->next = NULL;
    if (l == NULL) return newL;
    list last = l; //last je temp spremenljivka
    while (last->next != NULL) last = last->next;
    last->next = newL;
    return l;
}

list insertEnd2(int value, list l) {//rekurzivno
    if (l == NULL) {
        list newL = (list) malloc(sizeof(node));
        newL->value = value;
        newL->next = NULL;
        return newL;
    }
    l->next = insertEnd2(value, l->next);
    return l;
}

list findFirst(int v, list l) {
    while (l != NULL) {
        if (l->value == v) return l;
        l = l->next;
    }
    return NULL;//ce se while zanka iztece
}

list findFirst2(int v, list l) {//rekurzivna
    if (l == NULL) return NULL;
    if (l->value == v) return l;
    return findFirst2(v, l->next);
}

//DN iterat in rekurz za zadnjega od vseh enakih
//Npr 56345653 --vrnemo predzadnjo 5ko

///Naloge..1. vrnes daljsi seznam
//2. Ali so vrednosti na indeksih enaki med dvema linked listoma
//3. Vrni seznam v obratnem vr redu(a. Z novim, b. Uporabit starih skatl)



///Kolokvij 2: linked list, pa datoteke - NAVODILA ZA 2. KOLOKVIJ


int pred08() {

    return 0;
}