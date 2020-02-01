#pragma once
//include parent
#include "..\MeshShell.hpp"

class Mesh : public MeshShell
{
public:
	Mesh();
	~Mesh();
	void generateMesh();
};

