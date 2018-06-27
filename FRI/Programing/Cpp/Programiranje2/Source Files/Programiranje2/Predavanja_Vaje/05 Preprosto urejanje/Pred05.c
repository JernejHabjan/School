//
// Created by Jernej Habjan on 10. 03. 2017.
//
#include <stdio.h>
#include <stdlib.h>


int a1[100];
int *a2[100]; //to je tabela 100 kazalcev na int

int (*a3)[100]; // a je kazalec na tabelo 100 intov (deklariramo 1 kazalec)
int *(a4[100]); //pa je tabela 100 kazalcev na int(deklariramo 100 *sizeof(*int)

//// Main

//public static void main(String[] args){}
int main_example_3(int argc, char *argv[]) {}//dobi tabelo kazalcev na stringe
//Pru argv je ime dat...
//Napisi program ki vrne vse arg

//Multi-D tabele
//Programiranje game of life

char world0[100][100];
char world1[100][100]; //temp world

//Na zacetku vse mrtve potem pa par zivih

void clear(char world0[100][100]) {//Napise v vsako celico space
}

void print_world(char world0[100][100]) {//Izpise zive kot X in mrtve kot
}

//clear(world0) //naslov je prletu v funkcijo

int neighbours(char world[100][100], int i, int j) {//Pogleda 8 celic okrog pa merkat mors da s sveta ne pade
}

void step(char prev[100][100], char next[100][100]) {
    clear(next);
    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
            int n = neighbours(prev, i, j);

            if (prev[i][j] == 1) { //celica je ziva
                if (n < 2)
                    printf("celica umre");
                if (n == 2 || n == 3)
                    printf("celica prezivi");

                if (n > 3)
                    printf("celica umre");


            } else //celica je mrtva
            if (n == 3)

                printf("ozivi");


        }
    }
}

void run_zivljenje() {
    clear(world0);
    //init(world0);
    for (int g = 1; g < 100; g++) {
        step(world0, world1);
        print_world(world1);
        //In this line copy world1 to world0



    }
}

void run_zivljenje_pointers() {
    //Lahko s kazalci
    char  (*prev1)[100][100];//2 kazalca na tabelo 100*100 znakov
    char  (*next1)[100][100];

    prev1 = &world0;
    next1 = &world1;//vsak je usmerjen na enga izmed dveh tabel

    clear(*prev1); //*prev je tabela kamor kaze
    //Init(*prev)
    for (int g = 1; g < 100; g++) {
        step(*prev1, *next1);
        print_world(*next1);
        //Zamenjamo
        char (*temp)[100][100];
        temp = prev1;
        prev1 = next1;
        next1 = temp;
        //Tukaj nismo kopirali tabele ampak smo samo pointerja switchal
    }
}

////////
void nadaljevanje() {

    int a[10];
    int *pi = a; //samo 1 int
    int (*pa)[10] = &a; //cela tabela

    int f(int b[10]) {}

//Klicanje funkcij
    f(a);
    f(pi); //pazi
    f(*pa); //pazi

/////////
    char **prev2;
    char **next2;
    int N = 20;
    prev2 = (char **) malloc(N * sizeof(char *));// prev kaze na 1d tabelo--tuki not so kazalci na char
    for (int i = 0; i < N; i++)
        prev2[i] = (char *) malloc(N * sizeof(char));//tukaj ni nujno da je N ampak je lahko druge dolzine...
    // Se vc.. vsaka dolzina teh tabel ie lah drgacna
//Prev[i] je enojni pointer

//prev2 //tipa char**
//prev2[i] //tipa char *
//prev2[i][j] //tipa char


//Ko free - s for zanko po arrayu pa une freejat pa sele nakonc zunanjo tabelo free
}
//////Pomnilniska 1d tabela

int f2(int t[100][1000]) { printf("temp"); }//najhitreje rece zunanji index
//1000celic, 1000celic... In to 100x

int f3(int t[][1000]) { printf("temp"); } //spustimo tega
//a[4][45] --lah zracunas 4*1000+45 (nerabs stotke)

//Primer
int a_pred5[10][20];
int b_pred5[30][40];

int f4(int t[][20]) {
    return t[1][3];
}

void nadaljevanje1() {

    f4(a_pred5); //vrne 1*20+3 --23. Celica
    //f4(b_pred5); //vrne 1*20+3 -- 23. Celica -- ker je v funkciji 20!!!!
//Vrne narobe
//ZATO TEGA NE POCNEMO
}

///Def tabele
//1. S fiksno velikostjo in jih prenasam s fiksno velikostjo po fun
//2. Vzamemo fiksno vikost tabele ampam kot parameter lahko samo 1. Dimenzijo lah nardimo fleksibilno
//3. Ce rabmo fleksibilno pa nardis z malloc

//Kaj naredimo ce: najvecja velikost je 100x100 -vzamemo fiksno in porabmo kokr porabmo
//Kaj naredimo ce:nevemo koliko ele
//    Malloc




int pred05() {

    return 0;
}