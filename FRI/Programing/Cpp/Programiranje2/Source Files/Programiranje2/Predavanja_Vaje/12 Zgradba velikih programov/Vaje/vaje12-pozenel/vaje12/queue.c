#include <stdio.h>
#include <stdlib.h>
#include "queue.h"

//
// Queue functions
//

Queue *createQueue(void *element) {
    Queue *q = (Queue *) malloc(sizeof(Queue));
    q->data = element;
    q->next = NULL;
    return q;
}

Queue *add(Queue *queue, void *element) {
    Queue *nov = createQueue(element);
    if (queue == NULL)
        return nov;
    Queue *tmp = queue;
    while (tmp->next != NULL)
        tmp = tmp->next;

    tmp->next = nov;
    return queue;
}

Queue *dequeue(Queue *queue, void **element) {
    if (queue == NULL) {
        printf("[Napaka | dequeue]: vrsta je prazna.\n");
        return NULL;
    }
    *element = queue->data;
    queue = queue->next;
    return queue;
}

int empty(Queue *queue) {
    return (queue == NULL) ? 1 : 0;
}