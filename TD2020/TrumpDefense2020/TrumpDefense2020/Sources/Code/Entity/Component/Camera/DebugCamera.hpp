#pragma once
//include parent
#include "Camera.hpp"

//include libs
#include <SDL\SDL.h>
#include <glm\glm.hpp>

class DebugCamera : public Camera
{
private:
	DebugCamera();
public:
	glm::vec3 vEye, vView, vUp;
	float fSpeed;
	float fSensitivity; // How many degrees to rotate per pixel moved by mouse (nice value is 0.10)

	// Main functions
	void rotateWithMouse();
	void update(ResourceManager *resourceManager) override;
	glm::mat4 look();

	void setMovingKeys(int a_iForw, int a_iBack, int a_iLeft, int a_iRight, int  iUp, int iDown, int iSpeed) override;

	// Functions that get viewing angles
	float getAngleX(), getAngleY();

	// Constructors
	SDL_Window *screen;

	DebugCamera(SDL_Window *screen);
	DebugCamera(SDL_Window *screen, glm::vec3 a_vEye, glm::vec3 a_vView, glm::vec3 a_vUp, float a_fSpeed, float a_fSensitivity);

	glm::vec3 &getVEye() override;

};

