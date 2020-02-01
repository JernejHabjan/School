#include "Light.hpp"

Light::Light(){}

Light::Light(glm::vec3 position, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular, float constant, float linear, float quadratic){ //POINT LIGHT
	this->position = position;
	this->ambient = ambient;
	this->diffuse = diffuse;
	this->specular = specular;
	this->constant = constant;
	this->linear = linear;
	this->quadratic = quadratic;
}

Light::Light(glm::vec3 direction, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular){ //DIR LIGHT
	this->direction = direction;
	this->ambient = ambient;
	this->diffuse = diffuse;
	this->specular = specular;

}

Light::Light(glm::vec3 direction, glm::vec3 position, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular, float constant, float linear, float quadratic, float cutOff, float outerCutOff){ //SPOT LIGHT
	this->direction = direction;
	this->position = position;
	this->ambient = ambient;
	this->diffuse = diffuse;
	this->specular = specular;
	this->constant = constant;
	this->linear = linear;
	this->quadratic = quadratic;
	this->cutOff = cutOff;
	this->outerCutOff = outerCutOff;
}


void Light::initPointLight(GLuint programID, const GLchar* positionName, const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName, const GLchar* constantName, const GLchar* linearName, const GLchar* quadraticName){


	lightPositionID = glGetUniformLocation(programID, positionName);
	lightAmbientID = glGetUniformLocation(programID, ambientName);
	lightDiffuseID = glGetUniformLocation(programID, diffuseName);
	lightSpecularID = glGetUniformLocation(programID, specularName);

	lightConstantID = glGetUniformLocation(programID, constantName);
	lightLinearID = glGetUniformLocation(programID, linearName);
	lightQuadraticID = glGetUniformLocation(programID, quadraticName);

}

void Light::initDirLight(GLuint programID, const GLchar* directionName, const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName){
	lightDirectionID = glGetUniformLocation(programID, directionName);

	lightAmbientID = glGetUniformLocation(programID, ambientName);
	lightDiffuseID = glGetUniformLocation(programID, diffuseName);
	lightSpecularID = glGetUniformLocation(programID, specularName);

}

void Light::initSpotLight(GLuint programID, const GLchar* directionName, const GLchar* positionName, const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName, const GLchar* constantName, const GLchar* linearName, const GLchar* quadraticName, const GLchar* cutOffName, const GLchar* outerCutOffName){
	lightDirectionID = glGetUniformLocation(programID, directionName);
	lightPositionID = glGetUniformLocation(programID, positionName);
	lightAmbientID = glGetUniformLocation(programID, ambientName);
	lightDiffuseID = glGetUniformLocation(programID, diffuseName);
	lightSpecularID = glGetUniformLocation(programID, specularName);

	lightConstantID = glGetUniformLocation(programID, constantName);
	lightLinearID = glGetUniformLocation(programID, linearName);
	lightQuadraticID = glGetUniformLocation(programID, quadraticName);

	lightcutOffID = glGetUniformLocation(programID, cutOffName);
	lightouterCutOffID = glGetUniformLocation(programID, outerCutOffName);
}

void Light::sendPropertiesToShaderPoint(){
	glUniform3f(lightPositionID, position.x, position.y, position.z);
	glUniform3f(lightAmbientID, ambient.r, ambient.g, ambient.b);
	glUniform3f(lightDiffuseID, diffuse.r, diffuse.g, diffuse.b);
	glUniform3f(lightSpecularID, specular.r, specular.g, specular.b);

	glUniform1f(lightConstantID, constant);
	glUniform1f(lightLinearID, linear);
	glUniform1f(lightQuadraticID, quadratic);

}



void Light::sendPropertiesToShaderPoint(float x, float y, float z){
	glUniform3f(lightPositionID, x, y, z);
	glUniform3f(lightAmbientID, ambient.r, ambient.g, ambient.b);
	glUniform3f(lightDiffuseID, diffuse.r, diffuse.g, diffuse.b);
	glUniform3f(lightSpecularID, specular.r, specular.g, specular.b);

	glUniform1f(lightConstantID, constant);
	glUniform1f(lightLinearID, linear);
	glUniform1f(lightQuadraticID, quadratic);
}

void Light::sendPropertiesToShaderSpot(float x, float y, float z){
	glUniform3f(lightDirectionID, direction.x, direction.y, direction.z);
	glUniform3f(lightPositionID, x, y, z);
	glUniform3f(lightAmbientID, ambient.r, ambient.g, ambient.b);
	glUniform3f(lightDiffuseID, diffuse.r, diffuse.g, diffuse.b);
	glUniform3f(lightSpecularID, specular.r, specular.g, specular.b);
	glUniform1f(lightConstantID, constant);
	glUniform1f(lightLinearID, linear);
	glUniform1f(lightQuadraticID, quadratic);
	glUniform1f(lightcutOffID, cutOff);
	glUniform1f(lightouterCutOffID, outerCutOff);
}



void Light::sendPropertiesToShaderDir(){
	glUniform3f(lightDirectionID, direction.x, direction.y, direction.z);
	glUniform3f(lightAmbientID, ambient.r, ambient.g, ambient.b);
	glUniform3f(lightDiffuseID, diffuse.r, diffuse.g, diffuse.b);
	glUniform3f(lightSpecularID, specular.r, specular.g, specular.b);
}


void Light::setPosition(glm::vec3 position){
	this->position = position;
}
void Light::setPosition(float x, float y, float z){
	this->position.x = x;
	this->position.y = y;
	this->position.z = z;
}


void Light::setAmbient(glm::vec3 ambient){
	this->ambient = ambient;
}
void Light::setAmbient(float r, float g, float b){
	this->ambient.r = r;
	this->ambient.g = g;
	this->ambient.b = b;
}

void Light::setDiffuse(glm::vec3 diffuse){
	this->diffuse = diffuse;
}
void Light::setDiffuse(float r, float g, float b){
	this->diffuse.r = r;
	this->diffuse.g = g;
	this->diffuse.b = b;
}

void Light::setSpecular(glm::vec3 specular){
	this->specular = specular;
}
void Light::setSpecular(float r, float g, float b){
	this->specular.r = r;
	this->specular.g = g;
	this->specular.b = b;
}



glm::vec3 Light::getPosition(){
	return position;
}

glm::vec3 Light::getDirection(){
	return direction;
}

glm::vec3 Light::getAmbient(){
	return ambient;
}

glm::vec3 Light::getDiffuse(){
	return diffuse;
}

glm::vec3 Light::getSpecular(){
	return specular;
}


void Light::printLightPosition(){
	std::cout << position.x << ", " << position.y << ", " << position.z << std::endl;
}