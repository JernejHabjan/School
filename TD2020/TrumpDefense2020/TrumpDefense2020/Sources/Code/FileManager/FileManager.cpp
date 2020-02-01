#pragma once
//include hpp
#include "FileManager.hpp"

//include files
#include "..\Defines.hpp"
#include "FileWriter.hpp"
#include "FileReader.hpp"

FileManager::FileManager(ResourceManager *resourceManager) {
	fr = new FileReader(resourceManager);
	fw = new FileWriter();
}


FileManager::~FileManager() {
	free(fr);
	free(fw);
}

bool FileManager::fileWrite(std::string &path, std::string &data) {
	return fw->fileWrite(path, data);
}

bool FileManager::fileRead(std::string path, ResourceManager *resourceManager)
{
	return fr->fileRead(path, resourceManager);
}
