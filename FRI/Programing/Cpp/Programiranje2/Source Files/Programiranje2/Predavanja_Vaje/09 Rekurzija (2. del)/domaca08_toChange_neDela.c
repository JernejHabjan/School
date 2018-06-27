#include <stdio.h>

typedef struct _node {
    int value;
    struct _node *l;
    struct _node *r;
} node;

/*
heap *Heap = NULL;

int merge (heap *l, heap *r){
    if (r == NULL) {
        return l;
    }
    if (l == NULL) {
        return r;
    }
    heap *a, *b;
    if (l-> value > r-> value){
        a = l;
        b = r;
    }
    else {
        a = r;
        b = l;
    }
    // heap *left = a->l;
    heap *right = a-> r;
    a-> l = merge(right, b);
}
int push (int n){
    heap *tmp = malloc(sizeof(heap));
    tmp-> value = a;
    tmp -> l = NULL;
    tmp -> r = NULL;
    merge(*Heap, *tmp);
}
int pop (){
    heap *tmp = Heap;
    Heap = merge(Heap -> l, Heap -> r);
    int v = tmp -> value;
    free(tmp);
    return v;
}
void print (heap *Heap) {
    if (Heap != NULL) {
        print(Heap -> l);
        printf("%d\n", Heap -> value);
        print(Heap -> r);
    }

}

 */
int domaca08() {
    int n;
    //while(scanf("%d", &n) 1=EOF);
    while (scanf("%d", &n));
    //push(n);
    // print;
    return 0;
}