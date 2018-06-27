//
// Created by Jernej Habjan on 21. 05. 2017.
//

/// DUAL LINKED LISTS


#include <stdio.h>
#include <stdlib.h>

typedef struct DualLinkedLists {
    int value;
    struct DualLinkedLists *next;
    struct DualLinkedLists *prev;
} dl, *dptr;


void add(dptr list, int value) {
    //get last
    dptr temp = list;
    while (temp->next != NULL && temp->next->value < value)temp = temp->next;


    //insert:
    if (temp->next == NULL || temp->next->value != value) {
        dptr new = (dptr) malloc(sizeof(dl));
        new->value = value;
        new->next = temp->next;
        new->prev = temp;

        temp->next = new;
    }


}

void print_dll(dptr list) {
    dptr temp = list->next;
    while (temp != NULL) {
        printf("%d ", temp->value);

        temp = temp->next;
    }
    printf("\n");
}

int  delete_dll(dptr list, int value) {
    //najdi prejsnega:
    dptr temp = list;
    while (temp->next != NULL) {
        if (temp->next->value == value) break;
        temp = temp->next;
    }

    if (temp->next == NULL) { //nismo nasli vrednosti... return
        return 1;
    }


    dptr prejsni = temp;
    dptr trenutni = temp->next;
    dptr naslednji = temp->next->next;

    prejsni->next = naslednji;
    naslednji->prev = prejsni;
    free(trenutni);
    return 0;

}


int dualLinkedLists() {

    dptr sentinel = (dptr) malloc(sizeof(dl));
    sentinel->value = -1;
    sentinel->next = NULL;
    sentinel->prev = NULL;

    add(sentinel, 31);
    add(sentinel, 11);
    add(sentinel, 24);
    add(sentinel, 53);

    print_dll(sentinel);

    delete_dll(sentinel, 24);
    delete_dll(sentinel, 2222);


    print_dll(sentinel);

    return 0;
}