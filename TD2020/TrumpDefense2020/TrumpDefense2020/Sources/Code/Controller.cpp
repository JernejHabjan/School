#pragma once
//include hpp
#include "Controller.hpp"

//include libs
#include <SDL\SDL.h>


//include files
#include "Renderer\GL_API.hpp"
#include "Renderer\Renderer.hpp"
#include "ResourceManager.hpp"
#include "SceneUpdater\InputManager.hpp"

Controller::Controller(ResourceManager *resourceManager) {
	this->resourceManager = resourceManager;
}

Controller::~Controller(){ }


void Controller::init() {

	//resourceManager->getRenderer()->initPrimitivesWithShaderAndMatrices();

}


void Controller::appBody() {
	Uint32 startTime;
	while (1) {
		startTime = SDL_GetTicks();

		updateTimer();
		resourceManager->getInputManager()->interpretAction();

		resourceManager->getRenderer()->draw(resourceManager);
		if (SDL_GetMouseFocus() != resourceManager->getRenderer()->getScreen()) { //if window is not focused, dont waste cpu
			SDL_Delay(200);
		}

		if (1000 / 60 > (SDL_GetTicks() - startTime))
			SDL_Delay(1000 / 60 - (SDL_GetTicks() - startTime));
	}
}


int Controller::getFPS() {
	return iFPSCount;
}

float Controller::sof(float fVal) {
	return fVal*fFrameInterval;
}

void Controller::resetTimer() {
	tLastFrame = clock();
	fFrameInterval = 0.0f;
}

void Controller::updateTimer() {
	clock_t tCur = clock();
	fFrameInterval = float(tCur - tLastFrame) / float(CLOCKS_PER_SEC);

	tLastFrame = tCur;
}

