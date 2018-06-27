
/// 1. naloga

typedef struct _node1 {
    int stevilo;
    struct _node1 (*tabela[5]);
} node1;

/// 2. naloga


typedef struct _node {
    int value;
    struct _node *next;
} node;

#include <stdlib.h>

#include <stdio.h>



node *filter(int max, node *list) {

    node *temp = list;
    if (temp == NULL) return temp;

    while (temp->next != NULL) {

        if (temp->next->value > max) {
            //printf("%d",temp->next->value);
            temp->next = temp->next->next;
        }

        if(temp->next != NULL)
            temp = temp->next;
    }
    if(list->value > max){
        return list->next;
    }

    return list;
}


void add_node(int value, node *first) {
    node *tmp = first;
    if (first == NULL) return;
    while (tmp->next != NULL) {
        tmp = tmp->next;
    }
    node *new = malloc(sizeof(node));
    new->value = value;
    tmp->next = new;

}

void print_nodes(node *first) {
    node *tmp = first;
    while (tmp != NULL) {
        printf("%d\n", tmp->value);
        tmp = tmp->next;
    }
}


int filter_fun() {

    node *first = malloc(sizeof(node));
    first->value = 77;

    add_node(3, first);
    add_node(5, first);
    add_node(6, first);
    print_nodes(first);
    printf("\n");
    first = filter(5, first);
    print_nodes(first);
    return 0;
}

/// 3 naloga
typedef struct _node3 { int value;
    struct _node3 *left, *right;
} node3;

void dump_heap (FILE *file, struct _node3 *heap){
    static int st_vozlisc = 0;


    if(heap == NULL) return;
    if(heap->left != NULL){
        dump_heap(file, heap->left);
    }if(heap->right != NULL){
        dump_heap(file, heap->right);
    }

    st_vozlisc++;


    if(st_vozlisc >8){
        fprintf(file, "%d\n", heap->value);
    }
    return;
}


int kolokvij_0_1(){
    filter_fun();
    //dump_heap();

    return 0;
}