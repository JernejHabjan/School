#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

char ax[10000];
char stevila[10000];//sem notri shranjujemo stevila recmo 1001
int steviloStevilk = 0;

int main() {


    scanf("%c", ax);
    char *tmp = ax;
    *tmp++;
    while (scanf("%c", tmp) != EOF) {

        tmp++;

    }

    int len = strlen(ax);
    int vsota = 0;

    char prejsniZnak = ' ';

    for (int i = 0; i < len; i++) {
        char naslednjiZnak = ax[i + 1];
        if (ax[i] >= '0' && ax[i] <= '9') {
            if (prejsniZnak == ' ') {
                //printf("int %d\n", ax[i]- '0');
                stevila[steviloStevilk] = ax[i];
                steviloStevilk++;
            }
        } else {

            prejsniZnak = ax[i];
            //printf("prejsni znak: %c\n", ax[i]);
            //printf("%s", stevila);

            int stevilo = 0;
            sscanf(stevila, "%d", &stevilo);

            vsota += stevilo;

            //printf("vsota: %d\n", vsota);

            steviloStevilk = 0;
            for (int i = 0; i < 10000; i++) {
                stevila[i] = '\0';

            }
        }
    }
    printf("%d\n", vsota);
    return 0;
}
