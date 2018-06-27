//
// Created by Jernej Habjan on 10. 03. 2017.
//

#include <stdlib.h>
#include <stdio.h>

void narisi_meje(int **ax, int n) {
    for (int i = 0; i < n; i++) {
        int x1, y1, x2, y2;
        scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
        //oznaci presecisce
        if (y1 == y2) //oznaci horizontalo
            for (int j = x1; j <= x2; j++) ax[j][y1] = 1;
        else if (x1 == x2)  //oznaci vertikalo
            for (int j = y1; j <= y2; j++) ax[x1][j] = 1;
    }
}


int oznaci(int x, int y, int **ax, int w, int h, int podrocja_index) {
    int is = 0;
    for (int i = x; i < w; i++)
        for (int j = y; j < h; j++) {

            if (ax[i][j] == 0) {
                ax[i][j] = podrocja_index;
                is = 1;

            } else break;
        }
    if (is == 1) printf("%d %d", x, y);
    return is;

}

void detektirajMeje(int **ax, int w, int h) {
    int podrocja_index = 2;
    for (int i = 0; i < w; i++)
        for (int j = 0; j < h; j++) {
            if (ax[i][j] > 0) continue; //je ze oznacena -> vecji od 1 ali je pa meja -> 1
            if (oznaci(i, j, ax, w, h, podrocja_index)) podrocja_index++;
        }

    printf("%d\n", podrocja_index - 2);

}

void izpisiMatriko(int **ax, int w, int h) {
    for (int i = 0; i < w; i++)
        for (int j = 0; j < h; j++) printf("%d", ax[i][j]);
    printf("\n");
}


int domaca06() {
    int w, h;
    scanf("%d %d", &w, &h);

    int **ax = malloc(w * sizeof(int *));
    for (int i = 0; i < h; i++) ax[i] = calloc(h, sizeof(int));

    int n;
    scanf("%d", &n);
    narisi_meje(ax, n);
    detektirajMeje(ax, w, h);
    //izpisiMatriko(ax, w, h);

    //free
    for (int i = 0; i < h; i++) free(ax[i]);
    free(ax);
    return 0;
}
