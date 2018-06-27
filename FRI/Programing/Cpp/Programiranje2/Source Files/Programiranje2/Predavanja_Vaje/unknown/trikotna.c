#include <stdio.h>

int prestej(int n) {
    int stevec = 1;
    int b = n;
    while (b > 1) {
        stevec++;
        for (int i = b; i > 1; i--) {
            if (b % i == 0) {
                b--;
                // printf("%s\n", "bla");
                // printf("%d\n", stevec);
                break;
            }
        }
    }
    return stevec;
}

int main() {
    int k = 0;
    long long int m = 1;
    int h = 2;
    while (k <= 100) {
        m += h;
        h = h + 1;
        k = prestej(m);
        printf("%llu\n", m);
    }
    printf("%s\n", "oj");
    printf("%llu\n", m);

    return 0;
}