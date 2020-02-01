#pragma once
#include <glm\glm.hpp>
#include <GL\glew.h>
#include <iostream>

class Light{

private:

	GLint lightPositionID;

	GLint lightAmbientID;
	GLint lightDiffuseID;
	GLint lightSpecularID;

	GLint lightConstantID;
	GLint lightLinearID;
	GLint lightQuadraticID;

	GLint lightDirectionID;

	GLint lightcutOffID;
	GLint lightouterCutOffID;


	GLuint programID;


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


public:
	Light();
	Light(glm::vec3 position, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular, float constant, float linear, float quadratic);//POINT LIGHT
	Light(glm::vec3 direction, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular);//DIRECTIONAL LIGHT
	Light(glm::vec3 direction, glm::vec3 position, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular, float constant, float linear, float quadratic, float cutOff, float outerCutOff); //SPOT LIGHT

	//POINT LIGHT
	void initPointLight(GLuint programID, const GLchar* positionName, const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName, const GLchar* constantName, const GLchar* linearName, const GLchar* quadraticName);
	void initDirLight(GLuint programID, const GLchar* directionName, const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName);
	void initSpotLight(GLuint programID, const GLchar* directionName, const GLchar* positionName, const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName, const GLchar* constantName, const GLchar* linearName, const GLchar* quadraticName, const GLchar* cutOffName, const GLchar* outerCutOffName);
	
	void sendPropertiesToShaderPoint();
	void sendPropertiesToShaderPoint(float x, float y, float z);
	void sendPropertiesToShaderDir();
	void sendPropertiesToShaderSpot(float x, float y, float z);


	void setPosition(glm::vec3 position);
	void setPosition(float x, float y, float z);

	void setAmbient(glm::vec3 ambient);
	void setAmbient(float r, float g, float b);

	void setDiffuse(glm::vec3 diffuse);
	void setDiffuse(float r, float g, float b);

	void setSpecular(glm::vec3 specular);
	void setSpecular(float r, float g, float b);

	glm::vec3 getPosition();
	glm::vec3 getDirection();
	glm::vec3 getAmbient();
	glm::vec3 getDiffuse();
	glm::vec3 getSpecular();


	void printLightPosition();
};

