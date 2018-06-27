#pragma once
#include "Loader.hpp"

class Texture
{

private:

	GLuint Texture_Diff;
	GLuint Texture_Norm;
	GLuint Texture_Spec;

	std::string name;
	SDL_Window* screen;
	GLuint programID;

public:
	Texture();
	Texture(const std::string name, SDL_Window* screen, GLuint programID);

	void loadAllTextures();
	void loadDiffuse();
	void loadNormal();
	void loadSpecular();


	void activateAll();
	void activateDiff();
	void activateNorm();
	void activateSpec();

	/*


	void sendtoshader

	*/
};

