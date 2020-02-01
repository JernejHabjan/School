#include "Material.hpp"

Material::Material(){}

void Material::initMaterial(GLuint programID,
							const GLchar* ambientName,
							const GLchar* diffuseName,
							const GLchar* specularName,
							const GLchar* shininessName){
	
	matAmbientLocID = glGetUniformLocation(programID, ambientName);
	matDiffuseLocID = glGetUniformLocation(programID, diffuseName);
	matSpecularLocID = glGetUniformLocation(programID, specularName);
	matShineLocID = glGetUniformLocation(programID, shininessName);
}

void Material::sendPropertiesToShader(){
	glUniform3f(matAmbientLocID, ambient.r, ambient.g, ambient.b);
	glUniform3f(matDiffuseLocID, diffuse.r, diffuse.g, diffuse.b);
	glUniform3f(matSpecularLocID, specular.r, specular.g, specular.b);
	glUniform1f(matShineLocID, shininess);
}

void Material::setDefault(){
	ambient = glm::vec3(0.1f, 0.1f, 0.1f);
	diffuse = glm::vec3(244.0f / 255.0f, 66.0f / 255.0f, 131.0f / 255.0f);
	specular = glm::vec3(0.3f, 0.3f, 0.3f);
	shininess = 0.4f * 128.0f;
}

void Material::setBronze(){
	ambient = glm::vec3(0.2125f, 0.1275f, 0.054f);
	diffuse = glm::vec3(0.714f, 0.4284f, 0.18144f);
	specular = glm::vec3(0.393548f, 0.271906f, 0.166721f);
	shininess = 0.2f * 128.0f;
}

void Material::setCopper(){
	ambient = glm::vec3(0.19125f, 0.0735f, 0.0225f);
	diffuse = glm::vec3(0.7038f, 0.27048f, 0.0828f);
	specular = glm::vec3(0.256777f, 0.137622f, 0.086014f);
	shininess = 0.1f * 128.0f;
}

void Material::setWhitePlastic(){
	ambient = glm::vec3(0.0f, 0.0f, 0.0f);
	diffuse = glm::vec3(0.55f, 0.55f, 0.55f);
	specular = glm::vec3(0.70f, 0.70f, 0.70f);
	shininess = 0.25f * 128.0f;
}