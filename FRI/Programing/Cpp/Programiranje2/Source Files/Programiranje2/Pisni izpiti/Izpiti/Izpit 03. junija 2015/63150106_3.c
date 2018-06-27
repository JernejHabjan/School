#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int poisci(int *ax, int i, int n, int vsota, int nivo){

    
    if(i>n){
        printf("%d", vsota);
        exit(0);
    }
    
    
    int next_index_left = i + nivo;
    int next_index_right = i + nivo+1;
    
    
    int vecji = ax[next_index_left]>ax[next_index_right]? 1: 0;
    
    
    vsota += vecji==1? ax[next_index_left]:ax[next_index_right];
    
    
    int vecji_index = vecji==1? next_index_left:next_index_right;
    poisci(ax, vecji_index, n, vsota, nivo+1);
    
  
    

}

int main(){

    int n  =4;
    
    
    int stevil = 0;
    for(int i = 1; i <= n; i++){
        stevil +=i;
    
    }
    
    int *ax = malloc(stevil * sizeof(int));
    
    
    
    //fill
    for(int i = 0; i < stevil; i++){
        scanf("%d", &ax[i]);
    }
    
    
    
    
    
    
    
    
    poisci(ax,0,n, ax[0], 1);
    
    
    free(ax);
    
    
    
    
    
    
    printf("lol");
    return 0;
    

}
