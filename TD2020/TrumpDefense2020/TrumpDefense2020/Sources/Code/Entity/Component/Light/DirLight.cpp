#pragma once
//include hpp
#include "DirLight.hpp"

DirLight::DirLight() { }

DirLight::DirLight(glm::vec3 direction, glm::vec3 ambient, glm::vec3 diffuse, glm::vec3 specular) : Light(ambient, diffuse, specular) {
	this->direction = direction;
}

DirLight::~DirLight() { }

void DirLight::init(APIuint programID, const GLchar * directionName, const GLchar * ambientName, const GLchar * diffuseName, const GLchar * specularName) {
	lightDirectionID = APIGetUniformLocation(programID, directionName);
	Light::init(ambientName, diffuseName, specularName);
}

void DirLight::sendPropertiesToShaderDir() {
	APIUniform3f(lightDirectionID, direction.x, direction.y, direction.z);
}

void DirLight::sendPropertiesToShader() {
	sendPropertiesToShaderDir();
	Light::sendPropertiesToShader();
}

void DirLight::sendPropertiesToShader(float x, float y, float z) {
	sendPropertiesToShader();
}

void DirLight::sendPropertiesToShader(glm::vec3 position) {
	sendPropertiesToShader();
}
