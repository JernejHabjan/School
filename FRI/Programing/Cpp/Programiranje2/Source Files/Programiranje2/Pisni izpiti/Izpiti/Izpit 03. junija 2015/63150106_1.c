#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <math.h>

int main(){

    int dolzina = 120;
    
    
    float a = 0;
    float b = 0;
    float c = 0;
    
    int bla=0;
    for(float i = 1; i < dolzina-1; i++){
        for(float j = 1; j < dolzina-1; j++){
        
            c = sqrt((i*i) + (j*j));
            if((c+i+j) ==dolzina) {
                printf("%f %f %f\n",c,i,j);
                bla++;
            }
        }   
    
    }printf("%d\n",bla/2);
    
    
    
    

}
