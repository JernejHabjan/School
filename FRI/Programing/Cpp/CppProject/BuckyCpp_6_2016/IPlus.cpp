#include <iostream>
#include <limits> 

class IPlus{
protected:
	int width, height;

public:
	IPlus::IPlus(int a = 0, int b = 0) :width(a), height(b){} //default value    //PAZI INITIALIZOR LIST

	IPlus::~IPlus(void){}
	virtual void neki() = 0;

	void neki2();
};

void IPlus::neki2(){ //pazi void pred tem.. pa prototypana v classu
	std::cout << 'lol' << std::endl;
}

class Jelly : public IPlus{

public:
	Jelly(int a = 0, int b = 0) :IPlus(a, b) { } //PAZI TA KONSTRUKTOR
	void neki(){
		std::cout << "lol" << std::endl;
		std::cout << height << std::endl;
	}
	int getHeight(){
		return height;
	}


};

template <class T>
T neki(T x, T y){
	T result;
	result = x>y ? x : y;
	return result;
}




template <class T, class U>
T neki1(T x, U y){

	T result;
	result = x - y;

	return result;

}
int main(){

	IPlus *IPlus;
	Jelly jelly(2, 3);


	IPlus = &jelly;
	IPlus->neki();
	std::cout << jelly.getHeight() << "REKT" << std::endl;

	int i = 5, j = 6, k;
	long l = 10, m = 5, n;
	k = neki<int>(i, j);
	n = neki<long>(l, m);
	std::cout << k << std::endl;
	std::cout << n << std::endl;

	float b = 30.0f;
	n = neki1<int, float>(i, b);
	std::cout << n << "LOOOOOOOOOOOOOOOOOOOOOOOO" << std::endl;

	std::cout << "lol" << std::endl;
	short neki = 5;
	short int neki1 = 5;
	std::cout << sizeof(short) << std::endl;
	std::cout << sizeof(short int) << std::endl;
	std::cout << sizeof(int) << std::endl;
	std::cout << sizeof(long) << std::endl;
	std::cout << sizeof(long int) << std::endl;
	std::cout << sizeof(long long) << std::endl;

	std::cout << "Minimum value for int: " << std::numeric_limits<int>::min() << '\n';
	std::cout << "Maximum value for int: " << std::numeric_limits<int>::max() << '\n';

	std::cout << "Minimum value for short: " << std::numeric_limits<short>::min() << '\n';
	std::cout << "Maximum value for short: " << std::numeric_limits<short>::max() << '\n';

	std::cout << "Minimum value for short int: " << std::numeric_limits<short int>::min() << '\n';
	std::cout << "Maximum value for short int: " << std::numeric_limits<short int>::max() << '\n';

	std::cout << "Minimum value for unsigned int: " << std::numeric_limits<unsigned int>::min() << '\n';
	std::cout << "Maximum value for unsigned int: " << std::numeric_limits<unsigned int>::max() << '\n';

	std::cout << "Minimum value for long long: " << std::numeric_limits<long long>::min() << '\n';
	std::cout << "Maximum value for long long: " << std::numeric_limits<long long>::max() << '\n';


	return 0;
}