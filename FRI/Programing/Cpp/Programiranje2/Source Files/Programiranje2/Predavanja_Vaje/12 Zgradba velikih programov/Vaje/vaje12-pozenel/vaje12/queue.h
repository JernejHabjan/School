//
// Queue declaration
//

typedef struct _Queue {
    void *data;
    struct _Queue *next;
} Queue;

Queue *createQueue(void *element);

Queue *add(Queue *queue, void *element);

Queue *dequeue(Queue *queue, void **element);

int empty(Queue *queue);
