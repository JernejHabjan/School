#pragma once
#include "Draw.hpp"
#include "Camera.hpp"
#include "Sound.hpp"
#include "Matrix.hpp"
#include "Shader.hpp"
#include "Mesh.hpp"
#include "Vertex.hpp"
#include "ObjectStorage.hpp"
#include "Grid.hpp"

class Resources{

private:

	float SCREEN_W;
	float SCREEN_H;

	std::vector<std::string> enemyArray;



	Draw draw;
	Camera* cam;
	Sound* sound;
	Matrix* matrix;
	Shader shader;
	Light light;
	Material material;
	ModelLoader modelLoader;

	std::vector<Light>pointLights;

	std::vector<Light>spotLights;

	Light dirLight;



public:
	GLuint money;
	GLuint life;
	int wallHeight;
	


	Resources(int &SCREEN_W, int &SCREEN_H, Sound* sound, Camera* cam, Matrix* matrix, std::vector<std::string> &enemyArray);

	void R_initStart(SDL_Window* screen, Grid &grid, int &level);
	void R_init(SDL_Window* screen, Matrix* matrix, Grid &grid, int &level);

	void R_draw(Grid &grid, int &level, Uint32 &startTime);




	void addPointLight(int &level, float x, float y, float z);
	void addDirLight(int &level);
	void addSpotLight(int &level, float x, float y, float z, float dirX, float dirY, float dirZ, float r, float g, float b);



};

