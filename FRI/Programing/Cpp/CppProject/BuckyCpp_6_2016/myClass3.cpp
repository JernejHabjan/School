#define print(x) std::cout<<x<<std::endl
#include "myClass3.h"

#include <string>
#include <iostream>

myClass3::myClass3(int a, int b): regVar(a), constVar(b) // REGVAR IN CONSTVAR STA DEFINANA V HEADERJU POD PRIVATE
{//PAZI VRSTICO ZGORIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
	//tko setaš pr konstruktorju... toj ubistvu regVar=a, constVar=b
	
	std::cout << "this prints myClass3 constructor" << std::endl;

}

void myClass3::printOnScreen(){
	
	std::cout << "reg var is " << regVar << "const var is " << constVar << std::endl;
}

void myClass3::printCrap(){
	std::cout << "neki"<<std::endl;
}

void myClass3::printCrap1() const{ //PAZI TAJ CONSTANT FUNCTION
	std::cout << "constant f" << std::endl; //PAZI DA V HEADER NAPIŠEŠ TUD CONST!!
}

void myClass3::printThis(){ //vse tri stvari izpišejo isto!!! 
	print(regVar);
	print(this->regVar);
	print((*this).regVar); // derreferencing a pointer -takes mem location of object and calls variable
}

myClass3::~myClass3()
{
}
