#pragma once

#include "ObjectStorage.hpp"
#include "Camera.hpp"
#include "Light.hpp"
#include "Material.hpp"
#include "Grid.hpp"
#include "Sound.hpp"

class Draw
{
private:
	Camera* cam;


	bool goRight = true;



	float angle = 0;
	float transl = 0;

	bool goUpHammer = true;
	float hammerRotationAnimation = 0;

	GLuint drawTimer = 0;
	GLuint drawTimerFlying = 0;
	GLuint spawnTimer = 0;
	GLuint hammerSFXTimer = 0;
	GLuint enemyUpgradeTimer = 0;
	GLuint trumpSFXTimer = 0;
	GLuint slumsTimer = 0;
	int lifeAddition = 0;

	bool spawn = true;

	bool displayLightSources = false;
public:
	Draw();
	Draw(Camera* cam);


	void display(Matrix* matrix, Shader &shader, std::vector<Light> &pointLights, std::vector<Light> &spotLights, 
		Light &dirLight, Material &material, ModelLoader &modelLoader, Grid &grid, GLuint &money, GLuint &life, 
		Sound *sound, int &wallHeight, std::vector<std::string> &enemyArray, int &level, Uint32 &gameStartTime);
	void drawTowers(Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, Grid &grid);
	void prepareForDraw(Matrix* matrix, Shader &shader, Material &material);

	void drawModel(ModelLoader* modelLoader, Matrix* matrix, Shader &shader, Material &material);
	void drawObject(GameObject* object, Matrix* matrix, Shader &shader, Material &material);

	void drawSkybox(Matrix* matrix, Shader &shader, ModelLoader &modelLoader, int &level);

	void drawHealth(Grid &grid, Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, GLuint &life);

	void drawSlums(Grid &grid, Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, Sound* sound);
	void drawWall(Grid &grid, Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, Sound* sound, int &wallHeight);
	void drawHammer(Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader, float hammerRotationAnimation);
	void drawTiles(Grid &grid, Matrix* matrix, Shader &shader, Material &material, ModelLoader &modelLoader);

	bool updateInTime(GLuint &timer, GLuint time);


	void updateEnemy(Grid &grid, Matrix* matrix, Shader &shader, Material &material, GLuint &life);


	bool updatePath(Square &sq, Grid &grid, GLuint &life, std::vector<glm::vec3> &path, GameObject &gameObject, float oldX, float oldZ);
	void checkCollision(Grid &grid, Matrix* matrix, ModelLoader &modelLoader, Shader &shader, Material &material, GLuint &money, Sound *sound);
	void spawnEnemy(Grid &grid, Sound *sound, std::vector<std::string> &enemyArray); 


};

