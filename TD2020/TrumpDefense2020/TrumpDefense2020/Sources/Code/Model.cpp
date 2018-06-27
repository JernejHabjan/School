#pragma once
//include hpp
#include "Model.hpp"

//include libs
#include <glm\gtc\matrix_transform.hpp>



Model::Model() { }
Model::~Model() { }

void Model::generateBBox() {

	std::vector<glm::vec3> vertices = getVertices();

	if (vertices.size() == 0)
		return;

	// Cube 1x1x1, centered on origin
	float bbox[] = {
		-0.5, -0.5, -0.5, 1.0,
		0.5, -0.5, -0.5, 1.0,
		0.5,  0.5, -0.5, 1.0,
		-0.5,  0.5, -0.5, 1.0,
		-0.5, -0.5,  0.5, 1.0,
		0.5, -0.5,  0.5, 1.0,
		0.5,  0.5,  0.5, 1.0,
		-0.5,  0.5,  0.5, 1.0,
	};

	//glGenBuffers(1, &vbo_bbox);
	//glBindBuffer(GL_ARRAY_BUFFER, vbo_bbox);
	//glBufferData(GL_ARRAY_BUFFER, sizeof(bbox), bbox, GL_STATIC_DRAW);
	//glBindBuffer(GL_ARRAY_BUFFER, 0);

	short elements[] = {
		0, 1, 2, 3,
		4, 5, 6, 7,
		0, 4, 1, 5, 2, 6, 3, 7
	};

	//glGenBuffers(1, &ibo_elements);
	//glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo_elements);
	//glBufferData(GL_ELEMENT_ARRAY_BUFFER, sizeof(elements), elements, GL_STATIC_DRAW);
	//glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);


	float
		minX, maxX,
		minY, maxY,
		minZ, maxZ; //BOUNDING BOX

	minX = maxX = vertices[0].x;
	minY = maxY = vertices[0].y;
	minZ = maxZ = vertices[0].z;
	for (int i = 0; i < vertices.size(); i++) {
		if (vertices[i].x < minX) minX = vertices[i].x;
		if (vertices[i].x > maxX) maxX = vertices[i].x;
		if (vertices[i].y < minY) minY = vertices[i].y;
		if (vertices[i].y > maxY) maxY = vertices[i].y;
		if (vertices[i].z < minZ) minZ = vertices[i].z;
		if (vertices[i].z > maxZ) maxZ = vertices[i].z;
	}
	glm::vec3 objSize = glm::vec3(maxX - minX, maxY - minY, maxZ - minZ);
	glm::vec3 center = glm::vec3((minX + maxX) / 2, (minY + maxY) / 2, (minZ + maxZ) / 2);
	BBoxTransform = glm::translate(glm::mat4(1), center)* glm::scale(glm::mat4(1), objSize);

}

//GETTERS AND SETTERS

std::vector<glm::vec3> Model::getVertices() { return vertices; }
std::vector<glm::vec2> Model::getUVs() { return uvs; }
std::vector<glm::vec3> Model::getNormals() { return normals; }
glm::mat4 Model::getBBox() { return BBoxTransform; }
