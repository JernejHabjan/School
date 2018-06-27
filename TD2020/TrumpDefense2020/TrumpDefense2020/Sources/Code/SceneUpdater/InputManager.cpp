#pragma once
//include hpp
#include "InputManager.hpp"

//include libs
#include <SDL\SDL.h>

//include files
#include "..\Defines.hpp"
#include "..\Loader\Loader.hpp"

#include "..\ResourceManager.hpp"
#include "..\Renderer\Renderer.hpp"
#include "..\Entity\Component\Camera\Camera.hpp"
#include "..\Renderer\Scene\Scene.hpp"
InputManager::InputManager(ResourceManager * resourceManager) {
	this->resourceManager = resourceManager;

}

InputManager::~InputManager() {
}


void InputManager::readControllsFromFile() {

}

void InputManager::interpretAction() {
	
	SDL_Event event;
	while (SDL_PollEvent(&event)) {
	
		switch (event.type) {
		case SDL_QUIT: {
			exit(1);
		}
		case SDL_KEYDOWN: {
	
			SDL_Keycode keyCode = event.key.keysym.sym;
			resourceManager->getRenderer()->getScenes().top()->updateScene(keyCode, resourceManager);
			break;
		}
		case SDL_MOUSEBUTTONDOWN: {
			switch (event.button.button) {
			case SDL_BUTTON_LEFT:
			{
				print("left mouse");
				break;
			}
			case SDL_BUTTON_RIGHT:
			{
				print("right mouse");
				break;
			}
			default:
			{
				print("MOUSE PRESS UNSUPPORTED");
				break;
			}
			}
			break;
		}

		case SDL_MOUSEWHEEL: {

			if (event.wheel.y < 0) {

				print("mouse wheel down");
			}
			else {
				print("mouse wheel up");
			}
			break;
		}
							 //no action occured
		}
	}
	
}

