

#include <stdlib.h>
#include <stdio.h>


#define MAXKNJIG 60

char texts[MAXKNJIG][10001];
int knjig = 0;



typedef struct _knjiga{
    int id;
    char *text_ptr;

}knjiga, *knjiga_ptr;

knjiga_ptr knjige[MAXKNJIG];

int main_knjige(int argc, char *argv[]){
    //program is reading books from command
    // ./neki.c lol.txt druga.txt

    if(argc < 2)exit(1);

    for(int i =1; i < argc; i++){
        FILE *f;
        f = fopen(argv[i], "r");
        if(f == NULL) exit(1);

        fscanf(f, "%s", texts[knjig]);
        knjig++;

    }
    for(int i = 0; i < knjig; i++){
        knjiga_ptr new_knjiga = (knjiga_ptr) malloc(sizeof(knjiga));
        new_knjiga->id = i;
        new_knjiga->text_ptr = texts[i];

        knjige[i] =  new_knjiga;
    }




}
