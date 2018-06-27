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

int najdi_besedo(char beseda[], char **besede, int st_bes) {
    // poiscimo besedo
    for (int i = 0; i < st_bes; i++)
        if (strcmp(beseda, besede[i]) == 0)
            return i;
    return -1;
}

int main() {
    int n;
    scanf("%d", &n);

    char vbeseda[NDOLZINA], beseda[NDOLZINA];
    char **besede = NULL;
    int stbesed = 0;
    while (scanf("%s", vbeseda) != EOF) {
        // odstrani locila, spremeni v majhne crke
        locila_pomanjsaj(vbeseda, beseda);
        int pos = najdi_besedo(beseda, besede, stbesed);
        if (pos == -1) {
            // nismo nasli besede
            stbesed++;

            besede = (char **) realloc(besede, stbesed * sizeof(char *));
            besede[stbesed - 1] = malloc(strlen(beseda) + 1);
            strcpy(besede[stbesed - 1], beseda);
            printf("%s\n", beseda);
        }
    }
    return 0;
}
