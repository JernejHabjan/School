#include <stdio.h>
#include <stdlib.h>

typedef struct pika {
    int R, G, B;
} Pixel;

double getGrayscaleWindow(Pixel **slika, int i, int j, int wsize) {
    double sum = 0;
    //printf("\t%d %d %d\n", i, j, wsize);
    for (int a = i; a < i + wsize; a++)
        for (int b = j; b < j + wsize; b++) {
            sum = sum + slika[a][b].R + slika[a][b].G + slika[a][b].B;
            //printf("\t\t%d %d\n", a, b);
        }
    return sum / (3 * wsize * wsize);
}

char gray2ascii(double sivina) {
    char znaki[] = {' ', '.', '\'', ':', 'o', '&', '8', '#', '@'};
    unsigned char scale[] = {230, 200, 180, 160, 130, 100, 70, 50};
    for (int k = 0; k < 8; k++)
        if (sivina >= scale[k])
            return znaki[k];
    return '@';
}

int main() {
    int w, h;
    char filein[1024], fileout[1024];
    int winsize;

    scanf("%s\n%s\n%d", filein, fileout, &winsize);

    FILE *f = fopen(filein, "rb");
    if (f == NULL) {
        printf("Ne morem odpreti datoteke %s.\n", filein);
        return (-1);
    }

    fscanf(f, "P6\n%d %d\n255\n", &w, &h);
    int bin = ftell(f);
    printf("%d %d\n", w, h);
    fseek(f, 0, SEEK_END);
    int len = ftell(f);
    unsigned char buffer[len - bin];

    fseek(f, bin, SEEK_SET);
    fread(buffer, 1, len - bin, f);
    fclose(f);

    int pos = 0;

    Pixel *slika[h];
    for (int row = 0; row < h; row++)
        slika[row] = (Pixel *) malloc(w * sizeof(Pixel));

    for (int i = 0; i < h; i++)
        for (int j = 0; j < w; j++) {
            slika[i][j].R = buffer[pos++];
            slika[i][j].G = buffer[pos++];
            slika[i][j].B = buffer[pos++];
        }

    FILE *out = fopen(fileout, "w");
    for (int i = 0; i < h; i += winsize) {
        for (int j = 0; j < w; j += winsize) {
            fprintf(out, "%c", gray2ascii(getGrayscaleWindow(slika, i, j, winsize)));
        }
        fprintf(out, "\n");
    }
    fclose(out);
    return 0;
}