#include <stdio.h>

int above[1000000];

int main() {
    int n, k, t, i, sum = 0, b, j;
    scanf("%d %d %d\n", &n, &k, &t);
    for(int i = 0; i < n*k; i++) {
        above[i] = k - 1 - (i % k);
    }
    for(i = 0; i < t; i++) {
        scanf("%d ", &b);
        sum += above[b];
        for(j = (b / k) * k; j < b; j++) {
            above[j]--;
        }
    }
    printf("%d\n", sum);
    return 0;
}

