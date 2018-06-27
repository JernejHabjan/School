#include <stdlib.h>
#include <stdio.h>
#include <string.h>


void bubblesort(int *ax, int n){

    for(int i = 0; i < n ; i++){
    
        for(int j = 0; j < n ; j++){
    
            if(ax[i] > ax[j]){
                int temp  = ax[i];
                ax[i] = ax[j];
                ax[j] = temp;
            } 
    
       }
    
    }

}


int main(){
    int n;
    scanf("%d",&n);
    
    int *ax;
    ax = malloc(n* sizeof(int));
    
    
    for(int i = 0; i < n; i++){
        int stevilo;
        scanf("%d",&stevilo);
        
        ax[i] = stevilo;
        
    
    }
    bubblesort(ax,n);
    

    int najd_dolz_zap =0;
    for(int i = 0; i< n; i++){
    
        int tmp_stevilo = ax[i];
    
    
        int dolzina_zap = 0;
        for(int j = i; j < n; j++){
            if((tmp_stevilo % ax[j] ) == 0){
                
                dolzina_zap++;
                tmp_stevilo = ax[j];
                
                
                //printf("ta %d deli %d\n",ax[i] ,ax[j]);
            }
        
        }//printf("%d\n", najd_dolz_zap);
        if(najd_dolz_zap < dolzina_zap)
            najd_dolz_zap = dolzina_zap;
            //printf("\n");
    
    }printf("%d\n",najd_dolz_zap);
    
    
    
    
    
    
    free(ax);
    
    
    
    
    
    
    
}
