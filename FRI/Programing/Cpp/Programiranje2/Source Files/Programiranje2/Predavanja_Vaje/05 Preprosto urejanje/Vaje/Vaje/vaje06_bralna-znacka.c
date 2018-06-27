#include <stdio.h>
#include <stdlib.h>

const int NAJDALJSE_IME = 16;

int vaje06_old() {
    int n, k;
    scanf("%d %d", &n, &k);

    char *imena[n];
    int knjige[n];

    for (int i = 0; i < n; i++) {
        imena[i] = (char *) malloc(sizeof(char) * (NAJDALJSE_IME + 1));
        scanf("%s", imena[i]);
    }
    for (int i = 0; i < n; i++) {
        scanf("%d", &knjige[i]);
    }

    // Bubble sort
    int sorted = 0;
    while (!sorted) {
        sorted = 1;
        for (int i = 0; i < n - 1; i++) {
            if (knjige[i] < knjige[i + 1]) {
                int tmp = knjige[i];
                knjige[i] = knjige[i + 1];
                knjige[i + 1] = tmp;

                char *tmp2 = imena[i];
                imena[i] = imena[i + 1];
                imena[i + 1] = tmp2;

                sorted = 0;
            }
        }
    }

    int mesto = 0;
    int zadnji_knjig = -1;
    for (int i = 0; i < n && (i < k || knjige[i] == zadnji_knjig); i++) {
        if (knjige[i] != zadnji_knjig) {
            mesto = i + 1;
            zadnji_knjig = knjige[i];
        }
        printf("%d. %s (%d)\n", mesto, imena[i], knjige[i]);
    }

    return 0;
}
