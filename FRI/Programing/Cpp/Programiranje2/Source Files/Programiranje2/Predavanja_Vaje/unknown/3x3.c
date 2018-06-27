#include <stdio.h>
#include <stdlib.h>

int power(int power);

int bt[10000];
int at[10000];
int bt2[10000];
int at2[10000];

int main() {
    int a, b, a2, b2;

    scanf("%d %d", &a, &b);
    a2 = a;
    b2 = b;
    int astevec = 0;
    int bstevec = 0;
    int m = 0;
    int n = 0;
    int k = 0;
    while (a2 > 0) {
        at2[astevec] = a2 % 10;
        a2 /= 10;
        astevec++;
    }
    for (int i = 0; i < astevec; i++) {
        at[i] = at2[astevec - (i + 1)];
    }
    while (b2 > 0) {
        bt2[bstevec] = b2 % 10;
        b2 /= 10;
        bstevec++;
    }
    for (int i = 0; i < bstevec; i++) {
        bt[i] = bt2[bstevec - (i + 1)];
    }
    for (int i = 0; i < (astevec); i++) {
        k = 0;
        for (int j = 0; j < bt[m]; j++) {
            if (!(j + 1 < bt[m] && at[i] == 0 && k != 1)) {
                printf("%d", at[i]);
                k = 1;
            }
            i++;
        }
        printf("\n");
        m++;
        i--;
    }
    return 0;
}