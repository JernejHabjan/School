#pragma once
//include hpp
#include "PointLight.hpp"

PointLight::PointLight() { }

void PointLight::sendPropertiesToShaderPoint() {
	APIUniform1f(lightConstantID, constant);
	APIUniform1f(lightLinearID, linear);
	APIUniform1f(lightQuadraticID, quadratic);
}

PointLight::PointLight(glm::vec3 position, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular, float constant, float linear, float quadratic)	: Light (ambient, diffuse, specular) {
	this->position = position;
	this->constant = constant;
	this->linear = linear;
	this->quadratic = quadratic;
}

PointLight::~PointLight() { }

void PointLight::init(APIuint programID, const GLchar * positionName, const GLchar * ambientName, const GLchar * diffuseName, const GLchar * specularName, const GLchar * constantName, const GLchar * linearName, const GLchar * quadraticName) {
	Light::init(ambientName, diffuseName, specularName);
	lightPositionID = APIGetUniformLocation(programID, positionName);
	lightConstantID = APIGetUniformLocation(programID, constantName);
	lightLinearID = APIGetUniformLocation(programID, linearName);
	lightQuadraticID = APIGetUniformLocation(programID, quadraticName);
}

void PointLight::sendPropertiesToShader() {
	Light::sendPropertiesToShader();
	sendPropertiesToShaderPoint();
}

void PointLight::sendPropertiesToShader(float x, float y, float z) {
	Light::sendPropertiesToShader(x, y, z);
	sendPropertiesToShaderPoint();
}

void PointLight::sendPropertiesToShader(glm::vec3 position) {
	Light::sendPropertiesToShader(position);
	sendPropertiesToShaderPoint();
}

