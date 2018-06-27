#include <stdio.h>
#include <stdlib.h>

struct vagon {
    int ID;
    int teza;
    struct vagon *next;
};

struct vlak {
    int ID;
    struct vlak *next;
    struct vagon *prvi;
};

struct vlak *dodajVlak(struct vlak *prvi, int vlak_id) {
    struct vlak *tmp = prvi;
    struct vlak *prev = NULL;
    // gremo cez vlake, dokler imajo manjsi id od vlak_id
    while (tmp && (tmp->ID < vlak_id)) {
        prev = tmp;
        tmp = tmp->next;
    }

    // naslednji je vecji ali enak id; ce je enak, ga vrnemo, ce je vecji, naredimo pred njim nov vlak z novim idjem
    if (tmp && tmp->ID == vlak_id)
        return tmp;

    struct vlak *nov = malloc(sizeof(struct vlak));
    nov->ID = vlak_id;
    nov->next = prev->next;
    nov->prvi = NULL;
    prev->next = nov;
    return nov;
}

struct vlak *getVlak(struct vlak *prvi, int vlak_id) {
    if (!prvi)
        return NULL;
    struct vlak *tmp = prvi;
    while (tmp) {
        if (tmp->ID == vlak_id)
            return tmp;
        tmp = tmp->next;
    }
    return NULL;
}

int getTezaVlaka(struct vlak *vlak) {
    struct vagon *tmp = vlak->prvi;
    int teza = 0;
    while (tmp) {
        teza += tmp->teza;
    }
    return teza;
}

void priklopi(struct vlak *prvi, int vlak_id, int vagon_id, int teza) {
    // poisci vlak z oznako vlak_id; ce ne obstaja, naredi nov vlak in ga vrne
    struct vlak *v = dodajVlak(prvi, vlak_id);

    struct vagon *tmp = v->prvi;
    struct vagon *prev = NULL;
    while (tmp && tmp->ID < vagon_id) {
        prev = tmp;
        tmp = tmp->next;
    }
    // poglej, ce ze obstaja vagon z isto vagon_id in ga ignoriraj
    if (tmp && tmp->ID == vagon_id)
        return;
    // priklopi nov vagon
    struct vagon *nov = malloc(sizeof(struct vagon));
    nov->ID = vagon_id;
    nov->teza = teza;
    if (!tmp) {
        if (prev) {
            prev->next = nov;
            nov->next = tmp;
        } else {
            v->prvi = nov;
            nov->next = NULL;
        }
    } else {
        if (prev) {
            nov->next = prev->next;
            prev->next = nov;
        } else { // dodamo na zacetek
            v->prvi = nov;
            nov->next = tmp;
        }
    }
}

void zbrisiVlak(struct vlak *prvi, struct vlak *v) {
    struct vlak *tmp = prvi;
    struct vlak *prev = NULL;
    while (tmp) {
        if (tmp == v) {
            if (prev) // ce obstaja prejsnji
                prev->next = tmp->next;
            free(v);
            return;
        }
        prev = tmp;
        tmp = tmp->next;
    }
}

void odklopi(struct vlak *prvi, int vlak_id, int vagon_id) {
    struct vlak *v = getVlak(prvi, vlak_id);
    if (v) {
        struct vagon *vag = v->prvi;
        struct vagon *prev = NULL;
        while (vag) {
            if (vag->ID == vagon_id) {
                if (vag == v->prvi) {
                    v->prvi = vag->next;
                    free(vag);
                } else {
                    prev->next = vag->next;
                    free(vag);
                    vag = prev;
                }
            }
            prev = vag;
            vag = vag->next;
        }
        // ce smo zbrisali edini vagon, moramo zbrisati se vlak
        if (!v->prvi)
            zbrisiVlak(prvi, v);
    } else {
        //printf("Vlak %d ne obstaja!\n", vlak_id);
    }
}

void izpis(struct vlak *prvi) {
    prvi = prvi->next;
    while (prvi) {
        int teza = 0;
        struct vagon *tmp = prvi->prvi;
        printf("%d: ", prvi->ID);
        while (tmp) {
            printf("%d ", tmp->ID);
            teza += tmp->teza;
            tmp = tmp->next;
        }
        printf("(%d)\n", teza);
        prvi = prvi->next;
    }
}

int main() {
    char ukaz;
    int vlak_id, vagon_id;
    int teza;
    struct vlak *prvi = malloc(sizeof(struct vlak));

    prvi->ID = 0;
    prvi->next = NULL;
    prvi->prvi = NULL;
    while (scanf("(%c, %d, %d, %d)\n", &ukaz, &vlak_id, &vagon_id, &teza) != EOF) {
        //printf("%c %d %d %d\n", ukaz, vlak_id, vagon_id, teza);
        if (ukaz == 'P') {
            priklopi(prvi, vlak_id, vagon_id, teza);
        } else if (ukaz == 'O') {
            odklopi(prvi, vlak_id, vagon_id);
        }
    }
    //printf("izpis\n");
    izpis(prvi);
    return 0;
}