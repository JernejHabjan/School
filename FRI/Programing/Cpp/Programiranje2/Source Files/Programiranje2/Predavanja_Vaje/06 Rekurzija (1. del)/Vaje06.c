//
// Created by Jernej Habjan on 9. 03. 2017.
//

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool cilj_dosezen(int *polni, int n, int k) {
    for (int i = 0; i < n; i++) {
        if (polni[i] == k) {
            return true;
        }
    }
    return false;
}


int min1(int a, int b) {
    return a < b ? a : b;
}

bool search(int *velikosti, int *polni, int n, int k, int korakov) {
    if (korakov < 0) {
        return false;
    }

    if (cilj_dosezen(polni, n, k)) {
        return true;
    }

    for (int i = 0; i < n; i++) {

        if (polni[i] == 0) {//nafilamo do vrha
            polni[i] = velikosti[i];
            if (search(velikosti, polni, n, k, korakov - 1) == true) {
                return true;
            }
            polni[i] = 0; //vedra nismo napolni in poskusimo kaj drugega
        }
        if (polni[i] > 0) { //zlijemo
            int temp_polnost = polni[i]; //da lahko restoramo polnost

            polni[i] = 0;
            if (search(velikosti, polni, n, k, korakov - 1) == true) {
                return true;
            }
            polni[i] = temp_polnost; //vedra nismo izpraznili in ga nazaj nafilamo in probamo kj druzga

        }

        for (int j = 0; j < n; j++) {    //prelij
            //vednro i ni enako j, vedro i prazen in j nesme biti poln
            if (i != j && polni[i] != 0 && polni[j] < velikosti[j]) {
                int volumenPrelitja = min1(polni[i], velikosti[j] - polni[j]);

                int staraPolnostI = polni[i];
                int staraPolnostJ = polni[j];

                polni[i] -= volumenPrelitja;
                polni[j] += volumenPrelitja;


                if (search(velikosti, polni, n, k, korakov - 1) == true) {
                    return true;
                }

                polni[i] = staraPolnostI; //restore old values
                polni[j] = staraPolnostJ;
            }
        }

    }
    return false; //ce nben izmed pogojev ni izpolnen
}


int vaje06() { /// REKURZIVNA VRCI
    int n;
    scanf("%d", &n);
    int *velikosti;
    int *polni;
    velikosti = (int *) malloc(n * sizeof(int));

    for (int i = 0; i < n; i++) {
        scanf("%d", &velikosti[i]);

    }
    polni = (int *) calloc((size_t) n, sizeof(int)); /////PAZI.---- SE VSE POSTAVI NA 0


    int k; //k je litrov vode ki hocemo
    scanf("%d", &k);

    int korakov = 0;

    while (!search(velikosti, polni, n, k, korakov)) { //vsakic ko funkcija vrne false
        korakov++;
    }

    printf("%d", korakov);

    free(velikosti);
    free(polni);
    return 0;
}
