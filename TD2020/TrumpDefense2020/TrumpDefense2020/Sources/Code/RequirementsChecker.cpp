#pragma once
//include hpp
#include "RequirementsChecker.hpp"

//include libs
#include <SDL\SDL.h>

//include files
#include "Defines.hpp"
#include "FileManager\FileManager.hpp"
#include "ResourceManager.hpp"
#include "Renderer\GL_API.hpp"


RequirementsChecker::RequirementsChecker() { }
RequirementsChecker::~RequirementsChecker() { }

void RequirementsChecker::checkGL_APIVersion(GL_API *glApi) {
	//determines what version of API is required to run

	glApi->bla();


	

}

void RequirementsChecker::checkInitFiles(ResourceManager *resourceManager) {
	//Checks file system for specific file

	printNL("Checking init files... ");

	std::string initPath = "Sources\\init.ini";

	if (resourceManager->getFileManager()->fileRead(initPath, resourceManager))
		print("OK!");
	else {
		print("NOK! - exiting...");
		system("PAUSE");
		exit(1);
	}
		

}


