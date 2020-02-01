#pragma once
//include hpp
#include "SpotLight.hpp"

void SpotLight::sendPropertiesToShaderSpot() {
	APIUniform3f(lightDirectionID, direction.x, direction.y, direction.z);
	APIUniform1f(lightConstantID, constant);
	APIUniform1f(lightLinearID, linear);
	APIUniform1f(lightQuadraticID, quadratic);
	APIUniform1f(lightcutOffID, cutOff);
	APIUniform1f(lightouterCutOffID, outerCutOff);
}

SpotLight::SpotLight() { }

SpotLight::SpotLight(glm::vec3 direction, glm::vec3 position, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular, float constant, float linear, float quadratic, float cutOff, float outerCutOff) : Light(ambient, diffuse, specular) {
	this->direction = direction;
	this->position = position;
	this->constant = constant;
	this->linear = linear;
	this->quadratic = quadratic;
	this->cutOff = cutOff;
	this->outerCutOff = outerCutOff;
}


SpotLight::~SpotLight() { }

void SpotLight::init(APIuint programID, const GLchar * directionName, const GLchar * positionName, const GLchar * ambientName, const GLchar * diffuseName, const GLchar * specularName, const GLchar * constantName, const GLchar * linearName, const GLchar * quadraticName, const GLchar * cutOffName, const GLchar * outerCutOffName) {
	lightDirectionID = APIGetUniformLocation(programID, directionName);
	lightPositionID = APIGetUniformLocation(programID, positionName);
	Light::init(ambientName, diffuseName, specularName);
	lightConstantID = APIGetUniformLocation(programID, constantName);
	lightLinearID = APIGetUniformLocation(programID, linearName);
	lightQuadraticID = APIGetUniformLocation(programID, quadraticName);
	lightcutOffID = APIGetUniformLocation(programID, cutOffName);
	lightouterCutOffID = APIGetUniformLocation(programID, outerCutOffName);
}

void SpotLight::sendPropertiesToShader() {
	Light::sendPropertiesToShader();
	sendPropertiesToShaderSpot();
}

void SpotLight::sendPropertiesToShader(float x, float y, float z) {
	Light::sendPropertiesToShader(x, y, z);
	sendPropertiesToShaderSpot();
}

void SpotLight::sendPropertiesToShader(glm::vec3 position) {
	Light::sendPropertiesToShader(position);
	sendPropertiesToShaderSpot();
}
