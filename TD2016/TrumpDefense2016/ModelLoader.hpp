#pragma once

#include "Texture.hpp"
#include "Shader.hpp"
#include <map>
class ModelLoader{


private:
	//bufferji
	GLuint vertexbuffer;
	GLuint uvbuffer;
	GLuint normalbuffer;

	std::vector<glm::vec3> vertices;
	std::vector<glm::vec2> uvs;
	std::vector<glm::vec3> normals;

	Texture texture;

	bool loadWholeObj;
	int loadTexture;


	void genBuffers();




	void loadRegular(SDL_Window* screen, Shader shader);
	void loadSpecial(SDL_Window* screen, Shader shader, int &level);
public:

	std::map <std::string, ModelLoader*> modelLoader_Models; //vsi obj ever


	ModelLoader();
	ModelLoader(const std::string name, bool loadWholeObj, int loadTexture, SDL_Window* screen, GLuint programID);
	void loadModels(SDL_Window* screen, Shader shader, int &level);
	void activate();

	std::vector<glm::vec3> getVertices();


	~ModelLoader();

};
