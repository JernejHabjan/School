#pragma once
//include libs
#include <vector>
#include <glm\glm.hpp>


/********************************

Class:	Model

Purpose:	For storing models
		raw data

********************************/
class Model {
private:

	std::vector<glm::vec3> vertices;
	std::vector<glm::vec2> uvs;
	std::vector<glm::vec3> normals;


	glm::mat4 BBoxTransform; //bounding box
public:
	Model();
	~Model();
	
	//functions
	void generateBBox();

	//getters and setters
	std::vector<glm::vec3> getVertices();
	std::vector<glm::vec2> getUVs();
	std::vector<glm::vec3> getNormals();
	glm::mat4 getBBox();
};

