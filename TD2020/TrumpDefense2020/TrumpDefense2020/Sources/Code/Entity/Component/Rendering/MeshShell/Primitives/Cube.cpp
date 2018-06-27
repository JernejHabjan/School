#include "Cube.hpp"


	Cube::Cube()
	{
	}

	Cube::~Cube()
	{
	}

	std::vector<float> getCubeVertices() {
		return {
			-0.5, -0.5, -0.5, 1.0,
			0.5, -0.5, -0.5, 1.0,
			0.5,  0.5, -0.5, 1.0,
			-0.5,  0.5, -0.5, 1.0,
			-0.5, -0.5,  0.5, 1.0,
			0.5, -0.5,  0.5, 1.0,
			0.5,  0.5,  0.5, 1.0,
			-0.5,  0.5,  0.5, 1.0,
		};
	}

	
	std::vector <short> getCubeElements() {
		return {
			0, 1, 2, 3,
			4, 5, 6, 7,
			0, 4, 1, 5, 2, 6, 3, 7
		};
	}