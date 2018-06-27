#include "Sally.h"


Sally::Sally()
{
}

Sally::Sally(int a){
	num = a;
}
Sally Sally::operator+(Sally obj){ //PAZI TO!!!!!!!!!!!!@@@@@@@@
	Sally brandNew;
	brandNew.num = num + obj.num;
	return(brandNew);
}