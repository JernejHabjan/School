#pragma once
//include libs
#include <vector>
#include <glm\glm.hpp>

//include files
#include "Renderer\GL_API.hpp"


class Matrix {
	std::vector<glm::mat4> matrixStack;

	float Matrix::degToRad(float degrees);
public:

	glm::mat4 modelMatrix;
	glm::mat4 viewMatrix;
	glm::mat4 projMatrix;
	glm::mat4 orthoMatrix;

	APIuint MatrixP_ID;
	APIuint MatrixV_ID;
	APIuint MatrixM_ID;
	APIuint MatrixPVM_ID;
	APIuint MatrixM_Transp_Inverse_ID;

	Matrix();

	void setProjection3D(float fFOV, float fAspectRatio, float fNear, float fFar);
	void setOrtho2D(int width, int height);

	void popMatrix(glm::mat4 &matrix);
	void pushMatrix(glm::mat4 &matrix);

	void translateX(glm::mat4 &matrix, float x);
	void translateY(glm::mat4 &matrix, float y);
	void translateZ(glm::mat4 &matrix, float y);
	void translate(glm::mat4 &matrix, float x, float y, float z);

	void rotateX(glm::mat4 &matrix, float degrees);
	void rotateY(glm::mat4 &matrix, float degrees);
	void rotateZ(glm::mat4 &matrix, float degrees);
	void rotateAll(glm::mat4 &matrix, float degrees);

	void Matrix::scaleX(glm::mat4 &matrix, float s);
	void Matrix::scaleY(glm::mat4 &matrix, float s);
	void Matrix::scaleZ(glm::mat4 &matrix, float s);
	void Matrix::scaleAll(glm::mat4 &matrix, float sX, float sY, float sZ);

	void setMatrix(glm::mat4 &matrix, glm::mat4 matrixCopy);
	const glm::mat4 &getMatrix(glm::mat4 &matrix) const;

	void Matrix::loadIdentity(glm::mat4 &matrix);
	glm::vec4 getCoordinates(glm::mat4 &matrix);

	void printMatrix(glm::mat4 &matrix);
};