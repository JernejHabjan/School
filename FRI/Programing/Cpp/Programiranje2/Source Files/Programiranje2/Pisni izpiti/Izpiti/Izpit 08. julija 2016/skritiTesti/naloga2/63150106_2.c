#include <stdio.h>
#include <stdlib.h>
#include <string.h>
   

    
int main(){
    
    
    int p,q,d;
    scanf("%d %d %d",&p, &q, &d);

    //alloc tabelo
    
    int **ax = malloc(p*sizeof(int*));
    for(int i = 0; i < p; i++){
        ax[i] = calloc(q, sizeof(int));
    }
    
    
    
    //preberi stevila
    for(int i =0; i < p; i++){
    
        for(int j = 0; j <q; j++){
              scanf("%d",&ax[i][j] ); 
        }
    
    }
    
    
    //izpis
    /*
    for(int i =0; i < p; i++){
    
        for(int j = 0; j <q; j++){
             printf("%d",ax[i][j]); 
        }printf("\n");
    
    }
    */
    
   int celica;
    for(celica = 0; celica< (p*q)-1 && d>0; celica++){
         //find_celico
        int tmp_celica = celica; 
        int tmp_koord_x = 0;
        int tmp_koord_y = 0;
        
       
        for(int i =0; i < p; i++){
            
            for(int j = 0; j <q; j++){
                if(ax[i][j] == tmp_celica){
                    tmp_koord_x =i;
                    tmp_koord_y = j;
                }
            
            }
        
        }
        
        //find naslednjo celico;
        
       int tmp_celica_next = celica+1; 
        int tmp_koord_x_next = 0;
        int tmp_koord_y_next = 0;
        
       
        for(int i =0; i < p; i++){
            
            for(int j = 0; j <q; j++){
                if(ax[i][j] == tmp_celica_next){
                    tmp_koord_x_next =i;
                    tmp_koord_y_next = j;
                }
            
            }
        
        } 
        
        //izracunaj pot:
      
        int razlika_x = tmp_koord_x_next > tmp_koord_x? tmp_koord_x_next - tmp_koord_x:tmp_koord_x - tmp_koord_x_next;
        int razlika_y = tmp_koord_y_next > tmp_koord_y? tmp_koord_y_next - tmp_koord_y:tmp_koord_y - tmp_koord_y_next;
        
        int total_razlika = razlika_x+razlika_y;
        //printf("%d\n",razlika_x+razlika_y);
        d-=total_razlika;
     
    
    }
    //koncen izpis
    printf("%d\n", celica-1);
    



   //free
   for(int i = 0; i <p; i++){
        free(ax[i]);
    }free(ax);
   
    return 0;
}
