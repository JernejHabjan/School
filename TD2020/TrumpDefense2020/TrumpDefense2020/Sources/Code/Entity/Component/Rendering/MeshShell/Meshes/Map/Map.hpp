#pragma once
//include parent
#include "..\Mesh.hpp"

//include libs
#include <vector>
#include <glm\glm.hpp>

class Map : public Mesh
{
private:
	std::vector<glm::vec3> pathLoadedFromFile;
public:
	Map();
	~Map();

	void loadPathFromFile();

};

