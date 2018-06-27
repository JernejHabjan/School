#include <stdio.h>

int nums[18][2];
int sel[18];

void izpis(int n, int d) {
    if(d == n) {
        int i;
        for(i = 0; i < n; i++)
            printf("%d", sel[i]);
        printf("\n");
    }
    else {
        int i;
        for(i = nums[d][0]; i <= nums[d][1]; i++) {
            sel[d] = i;
            izpis(n, d+1);
        }
    }
}

int main() {
    int n, i;
    scanf("%d\n", &n);
    for(i = 0; i < n; i++)
        scanf("%d %d\n", &nums[i][0], &nums[i][1]);
    izpis(n, 0);
    return 0;
}

