# pragma once

#include <SDL\SDL_image.h>
#include "Matrix.hpp"
#include "Grid.hpp"

class Camera{
private:
	SDL_Window* screen;
	glm::vec3 loc;
	float camPitch, camYaw;
	float movevel;
	float mousevel;
	float moveUpDown;
	void lockCamera();
	void moveCamera(float dir);
	void moveCameraUp(float dir);


	float translatedZ = 0.0;
	float translatedX = 0.0;
	float translatedY = 0.0;
	float pitch = 0.0;
	void rtsCamera(Matrix* viewMatrix, Grid &grid);
	void freeStyleCam(Matrix* viewMatrix);
public:
	Camera();
	Camera(SDL_Window* screen);
	void Control(Matrix* viewMatrix, Grid &grid);
	void UpdateCamera(Matrix* viewMatrix);
	glm::vec3 getVector();
	glm::vec3 getLocation();
	void setLocation(glm::vec3 vec);
	void setLocation(float x, float y, float z);
	void lookAt(float pitch, float yaw);
	void setSpeed(float mv, float mov);

	float getPitch();
	void setPitch(float pitch);

};
