//
// Created by Jernej Habjan on 10. 03. 2017.
//


#include <stdio.h>

char ascii(int value) {
    if (value >= 230) return ' ';
    if (value >= 200) return '.';
    if (value >= 180) return '\'';
    if (value >= 160) return ':';
    if (value >= 130) return 'o';
    if (value >= 100) return '&';
    if (value >= 70) return '8';
    if (value >= 50) return '#';
    return '@';
}

typedef struct _pixel {
    unsigned char r;
    unsigned char g;
    unsigned char b;
} pixel;

int n, w, h;

int getValue(pixel *picture, int i, int j) {
    int value = 0;
    for (int a = i * n; a < (i + 1) * n; a++) {
        for (int b = j * n; b < (j + 1) * n; b++) {
            pixel p = picture[(a * w) + b];
            value += p.r + p.g + p.b;
        }
    }
    return value / (n * n * 3);
}


int ascii_art() {
    char in_filename[21];
    char out_filename[21];
    scanf("%s %s %d", in_filename, out_filename, &n);

    FILE *f = fopen(in_filename, "r");
    fscanf(f, "P6\n%d %d\n255\n", &w, &h);

    pixel picture[h][w];
    //for (int i=0; i<h; i++) {
    //  for (int j=0; j<w; j++) {
    //    fscanf(f, "%c%c%c",
    //      &picture[i][j].r,
    //      &picture[i][j].g,
    //      &picture[i][j].b);
    //  }
    //}

    unsigned char *c = &picture[0][0].r;
    for (int i = 0; i < h * w * 3; i++) {
        fscanf(f, "%c", c++);
    }

    //fread(&picture[0][0].r, h*w, sizeof(pixel), f);


    fclose(f);

    f = fopen(out_filename, "w");
    for (int i = 0; i < h / n; i++) {
        for (int j = 0; j < w / n; j++) {
            int value = getValue(&picture[0][0], i, j);
            fprintf(f, "%c", ascii(value));
        }
        fprintf(f, "\n");
    }

    return 0;
}

int vaje10() {

    ascii_art();

    return 0;
}