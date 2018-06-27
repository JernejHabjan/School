#include <stdlib.h>
#include <stdio.h>
#include <string.h>
int obiskanih(int *obiskana, int n){
    int obiskanih = 0;
    for(int i =0; i < n; i++){
        obiskanih += obiskana[i];
    
    
    }return obiskanih;

}


int poisci(int *ax, int *obiskana, int n, int trenutno, int a, int b){
    //oznaci da je obiskana
    obiskana[trenutno] = 1;


    //izracunaj area za skoct na nov
    int minPlus = trenutno + a;
    int minMinus = trenutno - a;
    int maxPlus = trenutno + b;
    int maxMinus = trenutno -b;
 
    int nacinov = 0;
 
 
    
 
    for(int i = 0; i < n; i++){
        
        //preveri ce sem lahko skocis - dovoj dolg skok
        if( (i>=minPlus && i <=maxPlus) || (i <= minMinus && i>= maxMinus) ){
            //preveri ce sem se nisi skocil
            if (obiskana[i] ==0){
            
            
                nacinov+=  poisci(ax, obiskana, n, i, a, b);
            
            
            
            }    
            
            
            
        
        } 
    
    
    }
    
    
    //preveri ustavitveni pogoj:
    
    if(obiskanih(obiskana, n) == n){
        nacinov++;
       
    }
    
    
    //odznaci
    obiskana[trenutno] = 0;
 
 
 
    return nacinov;


}




int main(){

    int n, a,b;
    scanf("%d %d %d", &n, &a, &b);
    
    int *ax = malloc(n*sizeof(int));
    int *obiskana = calloc(n, sizeof(int));
    
    for(int i = 0; i < n; i++){
        ax[i] = i;
    
    }
    
  
    
    int trenutno = 0;
    int nacinov = poisci(ax, obiskana, n, trenutno, a, b);
    printf("%d\n",nacinov);
    
    
    
    
    
    
    free(ax); free(obiskana);


    return 0;
}
