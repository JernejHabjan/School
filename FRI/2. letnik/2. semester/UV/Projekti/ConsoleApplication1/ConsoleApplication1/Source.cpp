
#include <stdlib.h>
#include <stdio.h>



int bla(int a, int b){

	int vsota = a + b;
	return vsota;


}


void bla1(int a, int b, int &vsota){

	vsota = a + b;

}


void bla2(int a, int b, int *vsota){

	*vsota = a + b;

}

int main(int argc, char *argv[]){

	int vsota = bla(2, 3);
	printf("%d\n", vsota);


	int vsota1 =0;
	bla1(2, 3, vsota1);
	printf("%d\n", vsota1);

	int vsota2 = 0;
	int *vsota2PTR = &vsota2;
	bla2(2, 3, vsota2PTR);
	printf("%d\n", vsota2);



	system("PAUSE");
	return 0;
}