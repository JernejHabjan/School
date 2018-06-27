#pragma once
//include libs
#include <string>
#include <unordered_map>
#include <SDL\SDL.h>

//predefines
struct SDL_Window;
class ResourceManager;
class Camera;
class Scene
{
private:
	std::string level;
	SDL_Window *screen;


	std::unordered_map <SDL_Keycode, std::string> sceneInputHasher;
public:
	Scene();


	Camera *camera;

	Scene(std::string level, SDL_Window *screen);
	~Scene();

	void readSceneConditionsAndRules();
	void updateScene(SDL_Keycode key, ResourceManager * resourceManager);



	void init7();
	void initJoined();


	void draw(ResourceManager *resourceManager);
	void draw7(ResourceManager *resourceManager);
	void drawJoined(ResourceManager * resourceManager);
};

