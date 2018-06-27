#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <numeric>
#include <algorithm>
#include <chrono>

namespace Names{
	const std::vector<std::string> name{ "Tori", "Alexis", "Mia", "Madison", "August", "Nicole" };
	const std::vector<std::string> surname{ "Black", "Texas", "Malkova", "Ivy", "Ames", "Aniston" };

	std::string getName(){
		return name[rand() % name.size()];
	}

	std::string getSurname(){
		return surname[rand() % surname.size()];
	}
}

class Student{
private:
	std::string m_name, m_surname;
	std::vector<short> m_grades;

public:
	Student(){}
	Student(const std::string &name, const std::string &surname, const std::vector<short> &grades) :
		m_name(name), m_surname(surname), m_grades(grades)
	{
	}

	const short getGrade() const{
		return std::accumulate(m_grades.begin(), m_grades.end(), 0);
	}
};

Student newStudent(){
	const unsigned int NUM_GRADES = 10;

	std::vector<short> grades(NUM_GRADES);
	std::string name = Names::getName();
	std::string surname = Names::getSurname();

	std::generate(grades.begin(), grades.end(), [](){
		return (rand() % 6) + 5;
	});

	return Student{ name, surname, grades };
}

float getGradeAvg(const std::vector<Student> &students){
	return std::accumulate(students.begin(), students.end(), 0.0f, [](float i, const Student &student){
		return student.getGrade() + i;
	}) / students.size();
}

int main(){
	srand(time(0));

	std::chrono::high_resolution_clock::time_point start = std::chrono::high_resolution_clock::now();

	const unsigned int NUM_STUDENTS = 1000000;
	std::vector<Student> A1(NUM_STUDENTS), B1(NUM_STUDENTS);

	for (std::size_t i = 0; i < NUM_STUDENTS; ++i){
		A1[i] = newStudent();
		B1[i] = newStudent();
	}

	float avgA1 = getGradeAvg(A1), avgB1 = getGradeAvg(B1);

	std::cout << "avg A: " << avgA1 << " ||| avg B: " << avgB1 << std::endl;
	std::cout << (getGradeAvg(A1) > getGradeAvg(B1) ?
		"Razred A1 ima vecje povprecje!" : "Razred B1 ima vecje povprecje")
		<< std::endl;

	std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
	auto duration = std::chrono::duration_cast<std::chrono::microseconds>(end - start);
	std::cout << "Time: " << duration.count() << "us" << std::endl;

	system("PAUSE");
	return 0;
}