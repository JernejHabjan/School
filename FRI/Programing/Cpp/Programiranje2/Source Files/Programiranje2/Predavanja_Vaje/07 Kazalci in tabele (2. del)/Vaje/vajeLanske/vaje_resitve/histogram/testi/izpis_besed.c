#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define NDOLZINA 100000

void locila_pomanjsaj(char *src, char *dst) {
    for (; *src; ++src)
        if (!ispunct(*src))
            *dst++ = tolower(*src);
    *dst = 0;
}

int main() {
    int n;
    scanf("%d", &n);

    char vbeseda[NDOLZINA], beseda[NDOLZINA];
    while (scanf("%s", vbeseda) != EOF) {
        // odstrani locila, spremeni v majhne crke
        locila_pomanjsaj(vbeseda, beseda);
        printf("%s\n", beseda);
    }
    return 0;
}
