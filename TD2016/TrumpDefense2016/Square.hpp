#pragma once
#include <glm\glm.hpp>
#include "GameObject.hpp"

class Square{
private:
	float x_Middle;
	float z_Middle;

	Matrix matrix;

public:

	float x;
	float y = 0.0f;
	float z;
	bool isSelected = false;


	bool updated = false;

	bool isfree = true;
	bool isPath;
	bool isPathFlying;
	bool hasObject = false;
	bool hasFlyingObject = false;


	GameObject object;
	GameObject flyIngObject;


	Square();
	Square(float x, float z, float x_Middle, float z_Middle, Matrix &matrix, bool isPath, bool isPathFlying);

	glm::vec3 getPosition();
	glm::vec3 getMiddlePosition();

	Matrix &getMatrix();
	void setMatrix(Matrix &matrix);
};

