//
// Created by Jernej Habjan on 9. 03. 2017.
//
#include <stdio.h>

void kodiraj(int n) {
    int prevSt;
    int count = 1;
    scanf("%d", &prevSt);

    for (int i = 0; i < n - 1; i++) {
        int currSt;
        scanf("%d", &currSt);
        if (prevSt != currSt) {
            printf("%d %d ", count, prevSt);
            count = 0;
            prevSt = currSt;

        }count++;
    }printf("%d %d ", count, prevSt);
}

void odkodiraj(int n) {
    for (int i = 0; i < n; i++) {
        int krat, st;
        scanf("%d %d", &krat, &st);
        for (int j = 0; j < krat; j++) printf("%d ", st);
    }
}

int domaca02() {
    int u, n;
    scanf("%d %d", &u, &n);
    if (u == 1) kodiraj(n);
    else if (u == 2) odkodiraj(n);
    return 0;
}
