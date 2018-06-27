#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

#define NDOLZINA 100000

void locila_pomanjsaj(char *src, char *dst) {
    for (; *src; src++)
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

int najpogostejsa(int *frekvence, char **besede, int st_bes) {
    int max = 0;
    for (int i = 1; i < st_bes; i++) {
        if (frekvence[i] > frekvence[max] ||
            (frekvence[i] == frekvence[max] && strcmp(besede[i], besede[max]) < 0))
            max = i;
    }
    return max;
}


int main() {
    int n;
    scanf("%d", &n);

    char vbeseda[NDOLZINA], beseda[NDOLZINA];
    char **besede = NULL;
    int *frekvence = NULL;
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

            frekvence = realloc(frekvence, stbesed * sizeof(int));
            frekvence[stbesed - 1] = 1;
        } else
            frekvence[pos]++;
    }

    // izpisi n najbolj pogostih
    for (int i = 0; i < n; i++) {
        int najpogosta = najpogostejsa(frekvence, besede, stbesed);
        if (frekvence[najpogosta] > 0)
            printf("%s %d\n", besede[najpogosta], frekvence[najpogosta]);
        frekvence[najpogosta] = 0;
    }
    return 0;
}
