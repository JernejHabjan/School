#include "Square.hpp"


Square::Square(){}

Square::Square(float x, float z, float x_Middle, float z_Middle, Matrix &matrix, bool isPath, bool isPathFlying){
	this->x = x;
	this->z = z;
	this->x_Middle = x_Middle;
	this->z_Middle = z_Middle;
	this->matrix = matrix;
	this->isPath = isPath;
	this->isPathFlying = isPathFlying;

}

glm::vec3 Square::getPosition(){return glm::vec3(x, 0.0f, z);}
glm::vec3 Square::getMiddlePosition(){ return glm::vec3(x_Middle, 0.0f, z_Middle); }

Matrix &Square::getMatrix(){ return matrix; }

void Square::setMatrix(Matrix &matrix){ this->matrix = matrix; };