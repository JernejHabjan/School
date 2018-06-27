#include <stdio.h>

//Pogojno prevajanje
//#if
//#else
//#endif
//#ifdef
//#ifndef

//primer:
//main.c:
//#include <stdio.h>
//#if CHOICE
        //int main(){}
//#else
        //int main(int argc, char*argv[]){}
//#endif

//$ gcc -CHOICE=1 main.c

//stdio.h
//#ifndef __STDIOH__
//#define __STDIOH


//PREVAJANJE "VELIKIH" PROGRAMOV

        //header.h:
//#ifndef __MYLISTH__
//#define __MYLISTH__
        //typedef struct _node{
        //    void *value;
        //    struct _node *next;
        //}node;

        //list insert (void * value, list l);
        //list delete (void * value, list l);
        //extern list ll; ////extern
        //static int priv_var;///ta var je samo v temu filu pa more bit static... Static pomen sam tu se vid
//#endif

        //mylist.c:
//#include header.h
//Implementiras funkcije
        //list ll; //tle je extern definan
        //priv_var =5; //vidna samo tu

//main.c
//#include <stdio.h>
//#include "header.h"
//int main(){}

//Prevajanje
//gcc -c mylist.c //dobimo mylist.o --prevajanje
//gcc -c main.c //dobimo main.o --prevajanje
//gcc -o main main.o mylist.o --povezovanje
// ./main


//Trije deli programa
//main.c  #include "mylist.h"
//problem.c #include "mylisy.h"
//mylist.h --- typedef {}*list;
//list add(){}
//list del(){}
//gcc -c main.c
//gcc -c mylist.c
//Koda bo v obeh datotekah (main.o in problem.o)
//gcc -o main main.o mylist.o //linker bi rekel.. 2 inserta, 2 deleta -> napaka



int pred12() {


    return 0;
}