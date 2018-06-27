#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef long long llong;

int main(){
    
    int p, q, k;
    scanf ("%d %d %d", &p, &q, &k);
 
    llong product = p*q;
 
    char str[10];
    sprintf(str, "%lli", product);

    llong vsota = 0;
    int i;
    for(i = 0; i < k; i++){
        int tmp_vsota = 0;
        int j;
        for(j = 0 ; j < strlen(str); j++){
        
            int bla = str[j] - '0'; 
            tmp_vsota+=bla;
        
        }   
        vsota = tmp_vsota;
        sprintf(str, "%lli", vsota);       
    
    }
    printf("%s\n", str);
    return 0;
}
