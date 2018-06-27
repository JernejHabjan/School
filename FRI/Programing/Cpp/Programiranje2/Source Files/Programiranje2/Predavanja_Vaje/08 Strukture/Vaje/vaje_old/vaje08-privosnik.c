#include <stdio.h>
#include <stdlib.h>

struct vagon {
    int id;
    int teza;
    struct vagon *next;
};

struct vlak {
    int id;
    struct vagon *firstVagon;
    int teza;
    struct vlak *next;
};

struct vlak *priklopi(struct vlak *firstVlak, int vlakId, int vagonId, int teza) {
    struct vlak *prevVlak = NULL, *vlak = firstVlak;
    for (; vlak != NULL && vlak->id < vlakId; prevVlak = vlak, vlak = vlak->next);
    if (vlak == NULL || vlak->id != vlakId) {
        // vlak ne obstaja
        vlak = malloc(sizeof(struct vlak));
        vlak->id = vlakId;
        vlak->teza = 0;
        vlak->firstVagon = NULL;
        if (prevVlak == NULL) {
            vlak->next = firstVlak;
            firstVlak = vlak;
        } else {
            vlak->next = prevVlak->next;
            prevVlak->next = vlak;
        }
    }
    // vlak obstaja
    struct vagon *prevVagon = NULL, *vagon = vlak->firstVagon;
    for (; vagon != NULL && vagon->id < vagonId; prevVagon = vagon, vagon = vagon->next);
    if (vagon == NULL || vagon->id != vagonId) {
        // vagon ne obstaja
        vagon = malloc(sizeof(struct vagon));
        vagon->id = vagonId;
        vagon->teza = teza;
        if (prevVagon == NULL) {
            vagon->next = vlak->firstVagon;
            vlak->firstVagon = vagon;
        } else {
            vagon->next = prevVagon->next;
            prevVagon->next = vagon;
        }
        vlak->teza += teza;
    }
    return firstVlak;
}

struct vlak *odklopi(struct vlak *firstVlak, int vlakId, int vagonId) {
    struct vlak *prevVlak = NULL, *vlak = firstVlak;
    for (; vlak != NULL && vlak->id != vlakId; prevVlak = vlak, vlak = vlak->next);
    if (vlak) {
        // vlak obstaja
        struct vagon *prevVagon = NULL, *vagon = vlak->firstVagon;
        for (; vagon != NULL && vagon->id != vagonId; prevVagon = vagon, vagon = vagon->next);
        if (vagon) {
            // vagon obstaja
            vlak->teza -= vagon->teza;
            if (vagon == vlak->firstVagon) vlak->firstVagon = vlak->firstVagon->next;
            else prevVagon->next = vagon->next;
            free(vagon);
            // vagon smo zbrisali => ali je potrebno zbrisati tudi vlak?
            if (vlak->firstVagon == NULL) {
                // vlak nima vec vagonov => pobrisi vlak
                if (vlak == firstVlak) firstVlak = firstVlak->next;
                else prevVlak->next = vlak->next;
                free(vlak);
            }
        }
    }
    return firstVlak;
}

int main() {
    struct vlak *firstVlak = NULL;
    char naloga;
    int vlakId, vagonId, teza;
    while (scanf(" (%c, %d, %d, %d)", &naloga, &vlakId, &vagonId, &teza) != EOF) {
        if (naloga == 'P') firstVlak = priklopi(firstVlak, vlakId, vagonId, teza);
        if (naloga == 'O') firstVlak = odklopi(firstVlak, vlakId, vagonId);
    }
    for (struct vlak *vlak = firstVlak; vlak != NULL; vlak = vlak->next) {
        printf("%d: ", vlak->id);
        for (struct vagon *vagon = vlak->firstVagon; vagon != NULL; vagon = vagon->next) {
            printf("%d ", vagon->id);
        }
        printf("(%d)\n", vlak->teza);
    }
}
