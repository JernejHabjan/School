#include <stdlib.h>
#include <stdio.h>
#include <string.h>


char a[10000];
char b[10000];

char sum[10000];
int main(){
    
    
    
    
    scanf("%s %s", a, b);
    
    
    //printf("%s %s", a,b);
    
    
    
    int aL = strlen(a);
    int bL = strlen(b);
    int vecja = aL > bL? aL: bL;
    int manjsa = aL < bL? aL: bL;
     int vecja_index = aL > bL? 1: 0;
    
    //printf("\n\n");
    
    
    aL--;
    bL--;
    
    
    int ostanek = 0;
    for(int i = 0; i < vecja; i++){
        
        
        if(aL < 0){
          break;
        }
        
        if(bL < 0){
        
         break;
        }
        
        
        
        
        int vsota = (a[aL]-'0') + (b[bL] -'0')+ ostanek;
        //printf("vsota %d\n", vsota);
        ostanek = vsota /10; //prva stevilka
        int zapis = vsota%10; //zadnja stevilka
      
        
        
        sum[i] = zapis+'0';
        
        
        
        //printf("%d\n", zapis);
        
        aL--;
        bL--;
    
    }
    
    //sestevamo se prejsne
    for(int i = manjsa; i< vecja; i++){
        if (vecja_index){
        
            int vsota = (a[aL]-'0') +  ostanek;
            int ostanek = vsota /10; //prva stevilka
            int zapis = vsota%10; //zadnja stevilka
            sum[i] = zapis+'0';
            aL--;
            
            
        }else{
            int vsota = (b[bL]-'0') +  ostanek;
            int ostanek = vsota /10; //prva stevilka
            int zapis = vsota%10; //zadnja stevilka
            sum[i] = zapis+'0';
            bL--;
            
        
        }
        
    }
    
    //mogoce ce overflowa
    
    if(ostanek != 0){
    
     sum[strlen(sum)]  =1+'0';
    }
    sum[strlen(sum)+1]='\0';
        
    
    
    
    int len = strlen(sum);
    
    for(int i = len-1; i >= 0; i--){
     printf("%c",(sum[i]));
    
    }

    
    return 0;
}
