
#include <stdio.h>
#include <stdlib.h>


//////// 1 naloga

typedef struct _node_1 {

    int stevilo;
    struct _node_1 (*tabela[5]);
} node_1, *nodePtr_1;


/////// 2 naloga
typedef struct _node {
    int value;
    struct _node *next;


} node, *nodePtr;

void loopify(node *list) {

    if (list == NULL) return;

    nodePtr temp = list;
    //find last


    while (temp != NULL && temp->next != NULL)temp = temp->next;
    nodePtr zadnji = temp;

    //poisci prvega z isto vrednostjo
    temp = list;
    while (temp->next != NULL) {
        if (temp->next->value == zadnji->value) {
            zadnji->next = temp->next;
            break;
        }
        temp = temp->next;
    }

}

//////// 3 naloga
typedef struct _heap {
    int value;
    struct _heap *left;
    struct _heap *right;
} heap;

int get_sum(FILE *f, heap *h) {
    if (h == NULL) return 0;
    int sum_l = get_sum(f, h->left);
    int sum_r = get_sum(f, h->right);
    fprintf(f, "%d\n", sum_l + sum_r);
    return sum_l + sum_r + h->value;
}

void sums(char *f, heap *h) {
    FILE *file;
    if (f == NULL) file = stdout;
    else {
        file = fopen(f, "w");
        if (file == NULL) {
            fprintf(stderr, "cannot open write file");
            exit(1);
        }
    }
    get_sum(file, h);
}

int kolokvij_2(){

    //loopify();
    //sums();

    return 0;
}