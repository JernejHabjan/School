#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <stdbool.h>


int sestevaj(int left, int top, int n, int **ax){
    int vsota =  ax[left][top];
   
    if(left >=n-1 && top >= n-1){

        return vsota;
    }


    int vsotaLeft =0;
    int vsotaTop =0;
    
    

    if(left < n-1){
        vsotaLeft = sestevaj(left+1, top, n, ax);
    
    
    }if(top < n-1){
        vsotaTop = sestevaj(left, top +1 , n, ax);
    }
    
    vsota += vsotaLeft < vsotaTop? vsotaLeft: vsotaTop;
    vsota += vsotaLeft == 0? vsotaTop:0; // ce je ze na meji... pol vrne nic
    vsota += vsotaTop ==0 ? vsotaLeft:0; // ce je na meji pol vrne nic
 
    return vsota;
    
}


int main(){
    int n;
    scanf("%d",&n);
    //alociraj
    
    int **ax = malloc(n*sizeof(int*));
    for(int i = 0; i < n; i++){
        ax[i] = malloc(n*sizeof(int));
        for(int j = 0; j<n;j++){
            scanf("%d", &ax[i][j]);
        
        }
    }
    
    printf("%d\n", sestevaj(0,0, n, ax));
    

    //free
    for(int i =0; i < n; i++){
        free(ax[i]);
   
   
    }free(ax);
    
    
    
    
    
    

    return 0;
}
