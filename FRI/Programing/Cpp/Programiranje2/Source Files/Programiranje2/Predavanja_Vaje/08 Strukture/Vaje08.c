#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct _ocena {
    int student;
    char predmet[11];
    int ocena;
} ocena;

ocena ocene[1000];
ocena pozitivne[1000];
int stPozitivnih = 0;


void popraviPozitivne(int student, int pozitivna) {
    int i;
    for (i = 0; i < stPozitivnih; i++) {
        if (pozitivne[i].student == student) {
            pozitivne[i].ocena += pozitivna;
            return;
        }
    }
    if (pozitivna > 0) {
        pozitivne[i].student = student;
        pozitivne[i].ocena = 1;
        stPozitivnih++;
    }
}

int pogojiZaNapredovanje() {
    int n, m, p;
    scanf("%d %d %d", &n, &m, &p);

    ocena tmp;
    int stOcen = 0;
    for (int i = 0; i < n; i++) {
        scanf("%d %s %d",
              &tmp.student,
              tmp.predmet,
              &tmp.ocena);

        int j;
        for (j = 0; j < stOcen; j++) {
            if (ocene[j].student == tmp.student && strcmp(ocene[j].predmet, tmp.predmet) == 0) {
                if (ocene[j].ocena < 6 && tmp.ocena >= 6)
                    popraviPozitivne(tmp.student, 1);
                else if (ocene[j].ocena >= 6 && tmp.ocena < 6)
                    popraviPozitivne(tmp.student, -1);
                ocene[j] = tmp;
                break;
            }
        }
        if (j == stOcen) {
            ocene[j] = tmp;
            if (tmp.ocena > 5)
                popraviPozitivne(tmp.student, 1);
            stOcen++;
        }
    }

    int stevec = 0;
    for (int i = 0; i < stPozitivnih; i++) {
        if (pozitivne[i].ocena >= p) {
            stevec++;
        }
    }
    printf("%d\n", stevec);

    return 0;
}


int vaje08() { /////CE PRIREJAMO STRUKTURE SE SKOPIRA IZ ENE V DRUGO IN NE KAZALCI
    pogojiZaNapredovanje();
    return 0;
}