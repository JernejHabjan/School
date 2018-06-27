#include <stdio.h>
#include <ctype.h>
#include <memory.h>

char besedilo[10001];
char *besede[10001];//tabela kazalcev
int frekvence[10001];
int stBesed = 0;

void dodaj(char *beseda) {
    if (beseda[0] == '\0') return;
    for (int i = 0; i < stBesed; i++) {
        if (strcmp(besede[i], beseda) == 0) {
            //strcmp vrne -1 če je prva besedaa po abecedi prej, 0 če sta enak i , sicer pa 1
            //sta enaki
            frekvence[i]++;
            return;
        }
    }


    besede[stBesed] = beseda;
    frekvence[stBesed] = 1;
    stBesed++;
}


int vaje07() { /////////////////////PAZI TEZKE VAJE!!!!!!!!
    int n;
    scanf("%d", &n);

    char znak;
    char *b = besedilo;
    char *zacetek = b; //kaze na zacetek besede


    while (scanf("%c", &znak) != EOF) {
        if (isalpha(znak)) { //vse kar je \n, presledke.. punct...

            *b = tolower(znak);
            b++;
        } else {
            *b = '\0';
            b++;
            //zapomnim si besedo
            dodaj(zacetek);
            //pokazem na zacetek naslednje besede
            zacetek = b;
        }
    }

    *b = '\0';
    dodaj(zacetek);


    //uredi

    int urejen = 0;
    while (!urejen) {
        urejen = 1;
        for (int i = 0; i < stBesed - 1; i++) {
            if (frekvence[i] < frekvence[i + 1] ||
                (frekvence[i] == frekvence[i + 1] && strcmp(besede[i], besede[i + 1]) > 0)) {


                //swap
                int tmp = frekvence[i];
                frekvence[i] = frekvence[i + 1];
                frekvence[i + 1] = tmp;

                char *tmp2 = besede[i];
                besede[i] = besede[i + 1];
                besede[i + 1] = tmp2;

                urejen = 0;
            }
        }
    }


    for (int i = 0; i < stBesed && i < n; i++) {
        printf("%s %d\n", besede[i], frekvence[i]);
    }

    return 0;

}
