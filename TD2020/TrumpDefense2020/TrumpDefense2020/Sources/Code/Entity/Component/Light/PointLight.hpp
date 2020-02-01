#pragma once

//include parent
#include "Light.hpp"

class PointLight : public Light {
private:
	void sendPropertiesToShaderPoint();
public:
	PointLight(glm::vec3 position, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular, float constant, float linear, float quadratic);
	PointLight();
	~PointLight();

	void init(APIuint programID, const GLchar* positionName, const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName, const GLchar* constantName, const GLchar* linearName, const GLchar* quadraticName);
	void sendPropertiesToShader();
	void sendPropertiesToShader(float x, float y, float z);
	void sendPropertiesToShader(glm::vec3 position);
};

