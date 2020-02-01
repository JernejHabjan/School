#pragma once
//include libs
#include <vector>
#include <glm\glm.hpp>

class Component;
class Entity
{
private:
	glm::uint8 classId;
	std::string name;
	

	glm::vec3 coordinates;
	bool isStatic;
	
	

	std::vector<Component> components;
public:
	Entity();
	~Entity();

	void rotateEntity();
	void translateEntity();


};

