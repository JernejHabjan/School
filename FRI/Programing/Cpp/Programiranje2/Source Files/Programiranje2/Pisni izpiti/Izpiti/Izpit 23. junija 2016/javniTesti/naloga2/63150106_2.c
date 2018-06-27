#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int  SIZE= 1001;

void bubblesort(int *neki, int n){

    for(int i =0; i <n; i++){
    
        for(int j = 0; j < n; j++){
        
            if(neki[i] > neki[j]){
                int temp = neki[i];
                neki[i] = neki[j];
                neki[j] = temp;
           
            
            
            }
        }
    }

}

int main(){
    int n;
    scanf("%d",&n);
  
    //allocate
    int **ax;
    ax = (int **) calloc(SIZE, sizeof(int *));
    for(int i =0; i < SIZE; i++){
        ax[i] = (int *) calloc(SIZE, sizeof(int));
        ax[i]+=SIZE/2;
    
    }
    ax += SIZE /2;
    
    
    
    
    int maxX = -SIZE/2;
    int minX = +SIZE/2;
    int maxY = -SIZE/2;
    int minY = +SIZE/2;
    //preberemo
    for(int i = 0; i < n; i++){
    
    
        int x,y,d;
        scanf("%d %d %d", &x, &y, &d);
        
        for(int ma 3i = x; i < x+d; i++){
        
            for(int j = y ; j < y+d; j++){
                if(i < minX) minX = i;
                if(i > maxX) maxX = i;
                if(j < minY) minY = j;
                if(j > maxY) maxY = j;
                
                
                
                ax[i][j]++;
           
            }   
        
        }
        
        
    
    }
    int *neki = calloc(n, sizeof(int));
    
    
    
    //prestejemo
    for(int i = minX; i <= maxX; i++){
         for(int j = minY; j <= maxY; j++){
            if(ax[i][j] != 0){
                neki[ax[i][j]]++;
         
            }
            
           
        }printf("\n");
        
    
    }
    bubblesort(neki,n);
    for(int i = 0; i < n; i++){
    
        printf("%d\n",neki[i]);
    
    }
    
    
    
    
    
    
    
    
    //free
    ax-=SIZE/2;
    for(int i = 0; i < SIZE; i++){
        ax[i]-=SIZE/2;
        free(ax[i]);
    }
    free(ax);
    
}
