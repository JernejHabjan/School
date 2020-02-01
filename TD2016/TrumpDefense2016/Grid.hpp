#pragma once

#include "Square.hpp"
#include <vector>
#include <iostream>

class Grid{
private:

	std::vector<Square> squareArray;
	std::vector<glm::vec3> path;
	std::vector<glm::vec3> pathFlying;


	void makePath();
	void makePathFlying();
	bool pathNode(float &x, float &z);
	bool pathNodeFlying(float &x, float &z);

public:

	const float GRID_W = 128.0f;
	const float GRID_H = 96.0f;
	const float SQUARE_SIZE = 8.0f;
	
	const float GRID_MAP_W = 192.0f;
	const float GRID_MAP_H = 128.0f;

	Grid();
	void Grid::initSq(Matrix* matrix);
	std::vector<Square>& getSquareArray();
	std::vector<glm::vec3>& getPath();
	std::vector<glm::vec3>& getPathFlying();


};

