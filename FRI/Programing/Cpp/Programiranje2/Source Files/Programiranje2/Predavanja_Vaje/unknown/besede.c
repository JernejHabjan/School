#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int n;
char b[100001];
char b2[100001];

char **besede = 0;
int *count = 0;

void pocisti(char *a, char *b) {
    for (; *a != 0; a++) {
        if (!ispunct(*a)) {
            *b = tolower(*a);
            b++;
        }
    }
    *b = 0;
}

int main() {
    scanf("%d", &n);

    int st_besed = 0;

    while (scanf("%s", b) != EOF) {
        pocisti(b, b2);

        int nova = 1;
        for (int i = 0; i < st_besed; i++) {
            if (strcmp(besede[i], b2) == 0) {
                count[i]++;
                nova = 0;
            }
        }

        if (nova) {
            st_besed++;
            besede = (char **) realloc(besede, st_besed * sizeof(char *));
            count = (int *) realloc(count, st_besed * sizeof(int *));
            char *tmp = (char *) malloc((strlen(b2) + 1) * sizeof(char));
            strcpy(tmp, b2);
            besede[st_besed - 1] = tmp;
            count[st_besed - 1] = 1;
        }
    }

    int sorted = 0;
    while (!sorted) {
        sorted = 1;
        for (int i = 1; i < st_besed; i++) {
            if (count[i] > count[i - 1]
                || (count[i] == count[i - 1] && strcmp(besede[i], besede[i - 1]) < 0)) {
                char *tmp = besede[i - 1];
                besede[i - 1] = besede[i];
                besede[i] = tmp;

                int tmp2 = count[i - 1];
                count[i - 1] = count[i];
                count[i] = tmp2;

                sorted = 0;
            }
        }
    }

#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

    int n;
    char b[100001];
    char b2[100001];

    char **besede = 0;
    int *count = 0;

    void pocisti(char *a, char *b) {
        for (; *a != 0; a++) {
            if (!ispunct(*a)) {
                *b = tolower(*a);
                b++;
            }
        }
        *b = 0;
    }

    int main() {
        scanf("%d", &n);

        int st_besed = 0;

        while (scanf("%s", b) != EOF) {
            pocisti(b, b2);

            int nova = 1;
            for (int i = 0; i < st_besed; i++) {
                if (strcmp(besede[i], b2) == 0) {
                    count[i]++;
                    nova = 0;
                }
            }

            if (nova) {
                st_besed++;
                besede = (char **) realloc(besede, st_besed * sizeof(char *));
                count = (int *) realloc(count, st_besed * sizeof(int *));
                char *tmp = (char *) malloc((strlen(b2) + 1) * sizeof(char));
                strcpy(tmp, b2);
                besede[st_besed - 1] = tmp;
                count[st_besed - 1] = 1;
            }
            if (n > st_besed) {
                n = st_besed;
            }

            for (int i = 0; i < n; i++) {
                printf("%s %d\n", besede[i], count[i]);
            }

            return 0;
        }