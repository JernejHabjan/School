#pragma once
//include libs
#include <unordered_map>
#include <glm\glm.hpp>

//include files
#include "Renderer\GL_API.hpp"

class Shader {
private:
	std::unordered_map<std::string, APIuint> programIDs; // TODO DA MAM MAP

	APIuint uiShader; // ID of shader
	int iType; // GL_VERTEX_SHADER, GL_FRAGMENT_SHADER...
	bool bLoaded; // Whether shader was loaded and compiled

public:
	Shader();
	void deleteShader();

	bool isLoaded();
	APIuint getShaderID();


	bool loadShader(std::string filePath, int a_iType);

	std::unordered_map<std::string, APIuint> *getProgramIDs();

};


/********************************

Class:	ShaderProgram

Purpose: Wraps OpenGL shader program
and make its usage easy.

********************************/

class ShaderProgram {
public:
	void createProgram();
	void deleteProgram();

	bool addShaderToProgram(Shader* shShader);
	bool linkProgram();

	void useProgram();

	APIuint getProgramID();


	// Setting vectors
	void setUniform(std::string sName, glm::vec2* vVectors, int iCount = 1);
	void setUniform(std::string sName, const glm::vec2 vVector);
	void setUniform(std::string sName, glm::vec3* vVectors, int iCount = 1);
	void setUniform(std::string sName, const glm::vec3 vVector);
	void setUniform(std::string sName, glm::vec4* vVectors, int iCount = 1);
	void setUniform(std::string sName, const glm::vec4 vVector);

	// Setting floats
	void setUniform(std::string sName, float* fValues, int iCount = 1);
	void setUniform(std::string sName, const float fValue);

	// Setting 4x4 matrices
	void setUniform(std::string sName, glm::mat4* mMatrices, int iCount = 1);
	void setUniform(std::string sName, const glm::mat4 mMatrix);

	// Setting integers
	void setUniform(std::string sName, int* iValues, int iCount = 1);
	void setUniform(std::string sName, const int iValue);

	ShaderProgram();

private:
	APIuint uiProgram; // ID of program
	bool bLinked; // Whether program was linked and is ready to use
};