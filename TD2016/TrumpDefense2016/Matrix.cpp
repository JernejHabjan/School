#define _USE_MATH_DEFINES
#include "Matrix.hpp"
Matrix::Matrix() :
modelMatrix(1.0f),
viewMatrix(1.0f),
projMatrix(1.0f)
{}

float Matrix::degToRad(float degrees){
	return degrees*(M_PI / 180.0f);
}

void Matrix::popMatrix(glm::mat4 &matrix){
	if (!matrixStack.empty()){
		matrix = matrixStack.back();
		matrixStack.push_back(matrix);
	}
}

void Matrix::pushMatrix(glm::mat4 &matrix){
	matrixStack.push_back(matrix);
}


void Matrix::translateX(glm::mat4 &matrix, float x){
	matrix = glm::translate(matrix, glm::vec3(x, 0.0f, 0.0f));
}

void Matrix::translateY(glm::mat4 &matrix, float y){
	matrix = glm::translate(matrix, glm::vec3(0.0f, y, 0.0f));
}

void Matrix::translateZ(glm::mat4 &matrix, float z){
	matrix = glm::translate(matrix, glm::vec3(0, 0, z));
}

void Matrix::translate(glm::mat4 &matrix, float x, float y, float z){
	matrix = glm::translate(matrix, glm::vec3(x, y, z));
}


void Matrix::rotateX(glm::mat4 &matrix, float degrees){
	matrix = glm::rotate(matrix, degToRad(degrees), glm::vec3(1.0f, 0.0f, 0.0f));
}


void Matrix::rotateY(glm::mat4 &matrix, float degrees){
	matrix = glm::rotate(matrix, degToRad(degrees), glm::vec3(0.0f, 1.0f, 0.0f));
}

void Matrix::rotateZ(glm::mat4 &matrix, float degrees){
	matrix = glm::rotate(matrix, degToRad(degrees), glm::vec3(0.0f, 0.0f, 1.0f));
}

void Matrix::rotateAll(glm::mat4 &matrix, float degrees){
	matrix = glm::rotate(matrix, degToRad(degrees), glm::vec3(1.0f, 1.0f, 1.0f));
}

void Matrix::scaleX(glm::mat4 &matrix, float s){
	matrix = glm::scale(matrix, glm::vec3(s, 1.0, 1.0));
}

void Matrix::scaleY(glm::mat4 &matrix, float s){
	matrix = glm::scale(matrix, glm::vec3(1.0, s, 1.0));
}

void Matrix::scaleZ(glm::mat4 &matrix, float s){
	matrix = glm::scale(matrix, glm::vec3(1.0, 1.0, s));
}

void Matrix::scaleAll(glm::mat4 &matrix, float sX, float sY, float sZ){
	matrix = glm::scale(matrix, glm::vec3(sX, sY, sZ));
}

void Matrix::setMatrix(glm::mat4 &matrix, glm::mat4 matrixCopy){

	matrix = matrixCopy;
}


const glm::mat4 &Matrix::getMatrix(glm::mat4 &matrix) const{
	return matrix;
}

void Matrix::loadIdentity(glm::mat4 &matrix){
	matrix = glm::mat4(1.0f);
}

glm::vec4 Matrix::getCoordinates(glm::mat4 &matrix){
	return matrix * glm::vec4(0.0f, 0.0f, 0.0f, 1.0f);
}

void Matrix::printMatrix(glm::mat4 &matrix){
	const float *pSource = (const float*)glm::value_ptr(matrix);
	for (int i = 0; i < 16; ++i){
		if (i % 4 == 0){
			std::cout << "\n";
		}
		std::cout << pSource[i] << " ";
	}std::cout << "\n";
}
