#pragma once
//include parent
#include "Light.hpp"
class DirLight : public Light {
private:
	void sendPropertiesToShaderDir();
public:
	DirLight();
	DirLight(glm::vec3 direction, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular);
	~DirLight();
	void init(APIuint programID, const GLchar* directionName, const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName);
	void sendPropertiesToShader();
	void sendPropertiesToShader(float x, float y, float z);
	void sendPropertiesToShader(glm::vec3 position);
};

