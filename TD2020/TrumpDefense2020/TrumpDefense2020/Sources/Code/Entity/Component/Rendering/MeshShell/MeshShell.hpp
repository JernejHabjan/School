#pragma once
//include libs
#include <vector>
#include <glm\glm.hpp>

class Model;
class MeshShell
{
protected:
	Model *model;

public:
	MeshShell();
	~MeshShell();

	void getModelById();
	
	
	virtual void generateMesh() = 0;
	

};

