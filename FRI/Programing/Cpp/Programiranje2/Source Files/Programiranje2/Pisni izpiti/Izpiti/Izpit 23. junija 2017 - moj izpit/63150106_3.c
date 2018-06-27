#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

typedef struct bla {
    int prvi;
    int drugi;

} b, *bptr;

void izpis(b **ax, int n, int index, int *prejsneStevke, int stevkeNum) {
    if (index + 1 > n) return;

    int prvi = ax[index]->prvi;
    int drugi = ax[index]->drugi;
    for (int i = prvi; i <= drugi; i++) {
        prejsneStevke[stevkeNum] = i;

        //izpis stevk
        if (stevkeNum + 1 == n) {
            for (int j = 0; j < stevkeNum + 1; j++) {
                printf("%d", prejsneStevke[j]);

            }
            printf("\n");
        }
        izpis(ax, n, index + 1, prejsneStevke, stevkeNum + 1);
    }
}


int main() {
    int n;
    scanf("%d", &n);

    b **ax = malloc(n * sizeof(b *));
    for (int i = 0; i < n; i++) {

        int a, b;
        scanf("%d %d", &a, &b);
        ax[i] = malloc(sizeof(b));

        ax[i]->prvi = a;
        ax[i]->drugi = b;
    }

    int *prejsneStevke = calloc(n, sizeof(int));
    int stevkeNum = 0;

    //izpis:
    izpis(ax, n, 0, prejsneStevke, stevkeNum);
    return 0;
}
