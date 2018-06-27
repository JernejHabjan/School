#include <stdio.h>
#include <stdlib.h>
#include <string.h>
   
 void izpisiTree(int *ax, int i, int n){
    
    if(i >=n) return;
    printf("%d",ax[i]);
 
    izpisiTree(ax, 2*i+1,n);
    izpisiTree(ax, 2*i+2,n);
 
 }
    
int main(){


    int stevilo;
    scanf("%d", &stevilo);
    
    int potenca = 1;
    for(int i =0 ; i < stevilo; i++){
        potenca*=2;
    }
    int *ax = malloc((potenca-1)*sizeof(int));

    
    for(int i=0; i < potenca-1; i++){
        scanf("%d",&ax[i]);
    }
    

    izpisiTree(ax, 0, potenca);


   
    return 0;
}
