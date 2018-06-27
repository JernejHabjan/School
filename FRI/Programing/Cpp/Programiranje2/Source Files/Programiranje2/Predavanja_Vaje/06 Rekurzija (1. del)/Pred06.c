//
// Created by Jernej Habjan on 10. 03. 2017.
//


#include <stdio.h>
#include <stdlib.h>




///Rekurzija

void primer_rekurzija_1() {
    int f(int i) {
        if (i == 0)
            return 0;
        else return f(i - 1);
    }
}
//Kaj se zgodi ko f(-5)
//Overflow do 0 -- vrne 0  --- SE NE ZGODI Kker intov je 2**32(pro 32 int programih)--bi se pa zgodil pr CHAR
//Stack overflow--pri int pride do tega

//Primer 2
//Vzajemno rekurzivni
int f(int i);

int g(int i);

int f(int i) { if (i == 0) return 0; else return g(i - 1); }

int g(int i) { if (i == 0) return 0; else return f(i - 1); }

//Primer

int f1(int i) {
    int a = i;
    int b = 0;
    if (i == 0) b = 0; else b = f1(i - 1);
    return a + b;
}


//Pazi... Hanoi towers
void hanoiTowers(int n, char from_rod, char to_rod, char aux_rod){
    if(n == 1){
        printf("move disk 1 from %c to %c", from_rod, to_rod);
        return;
    }
    hanoiTowers(n-1, from_rod, aux_rod, to_rod);
    printf("move %d from %c to %c", n, from_rod, to_rod);
    hanoiTowers(n-1, aux_rod, to_rod, from_rod);
}


//Naloga kolokvij
//Zgornja trikotna matrika. Pod diagonalo so same nicle. Hocmo indeksirat od 1 naprej

//Double a[6][6]
void zgornje_trikotna() {
    int N = 6;
    double **b;
    b = (double **) malloc(N * sizeof(double *));//
    b--; //zdaj kaze prvi ele na 1 --- B[0] je exception  ////PAZI PREMIK!!!!!!
    for (int i = 1; i < N + 1; ++i) { // ker od 1 startamo
        b[i] = (double *) malloc((N - i + 1) * sizeof(double)); // da je se vrstica n+1 /////pazi n-i+1
        b[i]--; //zdj se se te zacnejo z 1
    }
    //Za free bi mogl dat pol B++ pr vsakmu pa pol feejat


}

///Rekurzija naprej

//Izracunaj nto vrstico fib zaporedja
void pascal(int n, int *coeff) { //tabela n elementov
    if (n == 1) {
        coeff[0] = 1;
        return;
    }
    int *c;
    c = (int *) malloc(n - 1 * sizeof(int));
    pascal(n - 1, c);
    coeff[0] = 1;
    for (int i = 1; i < n - 1; i++)
        coeff[i] = c[i - 1] + c[i]; //sestejemo zgornja dva ele
    coeff[n - 1] = 1;
    free(c);
    return;
}

void pascal1(int n, int *coeff) {
    if (n == 0) {
        coeff[0] = 1;
        return;
    }
    pascal1(n - 1, &coeff[1]);
    coeff[0] = 1;
    for (int i = 1; i < n - 1; i++)
        coeff[i] = coeff[i] + coeff[i + 1]; // isto vrstico sesteje

}

int pred06() {


    return 0;
}