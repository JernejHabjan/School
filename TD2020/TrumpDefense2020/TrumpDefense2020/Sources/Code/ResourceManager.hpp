#pragma once
//include libs
#include <unordered_map>
#include <time.h>

//predefines
class FileManager;
class Light;
class SDL_Sound;
class Material;
class Texture;
class Model;
class Entity;
class Renderer;
class Matrix;
class Controller;
class InputManager;

/********************************

Class:	ResourceMager

Purpose: Stores raw and complex
	structures

********************************/


class ResourceManager {
private:
	Matrix *matrix;
	Renderer *renderer;
	FileManager *fileManager;
	Controller *controller;
	InputManager *inputManager;

	std::vector<std::string> initFiles; //files to load from - obj, textures..
	std::vector<std::string> configFiles; //files that store game data

	std::unordered_map<std::string, Light*> lightHashmap;
	std::unordered_map<std::string, SDL_Sound*> soundHashmap;
	std::unordered_map<std::string, Material*> materialHashmap;
	std::unordered_map<std::string, Texture*> textureHashmap;
	std::unordered_map<std::string, Model*> modelHashmap;
	std::unordered_map<std::string, Entity*> entityHashmap;
public:

	ResourceManager();
	~ResourceManager();

	//template <class T>
	//freeHashMap(std::unordered_map<std::string, T*> hashMap;

	//GETTERS
	Matrix * getMatrix() const;
	Renderer * getRenderer() const;
	FileManager * getFileManager() const;
	Controller * getController() const;
	InputManager * getInputManager() const;


	std::vector<std::string> *getInitFiles();
	std::vector<std::string> *getConfigFiles();
	const std::unordered_map<std::string, Light*> *getLightHashMap();
	const std::unordered_map<std::string, SDL_Sound*> *getSoundHashmap();
	const std::unordered_map<std::string, Material*> *getMaterialHashmap();
	const std::unordered_map<std::string, Texture*> *getTextureHashmap();
	const std::unordered_map<std::string, Model*> *getModelHashmap();
	const std::unordered_map<std::string, Entity*> *getEntityHashmap();

	//SETTERS
	void addInitFile(std::string initFile);
	void addConfigFile(std::string configFile);
	void addLight(std::string name, Light *light);
	void addSound(std::string name, SDL_Sound *sound);
	void addMaterial(std::string name, Material *material);
	void addTexture(std::string name, Texture *texture);
	void addModel(std::string name, Model *model);
	void addEntity(std::string name, Entity *entity);
};

