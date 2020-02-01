#pragma once
//include hpp
#include "Loader.hpp"

//include files
#include "..\ResourceManager.hpp"
#include "..\FileManager\FileManager.hpp"

Loader::Loader(ResourceManager* resourceManager) { 
	this->resourceManager = resourceManager;
}


Loader::~Loader() { }



void Loader::load() {
	for each (std::string file in *resourceManager->getInitFiles()) {
		resourceManager->getFileManager()->fileRead(file, resourceManager);
	}
}
