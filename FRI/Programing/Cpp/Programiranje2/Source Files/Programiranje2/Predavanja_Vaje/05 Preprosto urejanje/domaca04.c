//
// Created by Jernej Habjan on 9. 03. 2017.
//

#include <stdio.h>
#include <string.h>

char str[1000];
char checkStr[1000];

void removeDoubleDots() {

    char tmp[1000];
    char prejsni = ' ';
    int counter = 0;
    for (int i = 0; i < strlen(str); i++) {
        char znak = str[i];
        if (znak != '*') {
            tmp[counter] = znak;
            counter++;
            prejsni = ' ';
        } else { //je zvezdica
            if (prejsni != '*') {
                tmp[counter] = znak;
                counter++;
            }
            prejsni = '*';
        }

    }tmp[counter] = '\0';
    printf("%s\n", tmp);

}

int check(int ci1, int ci2) {
    printf("%c %c\n", str[ci1], checkStr[ci2]);
    if (str[ci1] == checkStr[ci2]) return check(ci1 + 1, ci2 + 1);
    if (str[ci1] == '?' && checkStr[ci2] != '\0') return check(ci1 + 1, ci2 + 1);
    if (str[ci1] == '*' && checkStr[ci2] != '\0') return check(ci1, ci2 + 1);

    return 0;
}


int domaca04() {
    //TODO - ustavlo se je pri rekurziji - neznam naprej
    scanf("%s", str);
    removeDoubleDots();
    int n;
    scanf("%d", &n);
    int count = 0;
    for (int i = 0; i < 1; i++) {
        scanf("%s", checkStr);
        if (check(0, 0)) count++;
    }
    printf("%d\n", count);
    return 0;
}
