#define _USE_MATH_DEFINES
#define print(x) std::cout<<x<<std::endl
#include <windows.h>
#include <iostream>
#include <algorithm>
#include <vector>
#include <fstream>
#include <string>
#include <cmath>
#include <ctime>
#include <array>

#include "Sally.h"
#include "myClass3.h" //includaš sosedn class
#include "Mother.h"
#include "Daughter.h"

//NOTES Matic:
	//for(int &i : vector) -da spreminjaš value že v iteracij
	//const std::vector<int> &vec kot parameter d ne delaš kopije

//STATIC ARRAY
void arrayStatic(){
	int myArray[10];
	int myArray2[10] = { 1, 2, 4 };

	std::array<int, 20> neki; //MUST INCLUDE ARRAY
}
//#######################

//SET
int a[] = { 1, 9, 4, 5, 8, 3, 1, 3, 5 };
const size_t len = sizeof(a) / sizeof(a[0]);

std::unordered_set<int> s (a, a + len);

std::cout << s.size() << std::endl;

//########################

void arrayParameter(int array1[]){
	//samo primer da se tako poda kot parameter
}
//########################

//MULTIDIMENSIONAL ARRAY
void multiDimensionalArray(){ //PRVA ŠTEVILKA JE ŠT VRSTIC,,, druga pa št elementov v vrstic
	int neki[3][3] = { 
	{ 1, 2, 3 },
	{ 4, 5, 6 },
	{ 7, 8, 9 } };
	print(neki[1][2]);
}
//########################

//PASSBYREFERENCE
void passByReference(int *x){ //SPREMENI ORG VALUE NA POMNILNIKU NA 333
	*x = 333;
}
//########################

//POINTERS
void pointerLession1(){ //ko passaš funkciji reference passaš direktn naslov in ne kopije
	int fish = 5;
	int *fishPointer = &fish;

	passByReference(fishPointer);  //SPREMENI NA POMNILNIKU
	print(fish);
}
void pointerLession2(){
	int arr[3];
	arr[0] = 4;
	int *pt1 = &arr[0];
	int *pt2 = &arr[1];
	int *pt3 = &arr[2];



	std::cout << *pt1 << std::endl;//TO JE ELEMENT
	std::cout << pt1 << std::endl;//TO JE POINTER NA ELEMENT


	pt1 += 2; // PRIŠTEJE NASLOVU DVA VALUA INTEGERJA... PREMAKNE NASLOV ZA 8... aka 2x po 4B

	print(std::to_string(*pt1) + " " + std::to_string(*pt3));  // in potem kaže pointer na ista elementa... na idexu 2
}
//########################

//ITERATOR
std::vector<float> x;
for (std::vector<float>::iterator it = x.begin(); it != x.end(); it++){
	std::cout << *it << std::endl;
}
//########################

//SIZEOF
void sizeOfTest(){
	print(sizeof(int));
	//sizeOfArray:
	double bucky[10];
	print(sizeof(bucky) / sizeof(bucky[0]));
}
//########################

//MIN MAX 
void minMax(){
	int list[6] = { 10, 4, 7, 8, 33, -2 };
	int arrayLength = (sizeof(list) / sizeof(list[0]));
	print(*std::min_element(list, list + arrayLength)); //POINTER??????
	print(*std::max_element(list, list + arrayLength));
}
//########################

//VECTOR
class MyClasskrneki{};
void dynamicArrayVector(){
	std::vector<int> array; // OR std::vector<int> array(10);
	int i = 999;          // some integer value
	array.reserve(10);    // make room for 10 elements
	array.push_back(i);
	array.push_back(31);
	print(array.capacity());
	print(array.size());
	print(array[1]); //on index

	std::vector<MyClasskrneki>array1; //can contain your class
}
//########################

//READER
void reader(){
	std::string line;
	std::ifstream myfile("beef.txt");
	if (myfile.is_open())
	{
		while (getline(myfile, line))
		{
			print(line);
		}
		myfile.close();
	}

	else print("Unable to open file");
}
//########################

//WRITER
void writer(){
	std::ofstream myFile;
	myFile.open("tuna.txt");
	myFile << "this is written in tuna.txt file.\n";
	myFile.close();
}
void writer2(){
	std::ofstream myFile("beef.txt");
	if (myFile.is_open()){ //if object myFile is associated with beef.txt
		std::cout<<"enter id, name, money\n";
		std::cout << "press ctrl+Z to quit\n";

		int idNumber;
		std::string name;
		double money;

		while (std::cin >> idNumber >> name >> money){ //PAZI TO!!!!! TOJ SICK
			myFile << idNumber <<" "<<name <<" "<< money;
		}
	}
	else{
		print("u messed up");
	}
	
	myFile.close();
}
//READ STRUKTURO:
void reader2(){
	std::ifstream myFile("beef.txt");

	int idNumber;
	std::string name;
	double money;
	while (myFile >> idNumber >> name >> money){
		std::cout << idNumber << " " << name << " " << money << std::endl;
	}
	myFile.close();
}

//PROTOTYPING
double circleSurface(int); // --- prototypaš lahko brez imen (int a) ampka samo (int)
//########################

//PRINTF
void printfTest(){
	printf("floats: %4.2f %+.0e %E \n", 3.1416, 3.1416, 3.1416); //E in e????? PRINTF
}
//########################

//SIMPLE CLASS
class MyClass1{
public:
	MyClass1(std::string name1){
		setName(name1);
	}
	void simplePrint(){
		std::cout << "neki krneki" << std::endl;
	}
	void setName(std::string name1){
		this->name1 = name1;
	}
	std::string getName(){
		return name1;
	}
private:
	std::string name1;
};
//########################

//STRING FUNCTIONS ->assign, at
void str(){
	std::string s1("hampster");
	std::string s2;
	s2.assign(s1); // je isto kt s2=s1;

	s2.at(1); //NA INDEXU
}
//########################

//SUBSTRING
void substringFunction(){
	std::string a = "lolgetrekt";
	print(a.substr(2, 3)); //od druzga dolžine 3
}
//########################

//SWAP
void swapper(){
	std::string a = "lolgetrekt";
	std::string b = "neki";
	a.swap(b); //jih zamena
}
//########################

//FIND
void findFunction(){
	std::string a = "lolgetrekt";
	a.find("ge"); //vrne na kermu indexu
	a.rfind("ge"); //reverse
}
//########################

//REPLACE INSERT
void replaceFunction(){
	std::string a = "lolgetrekt";
	a.replace(3, 6, "ge"); //kje začet, koliko replacat, s čim
	a.insert(3, "neki");
}
//########################

//RANDOM
void randomNum(){
	std::cout << rand() << std::endl; // return value between 0 and 65k
	int randNum = (rand() % 3) + 1; //DIVIDED BY 3... VRNE OD 0-2 IN PLUS 1... OD 1-3

	//TRUE RANDOM:
	//ZAENKRAT ZAKOMENTIRAN
	//srand(time(0)); //TRULLY RANDOM
	randNum = (rand() % 3) + 1; //morš dat to po srand in pol izpišeš
}
//########################

//ARROW SELECTION OPERATOR -> (calling method by pointer)
void arrowMemberSelectionOperator(){
	myClass3 object(2,3);
	
	myClass3 *objectPointer = &object; //POINTER NA OBJECT Z ISTMU DATA TYPOM KT CLASS

	objectPointer->printCrap();   //DA KLIČEŠ METODO S POINTERJEM DAŠ -> !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	object.printCrap();
}
//########################

// CONST
void constObjects(){
	myClass3 object(2, 3);
	object.printCrap();

	const myClass3 constObject(2, 3);
	constObject.printCrap1();
}
//########################

//FRIEND
class Friend{ //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
public:
	Friend(){ var = 0; }
private:
	int var;
	friend void friendsFriend(Friend &obj);
};
void friendsFriend(Friend &obj){
	obj.var = 99;
	print(obj.var);
}
//########################

//POLYMORPHISM
class Enemy{
protected:
	int attackPower;
public:
	void setAttackPower(int a){
		attackPower = a;
	}
	virtual void attack() = 0; // daj pure virtual daš =0

};
class Ninja: public Enemy{
public:
	void attack(){
		print("hit: "+attackPower);
	}
};
class Monster : public Enemy{
public:
	void attack(){
		print("boom: " + attackPower);
	}
};
//########################

//TEMPLATE
template <class FIRST, class SECOND>
FIRST addCrap(FIRST a, SECOND b){ //če kličeš funkcijo pa maš notr doubla.. 
	//zamena besedo "SECOND AL FIRST" z "double"
	return (a + b);
}
//########################

//TEMPLATE EXAMPLE 2
template <class T> 
class Bucky1{
	T first, second;
public:
	Bucky1(T first, T second){
		this->first = first;
		this->second = second;
	}
	T bigger();
};
template <class T>
T Bucky1<T>::bigger(){
	return (first > second ? first : second);
}
template<>
class Bucky1<char>{ //SPERIALIZED TEMPLATE SAMO ZA CHARACTERJE
public:
	Bucky1(char x){
		std::cout <<x<< " is indeed a char"<<std::endl;
	}
};
//########################

//TRY EXCEPT
void trySomething(){
	try{
		int momsAge = 30;
		int sonsAge = 20;
		if (sonsAge > momsAge){
			throw 99;
		}
	}
	catch(int x){
		std::cout << "son cannot be older than mom, err num:" << x << std::endl;
	}
	catch (...){ //TO UJAME VSE ERROR MESSAGE
		std::cout << "this catches ALL other errors" << std::endl;
	}
}
//########################



int main(){

	//minMax();
	//dynamicArrayVector();
	//int a = 5; pointerReference(&a);
	//reader();
	
	//circleSurface(2);

	//INCREMENT
	/*
	int x = 2;
	std::cout << ++x << std::endl; //PRVO INCREMENTA IN POTEM IZPIŠE
	*/
	//TESTIRANJE CLASSA
	/*
	myClassObject.simplePrint();
	MyClass1 myClassObject("micka");
	print(myClassObject.getName());
	
	myClassObject.setName("jernej");
	print(myClassObject.getName());
	*/

	//myClass3 neki(2,3);


	//multiDimensionalArray();


	//POINTERS:
	//pointerLession1();
	//pointerLession2();


	//arrowMemberSelectionOperator(); //TOJ FUCKED

	//KONSTRUKTOR Z MEMBER INITIALIZERJEM -> MyClass(int a): var1(a){
		//myClass3 neki(2, 3);
		//neki.printOnScreen();

	//FRIEND POGLEJ SI TUTORIAL 48
		//Friend obj;
		//friendsFriend(obj); //IN POL IZPIŠE 99
			//class has to give away friendship... d lah uporablaš obj kot parametre funckcije

	//OPERATOR PLUS OVERLOAD:
		/*Sally a(12);
		Sally b(23);
		Sally c;
		c = a + b;
		print(c.num);*/

	//POLYMORPHISM IN VIRTUAL FUNCTIONS--- PAZI SE DELA S POINTERJI PA ->
		/*Ninja n;
		Monster m;
		Enemy *enemy1 = &n;
		Enemy *enemy2 = &m;
		enemy1->setAttackPower(20);
		enemy2->setAttackPower(30);
		n.attack();
		m.attack();

		//če ima pa že enemy VIRTUALNO FUNKCIJO attack pa lah kličemo attack z enemy1 in enemy2
		enemy1->attack();
		enemy2->attack();*/

	//template
		/*Bucky1<int> bo(2, 3); //PAZI NA TA INT
		print(bo.bigger());
		Bucky1<double> bo1(2.3, 3.4); //TA DATATYPE MORŠ NAPISAT!!!
		print(bo1.bigger());*/

	//specializing templates
		//Bucky1<char> bo2('A');
	
	//writer:
		//writer();
		//writer2();
	reader2();


	//system("pause");
	return 0;
}

//PI AND TO_STRING
double circleSurface(int r = 1){ //DEFAULT PARAMETER =1!!!!
	M_PI;
	std::string s = "Hello";
	int neki = 4;
	std::string greet = s + " World " + std::to_string(neki); //TOSTRING


	print(greet);
	return M_PI*pow(r, 2); //PAZI DEFINE _USE_MATH_DEFINES pa include <cmath>
}


//GLOBAL VARIABLE (::x)
// std::cout << ::x<<std::endl;