
#include <stdio.h>
#include <stdlib.h>



void vlaki_moji() ;

int vaje09() {
    //vlaki();
    vlaki_moji();
    return 0;
}



///// MOJI VLAKI


typedef struct _mojVagon {
    struct _mojVagon *next;
    unsigned int vagon_id;
} moj_vagon, *moj_vagon_ptr;

typedef struct _MojVlak {
    struct _MojVlak *next;
    moj_vagon_ptr firstVagon;
    unsigned int vlak_id;
} moj_vlak, *moj_vlak_ptr;

void vlaki_moji();

void izpis_moj(moj_vlak_ptr pVlak){
    moj_vlak_ptr temp_pVlak = pVlak->next;

    while(temp_pVlak != NULL){
        printf("%u:", temp_pVlak->vlak_id);

        //vagoni
        moj_vagon_ptr temp_pVagon = temp_pVlak->firstVagon->next;
        while(temp_pVagon != NULL){
            printf(" %u",temp_pVagon->vagon_id);
            temp_pVagon = temp_pVagon->next;
        }

        printf("\n");
        temp_pVlak = temp_pVlak->next;
    }

}
moj_vagon_ptr ustvariVagon(unsigned int id, moj_vagon_ptr naslednji){
    moj_vagon_ptr vagon = (moj_vagon_ptr)malloc(sizeof(moj_vagon));
    vagon->vagon_id = id;
    vagon->next = naslednji;
    return vagon;



}

moj_vlak_ptr ustvariVlak(unsigned int id, moj_vlak_ptr naslednji){
    moj_vlak_ptr vlak = (moj_vlak_ptr)malloc(sizeof(moj_vlak));
    vlak->vlak_id = id;

    vlak->firstVagon = ustvariVagon(-1, NULL);
    vlak->next = naslednji;
    return vlak;
}

void free_moj(moj_vlak_ptr first_vlak) {
    moj_vlak_ptr temp_first_vlak = first_vlak;
    while (temp_first_vlak != NULL) {

        moj_vagon_ptr this_first_vagon = temp_first_vlak->firstVagon;
        while (this_first_vagon != NULL) {
            moj_vagon_ptr this_vagon_naslednji = this_first_vagon->next;
            free(this_first_vagon);
            this_first_vagon = this_vagon_naslednji;

        }
        moj_vlak_ptr q = temp_first_vlak->next;
        free(temp_first_vlak);
        temp_first_vlak = q;
    }
}


void add_vlak_moj(unsigned int temp_vlak_id, unsigned int temp_vagon_id, moj_vlak_ptr first_vlak){


    moj_vlak_ptr predhodnji_vlak = first_vlak;
    while(predhodnji_vlak->next != NULL && predhodnji_vlak->next->vlak_id < temp_vlak_id){
        predhodnji_vlak= predhodnji_vlak->next;
    }

    if(predhodnji_vlak->next == NULL ||predhodnji_vlak->next->vlak_id != temp_vlak_id){ //prazen vlak
        moj_vlak_ptr novi = ustvariVlak(temp_vlak_id, predhodnji_vlak->next );
        predhodnji_vlak->next = novi;
    }


    moj_vlak_ptr trenutniVlak = predhodnji_vlak->next; //dodamo vagon
    moj_vagon_ptr predhodnji_vagon = trenutniVlak->firstVagon;
    while(predhodnji_vagon->next != NULL && predhodnji_vagon->next->vagon_id < temp_vagon_id){
        predhodnji_vagon= predhodnji_vagon->next;
    }
    if(predhodnji_vagon->next == NULL ||predhodnji_vagon->next->vagon_id != temp_vagon_id){ //prazen vlak
        moj_vagon_ptr novi = ustvariVagon(temp_vagon_id, predhodnji_vagon->next);
        predhodnji_vagon->next = novi;
    }

}



void vlaki_moji() {

    moj_vlak_ptr first_vlak = ustvariVlak(0, NULL);

    unsigned int st_ukazov;
    scanf("%u", &st_ukazov);




    for (int i = 0; i < st_ukazov; i++) {

        unsigned int temp_vlak_id, temp_vagon_id;
        scanf("%u %u", &temp_vlak_id, &temp_vagon_id);
        //printf("lol %d\n", temp_vlak_id);
        add_vlak_moj(temp_vlak_id, temp_vagon_id, first_vlak);

    }

    izpis_moj(first_vlak);
    free_moj(first_vlak);
}

