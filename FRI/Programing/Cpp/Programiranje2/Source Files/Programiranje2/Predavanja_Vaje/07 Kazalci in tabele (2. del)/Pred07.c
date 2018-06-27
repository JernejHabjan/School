
///Rekurzija nadaljevanje

///Skakačevi obhodi

#define N 8


//Hocemo stevilo unikatnih obhodov
//Hocemo cele obhode
char sah[N][N];//da vemo kje smo ze bli
//Zacnem na 00 in gledam kam lahko skocim
//Pri vsakem skoku pristejem count --ko count==stpolj konc

int skakac(int m, int n, int skok) {//prejmemo trenutno poz in kateri skok je po vrsti
    if (skok == N * N) return 1; //pazi to!!!
    int obhodov = 0;
    sah[m][n] = 1; //pazi to ni ascii 1 ampak ascii start od new header
    int NoviM, NoviN;

    //dal smo jo v fun da jo vidi sam ta f.
    static int poteze[N][2] = {{2,  1},
                               {1,  2},
                               {-1, 2},
                               {-2, +1},
                               {-2, -1},
                               {-1, -2},
                               {+2, -1},
                               {+1, -2}};
    for (int i = 0; i < N; i++) {
        //Skoki
        NoviM = m + poteze[i][0];
        NoviN = n + poteze[i][1];
        //Reveri a nismo s sahovnce ali ce smo tm ze bli
        //Nesmem it
        //Else morm it tja
        if ((0 <= NoviM) && (NoviM < N) && (0 <= NoviN) && (NoviN < N) && (sah[NoviM][NoviN] == 0)) {
            obhodov += skakac(NoviM, NoviN, skok + 1);
        }
    }
    sah[m][n] = 0; //brisemo za sabo da lah z vecih pozicij skoc na isto polje
    return obhodov;
}
//Zacetni klic:
//Skakac(0,0,1); --zacnem na 00 in eno polje sem ze obiskal 00

//Ce mamo recmo kmete not.. nafilamo v prvotn polje 1ke tm kjer so kmertje in zmanjsamo final count


// Problem 2
//Problem 8 kraljic
//Isti problem kot prejsn
//Sam namest potez imam poteze kraljice --kot da bi naredu verigo skakacev

////////// 8 kraljic //////////
#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>



bool isAttacked(int q[], int col) {
    for (int i = 0; i < col; i++) {
        if (q[i] == q[col]) return true; //same row
        if (abs(q[col] - q[i]) == (col - i)) return true; //both diagonals
    }return false;
}

int solve(int q[N], int col) {
    int count = 0;
    if (col == N) ++count;
    else
        for (int i = 0; i < N; i++) {
            q[col] = i;
            if (!isAttacked(q, col))
                count += solve(q, col + 1);
        }
    return count;
}

void problem8Kraljic() {
    int a[N];
    printf("Stevilo moznih postavitev: %d\n",solve(a, 0));
}


int pred07() {
    //problem8Kraljic();

    printf("%d\n", skakac(0,0,1));
    return 0;
}