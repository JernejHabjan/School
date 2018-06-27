
#include <stdio.h>

int ax[] = {1, 2, 5, 10, 20};
int n = 5;

int st_nacinov;

void get_sum_kombinacije(int vsota, int max_vsota) {
    if (vsota > n)
        return;
    if (vsota == n) {
        st_nacinov++;
        return;
    }
    for (int i = 0; i < n; i++)
        get_sum_kombinacije(vsota + ax[i], max_vsota);
}


int kombinacije__() {
    get_sum_kombinacije(0, 50);

    printf("da se dobiti na %d nacinov\n", st_nacinov);
    return 0;

}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void swap(char *x, char*y){

    char temp = *x;
    *x= *y;
    *y = temp;
}
void permutations(char *word, int left, int right){

    if(right -left == 0) {printf("%s\n",word); return;}

    for(int i = left; i <=right; i++){

        swap(word+left, word+i);
        permutations(word, left+1, right);
        swap(word+left, word+i);


    }

}

int permutacije__() {

    char word[]="ABC";
    int n = strlen(word);
    permutations(word, 0, n-1);

    return 0;

}