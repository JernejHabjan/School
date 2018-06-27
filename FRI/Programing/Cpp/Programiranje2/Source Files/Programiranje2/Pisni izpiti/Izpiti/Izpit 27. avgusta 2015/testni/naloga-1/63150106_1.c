#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

typedef long long llong;

int main(){
    
    int ststevk, n;
    scanf("%d %d", &ststevk, &n);
    
    char *ax = malloc(ststevk * sizeof(char));
    scanf("%s", ax);
    
    llong max = 0;
    for(int i = 0; i < ststevk; i++){
       
        llong product = 1;
        //gremo cez vsa stevila in vzemamo po n
        for(int j = i; j < i+n && j < ststevk; j++){
        
            int stevka = ax[j] - '0';
            product *=stevka;
        } if(product >= max){
   
            max = product;
        }
    
    
    }
    printf("%lli\n", max);
    
    
    return 0;
}
