#pragma once
class myClass3
{
public:
	myClass3(int a, int b);
	void printOnScreen();
	void printCrap();
	void printCrap1() const;
	void printThis();
	~myClass3();
private:
	int regVar;
	const int constVar;
};

