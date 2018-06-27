#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>


bool isPrime(int n){
    for(int i = 2; i <n; i++){
        
            if(n %i ==0){
                return false;
            }
        
        }return true;

}




int findPrime(int n){
    
    
    int stevilo = n;
    
    while(true){
    
        //pogleda vsa stevila do tu ce delijo
        
        if(isPrime(stevilo))
        return stevilo;
        

        stevilo++;
        
    
    
    
    }
    
    
    
    


}

int main(){

    int n,k;
  
    
    scanf("%d %d", &n, &k);
    //printf("%d %d", n,k);  
    
    int stevilo = n;
    for(int i = 0; i <k; i++){
        
        stevilo ++;
        
        
        stevilo =findPrime(stevilo);
        
    
    
    }   
    
    
    
    printf("%d\n", stevilo);


    return 0;
}
