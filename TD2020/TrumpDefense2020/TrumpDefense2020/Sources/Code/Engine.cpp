#pragma once
#include "Engine.hpp"
#include "RequirementsChecker.hpp"
#include "ResourceManager.hpp"
#include "Controller.hpp"

//comment lib
#pragma comment(lib, "freetype.lib")
#pragma comment(lib, "FreeImage.lib")
#pragma comment(lib, "SDL2.lib")
#pragma comment(lib, "glew32.lib")
#pragma comment(lib, "opengl32.lib")
#pragma comment(lib, "SDL2_mixer.lib")


Engine::Engine() {
	resourceManager = new ResourceManager();
	RequirementsChecker::checkInitFiles(resourceManager);
	resourceManager->getController()->init();
	resourceManager->getController()->appBody();
	








	free(resourceManager);
}


const ResourceManager* Engine::getResourceManager() const { return resourceManager; }
