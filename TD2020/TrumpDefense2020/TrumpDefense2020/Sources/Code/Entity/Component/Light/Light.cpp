#pragma once
//include hpp
#include "Light.hpp"

//include libs
#include <string>

//include files
#include "..\..\..\Defines.hpp"
Light::Light() { }

Light::Light(glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular) {
	this->ambient = ambient;
	this->diffuse = diffuse;
	this->specular = specular;
}

Light::~Light(){ }

void Light::sendPropertiesToShaderGeneral() {
	APIUniform3f(lightAmbientID, ambient.r, ambient.g, ambient.b);
	APIUniform3f(lightDiffuseID, diffuse.r, diffuse.g, diffuse.b);
	APIUniform3f(lightSpecularID, specular.r, specular.g, specular.b);
}

void Light::sendPropertiesToShader() {
	APIUniform3f(lightPositionID, position.x, position.y, position.z);
	sendPropertiesToShaderGeneral();
}

void Light::sendPropertiesToShader(float x, float y, float z) {
	APIUniform3f(lightPositionID, x, y, z);
	sendPropertiesToShaderGeneral();
}

void Light::sendPropertiesToShader(glm::vec3 position) {
	APIUniform3f(lightPositionID, position.x, position.y, position.z);
	sendPropertiesToShaderGeneral();
}

void Light::init(const GLchar* ambientName, const GLchar* diffuseName, const GLchar* specularName) {
	lightAmbientID = APIGetUniformLocation(programID, ambientName);
	lightDiffuseID = APIGetUniformLocation(programID, diffuseName);
	lightSpecularID = APIGetUniformLocation(programID, specularName);
}

void Light::setPosition(glm::vec3 position){
	this->position = position;
}

void Light::setPosition(float x, float y, float z){
	this->position.x = x;
	this->position.y = y;
	this->position.z = z;
}

void Light::setAmbient(glm::vec3 ambient) {
	this->ambient = ambient;
}
void Light::setAmbient(float r, float g, float b) {
	this->ambient.r = r;
	this->ambient.g = g;
	this->ambient.b = b;
}

void Light::setDiffuse(glm::vec3 diffuse) {
	this->diffuse = diffuse;
}
void Light::setDiffuse(float r, float g, float b) {
	this->diffuse.r = r;
	this->diffuse.g = g;
	this->diffuse.b = b;
}

void Light::setSpecular(glm::vec3 specular) {
	this->specular = specular;
}
void Light::setSpecular(float r, float g, float b) {
	this->specular.r = r;
	this->specular.g = g;
	this->specular.b = b;
}

void Light::setDirection(float x, float y, float z) {
	direction.x = x;
	direction.y = y;
	direction.z = z;
}

void Light::setDirection(glm::vec3 direction) {
	this->direction.x = direction.x;
	this->direction.y = direction.y;
	this->direction.z = direction.z;
}

void Light::setConstant(float constant) { this->constant = constant; }
void Light::setLinear(float linear) { this->linear = linear; }
void Light::setQuadratic(float quadratic) {	this->quadratic = quadratic; }
void Light::setCutOff(float cutOff) { this->cutOff = cutOff; }
void Light::setOuterCutOff(float outerCutOff) {	this->outerCutOff = outerCutOff; }

const glm::vec3 & Light::getPosition() const { return position; }
const glm::vec3 & Light::getDirection() const { return direction; }
const float & Light::getConstant() const { return constant; }
const float & Light::getLinear() const { return constant; }
const float & Light::getQuadratic() const { return quadratic; }
const float & Light::getCutoff() const { return cutOff; }
const float & Light::getOuterCutOff() const { return outerCutOff; }
const glm::vec3 & Light::getAmbient() const { return ambient; }
const glm::vec3 & Light::getDiffuse() const { return diffuse; }
const glm::vec3 & Light::getSpecular() const { return specular; }

void Light::printLightPosition() { print(position.x << ", " << position.y << ", " << position.z); }