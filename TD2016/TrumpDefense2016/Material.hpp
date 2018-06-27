#pragma once
#include <glm\glm.hpp>
#include <GL\glew.h>

class Material {
public:
	
	int matAmbientLocID;
	GLint matDiffuseLocID;
	GLint matSpecularLocID;
	GLint matShineLocID;

	GLuint programID;

	glm::vec3 ambient;
	glm::vec3 diffuse;
	glm::vec3 specular;
	float shininess;


	Material();
	void initMaterial(GLuint programID,
		const GLchar* ambientName,
		const GLchar* diffuseName,
		const GLchar* specularName,
		const GLchar* shininessName);
	void sendPropertiesToShader();
	void setDefault();
	void setBronze();
	void setCopper();
	void setWhitePlastic();
};
