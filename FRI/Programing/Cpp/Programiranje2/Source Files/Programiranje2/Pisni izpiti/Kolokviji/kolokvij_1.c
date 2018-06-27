//
// Created by Jernej Habjan on 10. 04. 2017.
//

#include <stdio.h>
#include <stdlib.h>

void *zgornjaTrikotnaMatrika() {
    int N;
    scanf("%d", &N);

    int **ax;
    ax = (int **) malloc(N * sizeof(int *));

    ax--;

    for (int i = 1; i < N + 1; i++) {
        ax[i]--;
        ax[i] = (int *) malloc((N - i + 1) * sizeof(int));
        if (ax[i] == NULL)
            return NULL;
    }


    for (int i = 1; i < N + 1; i++) {
        ax[i]++;
        free(ax[i]);
    }
    ax++;
    free(ax);
}

int sestevanj = 0;

int fibkol(int n) {

    if (n == 0)return 0;
    if (n == 1) return 1;
    sestevanj++;
    return fibkol(n - 1) + fibkol(n - 2);


}

void sort_bubble(int *ax, int N) {

    for (int i = 0; i < N; i++) {

        for (int j = 0; j < N; j++) {
            if (ax[i] > ax[j]) {
                int temp = ax[i];
                ax[i] = ax[j];
                ax[j] = temp;
            }


        }
    }


}


int getNumDeljitelji1(int n) {

    int delitelji = 0;
    for (int i = 2; i < n; ++i) {
        if (n % i == 0)
            delitelji++;
    }
    return delitelji;
}

void sort_kol() {


    int N;
    scanf("%d", &N);

    int *ax;
    ax = (int *) malloc(N * sizeof(int));


    for (int i = 0; i < N; i++) {
        scanf("%d", &ax[i]);
    }

    //mam prebran


    sort_bubble(ax, N);

    for (int i = 0; i < N; i++) {
        printf("%d", ax[i]);
    }

    int stevilo_index = 5;
    int completed = 0;
    int curr_index = 0;

    while (curr_index < N && completed < stevilo_index) {

        if (getNumDeljitelji1(ax[curr_index]) == 4)
            completed++;
        curr_index++;
    }


}

int kolokvij_1() {

    //zgornjaTrikotnaMatrika();
    //fibkol(5);
    //printf("%d", sestevanj);


    sort_kol();
    return 0;

}