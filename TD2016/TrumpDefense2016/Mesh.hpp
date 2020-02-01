#pragma once
#include "Vertex.hpp"
#include <glm\glm.hpp>
#include <vector>


struct Mesh{
	std::vector<Vertex> vertices;
	std::vector<short int> indices;
	unsigned int numVertices;

};