#pragma once

#include "Matrix.hpp"
#include "ModelLoader.hpp"

class GameObject{

private:

	Matrix* matrix;
	bool useMatrix = false;

	std::string name;

	GLuint vbo_bbox;
	GLuint ibo_elements;

	glm::vec3 objSize;
	glm::vec3 center;
	glm::mat4 transform;








public:



	bool selected = false;
	ModelLoader* model;
	GameObject();
	GameObject(std::string name, ModelLoader &modelLoader, int &level);
	void getBBox();
	void drawBBox(Matrix* matrix);

	void setMatrix(Matrix* matrix);
	Matrix* getMatrix();

	glm::vec3 getPosition();


	std::string type; // flying ground defense other

	//Buildtile:
	bool isBuildTile = false;


	//TOWER:
	float defenseRange;
	float dps;
	GLuint lastShotTime;
	GLuint objectLevel;
	bool canShootAir = true;
	float rotationAngle = 0.0f;

	//ENEMY
	int coordinateIndex;
	bool hasMoved;
	int hp;

	glm::vec3 getObjSize();

	~GameObject();
};

