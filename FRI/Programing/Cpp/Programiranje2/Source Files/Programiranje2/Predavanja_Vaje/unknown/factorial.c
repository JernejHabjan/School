#include <stdio.h>
#include <stdlib.h>

void preuredi();

int *a;
int *kol;

int sestej(int i) {
    a[0] += a[i];
}

int zmnozi() {
    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
            a[j] *= kol[i];
            if (a[j] > 10) {
                int ostanek = 0;
                ostanek = a[j] % 10;
                a[j + 1] += (a[j] - ostanek) / 10;
                a[j] = ostanek;
            }
        }
        preuredi();
        preuredi();
        preuredi();
    }
}

void preuredi() {
    for (int i = 0; i < 99; i++) {
        if (a[i] > 9) {
            int ostanek = 0;
            ostanek = a[i] % 10;
            a[i + 1] += (a[i] - ostanek) / 10;
            a[i] = ostanek;
        }
    }

}

void rek(int n) {
    if (n == 0) {
    } else {
        kol[n] = n - 1;
        rek(n - 1);
    }
}

int main() {
    kol = (int *) malloc(100 * sizeof(int));
    rek(99);
    a = (int *) malloc(100 * sizeof(int));
    a[0] = 1;
    for (int i = 1; i < 100; i++) {
        a[i] = 0;
    }
    zmnozi();
    preuredi();
    for (int i = 0; i < 100; i++) {
        sestej(i);
    }
    printf("%d\n", a[0]);
    return 0;
}