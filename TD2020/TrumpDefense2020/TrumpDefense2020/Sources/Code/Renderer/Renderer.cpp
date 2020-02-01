#pragma once
//include hpp
#include "Renderer.hpp"

//include libs
#include <iostream>
#include <stack>
#include <assert.h>
#include <Windows.h>
#include <SDL\SDL.h>
#include <glm\gtc\type_ptr.hpp>
#include <glm\gtc\matrix_transform.hpp>

//include files
#include "Scene\Scene.hpp"
#include "..\Entity\Component\Camera\Camera.hpp"
#include "..\Defines.hpp"
#include "GL_API.hpp"
#include "..\RequirementsChecker.hpp"
#include "..\ResourceManager.hpp"
#include "..\Shader.hpp"
#include "..\VertexBufferObject.hpp"
#include "..\Texture.hpp"

#include "..\Entity\Component\Camera\walkingCamera.hpp"
#include "..\Matrix.hpp"
#include "..\Controller.hpp"

Renderer::Renderer() {
	GL_API glApi;
	GL_API *glApi_ptr = &glApi;
	RequirementsChecker::checkGL_APIVersion(glApi_ptr);

	readConfig();
	initScreen();

	initContext();
	initGlew();


	//create scenes
	initScenes();
}

Renderer::~Renderer() { 
	//free screen
	free(screen);
	//free scene stack
	while (!sceneStack.empty()) {
		free(&sceneStack.top());
		sceneStack.pop();
	}
}

void Renderer::readConfig() {
	//READS CONFIG FROM INI FILE (screen config, display config, resolution...)
	screenProperties.windowName = "Trump Defense 2020";
	screenProperties.SCREEN_W = 500;
	screenProperties.SCREEN_H = 500;
	screenProperties.startX = 30;
	screenProperties.startY = 30;
	//screenProperties.displayMode = SDL_WINDOW_FULLSCREEN;
	screenProperties.displayMode = SDL_WINDOW_OPENGL;


	screenProperties.freeConsole = false;
	screenProperties.fullSize = false;
}

void Renderer::initScreen() {

	//HIDE CONSOLE:
	if(screenProperties.freeConsole) FreeConsole();

	if (screenProperties.fullSize) {
		SDL_DisplayMode current;

		SDL_Init(SDL_INIT_VIDEO);

		// Get current display mode of all displays.
		for (int i = 0; i < SDL_GetNumVideoDisplays(); ++i) {

			int should_be_zero = SDL_GetCurrentDisplayMode(i, &current);

			if (should_be_zero != 0)
				// In case of error...
				SDL_Log("Could not get display mode for video display #%d: %s", i, SDL_GetError());

			else {
				// On success, print the current display mode.
				SDL_Log("Display #%d: current display mode is %dx%dpx @ %dhz.", i, current.w, current.h, current.refresh_rate);
				//and set current SCREEN_W and SCREEN_H
				screenProperties.SCREEN_W = current.w;
				screenProperties.SCREEN_H = current.h;
			}
		}
	
	}

	screen = SDL_CreateWindow(screenProperties.windowName, screenProperties.startX, screenProperties.startY, screenProperties.SCREEN_W, screenProperties.SCREEN_H, screenProperties.displayMode);
	assert(screen != nullptr && "SDL_Window not created!");


}
void Renderer::initContext() {
	SDL_GLContext context = SDL_GL_CreateContext(screen);
	assert(context != nullptr && "SDL_Window not created!");
}


void Renderer::initGlew() {
	glewExperimental = true; // Needed for core profile
	GLenum glewinit = glewInit();
	assert(!glewinit && "Glew not okay!");
	assert(GLEW_VERSION_2_1 && "Glew version lesser than 2.1 -error ! ");


	GLenum error = glGetError();
	assert(error == GL_NO_ERROR && "GL ERROR");

	std::cout << "***********************************" << std::endl;

	std::cout << "GPU Vendor: " << glGetString(GL_VENDOR) << std::endl;
	std::cout << "OpenGL Version: " << glGetString(GL_VERSION) << std::endl;
	std::cout << "Renderer: " << glGetString(GL_RENDERER) << std::endl;

	int max; glGetIntegerv(GL_MAX_TEXTURE_IMAGE_UNITS, &max);
	std::cout << "Max Texture Units: " << max << std::endl;

	glGetIntegerv(GL_MAX_UNIFORM_LOCATIONS, &max);
	std::cout << "Max Uniforms: " << max << std::endl;

	glGetIntegerv(GL_MAX_VERTEX_ATTRIBS, &max);
	std::cout << "Max Vertex Attributes: " << max << std::endl;
	std::cout << "***********************************" << std::endl;

}

SDL_Window * Renderer::getScreen() { return screen; }

void Renderer::initScenes() {

	//sceneStack.push(new Scene("7", screen));
	sceneStack.push(new Scene("joined", screen));
}

void Renderer::draw(ResourceManager * resourceManager){
	sceneStack.top()->draw(resourceManager);

}

std::stack<Scene*> &Renderer::getScenes() {
	return sceneStack;
}




