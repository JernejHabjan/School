#pragma once
//include parent
#include "ResourceManager.hpp"

//include libs
#include <SDL\SDL.h>

//include files
#include "Shader.hpp"
#include "Renderer\Renderer.hpp"
#include "Matrix.hpp"
#include "FileManager\FileManager.hpp"
#include "Controller.hpp"
#include "SceneUpdater\InputManager.hpp"


ResourceManager::ResourceManager() {
	matrix = new Matrix();
	renderer = new Renderer();
	fileManager = new FileManager(this);
	controller = new Controller(this);
	inputManager = new InputManager(this);



	float initialFOV = 45.0f;
	float zNear = 0.5f; //ker je cubemap velk 1x1x1
	float zFar = 200.0f;

	int SCREEN_W = renderer->screenProperties.SCREEN_W;
	int SCREEN_H = renderer->screenProperties.SCREEN_H;
	
	matrix->setProjection3D(initialFOV, SCREEN_W / SCREEN_H, zNear, zFar);
	matrix->setOrtho2D(SCREEN_W, SCREEN_H);
}


ResourceManager::~ResourceManager() {
	matrix->~Matrix();  free(matrix);
	renderer->~Renderer();  free(renderer);
	fileManager->~FileManager(); free(fileManager);
	controller->~Controller(); free(controller);
	inputManager->~InputManager(); free(inputManager);

	//free hashmaps and its objects
	/*freeHashMap(lightHashmap);
	freeHashMap(soundHashmap);
	freeHashMap(materialHashmap);
	freeHashMap(textureHashmap);
	freeHashMap(modelHashmap);
	freeHashMap(entityHashmap);*/
}
/*
template <class T>
ResourceManager::freeHashMap(std::unordered_map<std::string, T*> hashMap) {

	for (auto it = hashMap.begin(); it != hashMap.end(); ++it) {
		try	{ it->second->~TYPE(); }
		catch (int e)  {print(it->first << " doesn't have destructor"); }
		free(it->second);

	}
}*/

//GETTERS
Matrix * ResourceManager::getMatrix() const { return matrix; }
Renderer * ResourceManager::getRenderer() const {	return renderer; }
FileManager * ResourceManager::getFileManager() const { return fileManager; }
Controller * ResourceManager::getController() const { return controller; }
InputManager * ResourceManager::getInputManager() const { return inputManager; }
std::vector<std::string>* ResourceManager::getInitFiles() {	return &initFiles; }
std::vector<std::string>* ResourceManager::getConfigFiles() { return &configFiles; }
const std::unordered_map<std::string, Light*>* ResourceManager::getLightHashMap() { return &lightHashmap; }
const std::unordered_map<std::string, SDL_Sound*>* ResourceManager::getSoundHashmap() { return &soundHashmap; }
const std::unordered_map<std::string, Material*>* ResourceManager::getMaterialHashmap() { return &materialHashmap; }
const std::unordered_map<std::string, Texture*>* ResourceManager::getTextureHashmap() { return &textureHashmap; }
const std::unordered_map<std::string, Model*>* ResourceManager::getModelHashmap() { return &modelHashmap; }
const std::unordered_map<std::string, Entity*>* ResourceManager::getEntityHashmap() { return &entityHashmap; }

//SETTERS
void ResourceManager::addInitFile(std::string initFile) { initFiles.push_back(initFile); }
void ResourceManager::addConfigFile(std::string configFile) { configFiles.push_back(configFile); }
void ResourceManager::addLight(std::string name, Light * light) { lightHashmap.insert({ name, light }); }
void ResourceManager::addSound(std::string name, SDL_Sound *sound) { soundHashmap.insert({ name, sound }); }
void ResourceManager::addMaterial(std::string name, Material * material) { materialHashmap.insert({ name, material }); }
void ResourceManager::addTexture(std::string name, Texture * texture) {	textureHashmap.insert({ name, texture }); }
void ResourceManager::addModel(std::string name, Model * model) { modelHashmap.insert({ name, model }); }
void ResourceManager::addEntity(std::string name, Entity * entity) { entityHashmap.insert({ name, entity }); }
