#pragma once

#include <glm\glm.hpp>

#include <GL\glew.h>

#include <SDL\SDL_image.h>

#include <iostream>     // std::cout
#include <fstream>      // std::ifstream

#include <sstream>
#include <vector>
#include <string>


namespace Loader{

	bool loadOBJ(
		const std::string path,
		std::vector<glm::vec3> & out_vertices,
		std::vector<glm::vec2> & out_uvs,
		std::vector<glm::vec3> & out_normals
		);
	GLuint loadTexture(const std::string name, SDL_Window* screen, int textureNumber);
	void loadCubemap(SDL_Window* screen, int &level);
	SDL_Surface *getTextureFromFile(const std::string name, SDL_Window* screen);

};
