#include <stdio.h>

char grayToChar(int v) {
    char Z[] = {' ', '.', '\'', ':', 'o', '&', '8', '#', '@'};
    int S[] = {230, 200, 180, 160, 130, 100, 70, 50};
    for (int i = 0; i < 8; i++) if (v >= S[i]) return Z[i];
    return Z[8];
}

int main() {
    char iFileN[1024], oFileN[1024];
    int n;
    scanf("%1023s %1023s %d", iFileN, oFileN, &n);

    FILE *iFile = fopen(iFileN, "rb");
    FILE *oFile = fopen(oFileN, "w");
    if (!iFile || !oFile) return 1;
    int w, h;
    fscanf(iFile, "P6\n%d %d\n255\n", &w, &h);

    for (int l = 0; l < h / n; l++) { // za vsako vodoravno vrsto blokov
        int row[w / n];
        for (int k = 0; k < w / n; k++) row[k] = 0;
        for (int i = 0; i < n; i++) { // za vsako vrstico v vodoravni vrsti blokov
            for (int j = 0; j < w; j++) { // za vsak piksel v vrstici
                unsigned char r, g, b;
                fscanf(iFile, "%c%c%c", &r, &g, &b);
                row[j / n] += (r + g + b); // prištej rgb k vsoti ustreznega bloka 
            }
        }
        for (int k = 0; k < w / n; k++) // za vsak blok v vodoravni vrstici blokov 
            fprintf(oFile, "%c", grayToChar(row[k] / (3 * n * n))); // izpiši ustrezni znak
        fprintf(oFile, "\n");
    }
    fclose(oFile);
}
