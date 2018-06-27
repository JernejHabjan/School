//
// Created by Jernej Habjan on 9. 03. 2017.
//

#include <stdio.h>
#include <stdlib.h>

typedef struct Snake Snake;
typedef struct World World;

struct Snake {

    int *s_x;
    int *s_y;
    int dir;
    int len;
};
struct World {

    int *pos_x;
    int *pos_y;
    int *obj_type;
};


void move(Snake *sPtr, int action) {
    if (action == 1) { //podaljsamo rep
        sPtr->s_x[sPtr->len] = sPtr->s_x[sPtr->len - 1];
        sPtr->s_y[sPtr->len] = sPtr->s_y[sPtr->len - 1];

    }


    if (sPtr->len > 1) {
        //premaknemo vse
        for (int j = sPtr->len - 1; j > 0; j--) { //MOVE SNAKE
            sPtr->s_x[j] = sPtr->s_x[j - 1];
            sPtr->s_y[j] = sPtr->s_y[j - 1];
        }

    }




    //premaknemo glavo
    switch (sPtr->dir) {
        case 1: //up
            sPtr->s_y[0] += 1;
            break;
        case 2: //right
            sPtr->s_x[0] += 1;
            break;
        case 3: //down
            sPtr->s_y[0] += 1;
            break;
        case 4: //left
            sPtr->s_x[0] -= 1;
            break;
        default:
            printf("ERROR READING DIRECTION");
            break;
    }
    if (action == 1) {

        sPtr->len += 1; //povecamo dolzino pol da ne premaknemo zadnjega ele cej podalsana

    }

}


int checkAction(Snake *sPtr, World *wPtr, int obj_num) {

    for (int i = 0; i < obj_num; i++) {


        if (sPtr->s_x[0] == wPtr->pos_x[i] && sPtr->s_y[0] == wPtr->pos_y[i]) { //intercepted an object



            switch (wPtr->obj_type[i]) {
                case 1: //sadez

                    return 1;
                case 2: //zavijlevo
                    sPtr->dir -= 1;
                    if (sPtr->dir == 0) sPtr->dir = 4;
                    return 0;
                case 3: //zavij desno
                    sPtr->dir += 1;
                    if (sPtr->dir == 5) sPtr->dir = 1;
                    return 0;
                default:
                    printf("ERROR UNKNOWN ACTION");
                    return 0;
            }
        }
    }
    return 0;
}

int checkCollision(Snake *sPtr) {

    for (int i = 1; i < sPtr->len; i++) {
        if (sPtr->s_x[0] == sPtr->s_x[i] && sPtr->s_y[0] == sPtr->s_x[i]) {
            return 1;
        }
    }
    return 0;


}

int init(Snake *sPtr, World *wPtr, int obj_num) {
    wPtr->pos_x = (int *) malloc(obj_num * sizeof(int));
    wPtr->pos_y = (int *) malloc(obj_num * sizeof(int));
    wPtr->obj_type = (int *) malloc(obj_num * sizeof(int));

    if (wPtr->pos_x == NULL || wPtr->pos_y == NULL || wPtr->obj_type == NULL)
        return 0;

    for (int i = 0; i < obj_num; ++i) {
        scanf("%d%d%d", &wPtr->pos_x[i], &wPtr->pos_y[i], &wPtr->obj_type[i]);

    }

    //SNAKE
    sPtr->s_x = (int *) malloc(1000 * sizeof(int)); //max len kace 1000
    sPtr->s_y = (int *) malloc(1000 * sizeof(int));
    sPtr->dir = 1;
    sPtr->len = 1;

    if (sPtr->s_x == NULL || sPtr->s_y == NULL)
        return 0;

    return 1;

}

void releaseDN03(Snake *sPtr, World *wPtr) {
    free(sPtr->s_x);
    free(sPtr->s_y);

    free(wPtr->pos_y);
    free(wPtr->pos_x);
    free(wPtr->obj_type);
}


int domaca03() {

    int obj_num;
    scanf("%d", &obj_num);


    struct Snake s;
    struct World w;

    struct Snake *sPtr = &s;
    struct World *wPtr = &w;


    if (!init(sPtr, wPtr, obj_num)) {
        printf("COULD NOT ALLOCATE MEMORY... EXITING");
        return 1;
    }


    int num_iter1;
    scanf("%d", &num_iter1);


    for (int i = 0; i < num_iter1; i++) {


        int action = checkAction(sPtr, wPtr, obj_num);


        move(sPtr, action);
        if (checkCollision(sPtr)) { //if head collides
            printf("%d %d %d\n", sPtr->len, sPtr->s_x[0], sPtr->s_y[0]);
            releaseDN03(sPtr, wPtr);
            return 0;
        }

    }

    printf("%d %d %d\n", sPtr->len, sPtr->s_x[0], sPtr->s_y[0]);

    releaseDN03(sPtr, wPtr);
    return 0;
}