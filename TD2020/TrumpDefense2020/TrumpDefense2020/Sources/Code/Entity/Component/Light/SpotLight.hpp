#pragma once
//include parent
#include "Light.hpp"

class SpotLight : public Light {
private:
	void sendPropertiesToShaderSpot();
public:
	SpotLight();
	SpotLight(glm::vec3 direction, glm::vec3 position, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular, float constant, float linear, float quadratic, float cutOff, float outerCutOff);
	~SpotLight();
	void init(APIuint programID, const GLchar* directionName, const GLchar* positionName, const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName, const GLchar* constantName, const GLchar* linearName, const GLchar* quadraticName, const GLchar* cutOffName, const GLchar* outerCutOffName);
	void sendPropertiesToShader();
	void sendPropertiesToShader(float x, float y, float z);
	void sendPropertiesToShader(glm::vec3 position);
};

