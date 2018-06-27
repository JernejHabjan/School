#pragma once
//include hpp
#include "Camera.hpp"

//include files
#include "DebugCamera.hpp"
#include "walkingCamera.hpp"

Camera::Camera()
{

}


Camera::~Camera()
{
}

void Camera::initCamera(SDL_Window *screen)
{
	//read from file type of camera used for this scene

	//TEMP
	//camera = new DebugCamera(screen);
	camera = new WalkingCamera();

}

void Camera::update(ResourceManager *resourceManager)
{
	if(camera != nullptr)
		camera->update(resourceManager);
}

glm::mat4 Camera::look()
{
	return camera->look();
}

void Camera::readCameraControls()
{

	//read controlls --reads sdl controls from config file that user saved

	//setMovingKeys();

}

void Camera::setMovingKeys(int a_iForw, int a_iBack, int a_iLeft, int a_iRight, int  iUp, int iDown, int iSpeed)
{

}

glm::vec3 & Camera::getVEye()
{
	return camera->getVEye();
}
