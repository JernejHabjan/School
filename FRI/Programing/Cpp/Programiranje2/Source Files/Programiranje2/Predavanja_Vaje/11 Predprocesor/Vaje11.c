//
// Created by Jernej Habjan on 9. 03. 2017.
//

#include <stdio.h>

char data[30000];
char code[300000];
char *p;
char *code_ptr;

//http://en.wikipedia.org/wiki/Brainfuck

void execute(char instruction) {
    char c;
    char *start_pos;

    switch (instruction) {
        case '>':
            ++p;
            break;
        case '<':
            --p;
            break;
        case '+':
            ++*p;
            break;
        case '-':
            --*p;
            break;
        case '.':
            putchar(*p);
            break;
        case ',':
            *p = getchar();
            break;
        case '[':
            start_pos = code_ptr;
            if ((*p) == 0) {
                int inner_loops = 0;
                while (1) {
                    ++code_ptr;
                    c = *code_ptr;
                    if (inner_loops == 0 && c == ']') break;
                    if (c == ']') inner_loops--;
                    if (c == '[') inner_loops++;
                }
            } else
                while ((*p) != 0) {
                    code_ptr = start_pos;
                    ++code_ptr;
                    c = *code_ptr;

                    while (c != ']') {
                        execute(c);
                        ++code_ptr;
                        c = *code_ptr;
                    }
                }
        default:break;
    }

}


int vaje_lanske(){

    p = &data[0];
    code_ptr = &code[0];
    char in;
    while (scanf("%c", code_ptr++) != EOF);
    *code_ptr = in;
    *code_ptr = '\0';

    code_ptr = &code[0];
    while (*code_ptr != '\0') {
        execute(*code_ptr);
        ++code_ptr;
    }
    return 0;
}


int vaje_letos();
int vaje11() {

    //vaje_lanske();
    //vaje_letos();

    return 0;
}



////// VAJE LETOS ////////////

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
char* najdiOp(char *izraz) {
    char *c = izraz;
    char *op = NULL;
    //prvo preverimo ce so notri + ali -
    while (*c != '\0') {
        if (*c == '+' || *c == '-') {
            op = c;
        }
        c++;
    }
    if (op != NULL)
        return op;
    //potem preverimo ce so notri * ali /
    c = izraz;
    while (*c != '\0') {
        if (*c == '*' || *c == '/')
            op = c;
        c++;
    }
    return op;
}

int izracunaj(char *izraz) {
    char *op = najdiOp(izraz);
    if (op == NULL) //je stevilka
        return *izraz - '0';
        //atoi
        //return atoi(izraz);

    char operator = *op;
    *op = '\0';
    int levo = izracunaj(izraz);
    int desno = izracunaj(op+1);
    switch (operator) {
        case '+': return levo + desno;
        case '-': return levo - desno;
        case '*': return levo * desno;
        case '/': return levo / desno;
    }

    printf("Neveljaven izraz\n");
    return -1;
    // najdi zadnji operator
    // poracunaj levo, poracunaj desno (rekurzivno)
    // naredi operacijo na rezultatih levo in desno
}


int vaje_letos() {
    char *izraz = malloc(1001 * sizeof(char));
    scanf("%s", izraz);

    printf("%d\n", izracunaj(izraz));

    return 0;
}
