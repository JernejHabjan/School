#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

char tmp_str1[100000];
char tmp_str2[100000];



int main(){
    
    char *stevkeM = calloc(10, sizeof(char));
    char *stevkeNEW = calloc(10, sizeof(char));
    
    
    
    int m = 2;
    
    int stevilo =1;
    while(true){
           
       sprintf(tmp_str1, "%d", stevilo);
       sprintf(tmp_str2, "%d", stevilo*m);
           
        
        
        printf("%s %s", tmp_str1, tmp_str2);
        
        
        //fill stevke
        
        
        int len1 = strlen(tmp_str1);
        int len2 = strlen(tmp_str2);
        
        
        
        
        
        for(int i = 0; i < len1; i++){
            int stevilo = tmp_str1[i]-'0';
            stevkeM[stevilo] = '1';
        }
        
        for(int i = 0; i < len2; i++){
            int stevilo = tmp_str2[i]-'0';
            stevkeNEW[stevilo] = '1';
        }
        
        
        printf("stevke1 %s\n",stevkeM);
        printf("stevke2 %s\n",stevkeNEW);
        
        int ni = 1;
        for(int i =0; i < 10; i++){
            
            if(tmp_str1[i] ^ tmp_str2[i] == 0){
                ni = 0;
            
            }
            
        
        }
        if(ni ==0){
            printf("%d", stevilo);
            return 0;
        }
       
       
        
        
        
        
    
    
    }
    
    
    //free
    free(stevkeM);
    free(stevkeNEW);
    
    return 0;
    

}
