#pragma once

//include libs
#include <glm\glm.hpp>

//include files
#include "..\..\..\Renderer\GL_API.hpp"
class Light {
protected:
	APIuint programID;
	APIint lightPositionID;
	APIint lightAmbientID;
	APIint lightDiffuseID;
	APIint lightSpecularID;
	APIint lightConstantID;
	APIint lightLinearID;
	APIint lightQuadraticID;
	APIint lightDirectionID;
	APIint lightcutOffID;
	APIint lightouterCutOffID;
	
	//POINT
	glm::vec3 position;
	glm::vec3 ambient;
	glm::vec3 diffuse;
	glm::vec3 specular;
	float constant;
	float linear;
	float quadratic;
	//SPOT
	float cutOff;
	float outerCutOff;
	//DIR
	glm::vec3 direction;

	void sendPropertiesToShaderGeneral();
public:
	Light();
	Light(glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular);
	~Light();


	void sendPropertiesToShader();
	void sendPropertiesToShader(float x, float y, float z);
	void sendPropertiesToShader(glm::vec3 position);

	void init(const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName);

	//GETTERS AND SETTERS

	void setPosition(glm::vec3 position);
	void setPosition(float x, float y, float z);

	void setAmbient(glm::vec3 ambient);
	void setAmbient(float r, float g, float b);

	void setDiffuse(glm::vec3 diffuse);
	void setDiffuse(float r, float g, float b);

	void setSpecular(glm::vec3 specular);
	void setSpecular(float r, float g, float b);

	void setDirection(float x, float y, float z);
	void setDirection(glm::vec3 direction);

	void setConstant(float constant);
	void setLinear(float linear);
	void setQuadratic(float quadratic);

	void setCutOff(float cutOff);
	void setOuterCutOff(float outerCutOff);


	const glm::vec3 &getPosition() const;
	const glm::vec3 &getDirection() const;
	const glm::vec3 &getAmbient() const;
	const glm::vec3 &getDiffuse() const;
	const glm::vec3 &getSpecular() const;
	const float &getConstant() const;
	const float &getLinear() const;
	const float &getQuadratic() const;
	const float &getCutoff() const;
	const float &getOuterCutOff() const;

	void printLightPosition();
};

