#include <stdio.h>
#include <stdlib.h>

typedef struct _heap {
    int value;
    struct _heap *left;
    struct _heap *right;
} heap;

heap *HEAP = NULL;

heap *merge(heap *heap1, heap *heap2) {
    if (!heap1)
        return heap2;

    if (!heap2)
        return heap1;

    heap *a, *b;
    if (heap1->value < heap2->value) {
        a = heap1;
        b = heap2;
    } else {
        a = heap2;
        b = heap1;
    }

    heap *l = a->left, *r = a->right;

    a->right = l;
    a->left = merge(r, b);
    return a;
}

void push(int a) {
    heap *tmp = (heap *) malloc(sizeof(heap));
    tmp->value = a;
    tmp->left = NULL;
    tmp->right = NULL;

    HEAP = merge(HEAP, tmp);
}

int pop() {
    heap *tmp = HEAP;
    HEAP = merge(HEAP->left, HEAP->right);
    int v = tmp->value;
    free(tmp);
    return v;
}


int main() {
    int n;
    while (scanf("%d", &n) != EOF) {
        push(n);
    }

    while (HEAP) {
        printf("%d ", pop());
    }
    printf("\n");
    return 0;
}
