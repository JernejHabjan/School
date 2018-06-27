#include "stdio.h"

int main() {
    int n;
    scanf("%d", &n);
    int abc = 0;
    for (int i = 0; i <= n / 200; ++i) {
        for (int j = 0; j <= n / 100; ++j) {
            for (int k = 0; k <= n / 50; ++k) {
                for (int l = 0; l <= n / 20; ++l) {
                    for (int m = 0; m <= n / 10; ++m) {
                        for (int b = 0; b <= n / 5; ++b) {
                            for (int v = 0; v <= n / 2; ++v) {
                                int asc = n - (200 * i + 100 * j + 50 * k + 20 * l + 10 * m + 5 * b + 2 * v);
                                if (asc >= 0)
                                    abc++;
                            }
                        }
                    }
                }
            }
        }
    }
    printf("%d\n", abc);
    return 0;
}
// printf("%d %d %d %d %d %d %d %d\n", i, j, k ,l, m, b, v, asc);