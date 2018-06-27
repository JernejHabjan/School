#include <stdio.h>
#include <stdlib.h>
#include <string.h>
   
    char a[1000];
    char b[1000];
    
   
    
int main(){

   int a_i, b_i;
   scanf("%d %d",&a_i, &b_i);
    sprintf(a,"%d",a_i);
   sprintf(b,"%d",b_i);
   
   
   
   printf("%s %s\n",a,b);
   
   
   int *stevke;
   stevke = calloc(10,sizeof(int));
  
   for(int i = 0; i < strlen(a); i++){
        int stevilo = a[i]-'0';
       
        stevke[stevilo] = 1;
   
   }
   
   for(int i = 0; i < strlen(b); i++){
        int stevilo = b[i]-'0';
       
        stevke[stevilo] = 1;
   
   }
   
   
   int sum=0;
   for(int i = 0; i < 10; i++){
    if(stevke[i])sum++;
   
   }
   printf("%d\n",sum);
   
   
    return 0;
}
