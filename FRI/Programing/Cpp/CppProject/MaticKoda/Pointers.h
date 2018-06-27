#include <iostream>
#include <string>
#include <windows.h>

struct Node{
	Node *m_to;
	std::string m_name;
	std::string m_msg;
	Node(const std::string &name, const std::string &msg) :
		m_name(name), m_msg(msg), m_to(nullptr)
	{
	}

	void printMsg(){
		std::cout << m_name << ": " << m_msg << std::endl;
	}
	void printMsg(const std::string &msg){
		std::cout << m_name << ": " << msg << std::endl;
	}

}*from = nullptr;


int main(){
	Node n1{ "Jernej", "No you!" };
	Node n2{ "Matic", "Yes you?" };
	n1.m_to = &n2;
	n2.m_to = &n1;

	n1.printMsg("You fagget");
	from = n1.m_to;

	while (1){
		from->printMsg();
		from = from->m_to;
		Sleep(500);
	}

	system("PAUSE");
	return 0;
}