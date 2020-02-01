#pragma once
//include libs
#include <stack>

//predefine
struct SDL_Window;
class Camera;
class Scene;
class ResourceManager;

/********************************

Class:	Renderer

Purpose:	Drawing scenes

********************************/

class Renderer {
	typedef struct ScreenProperties {
		const char* windowName;
		int SCREEN_W;
		int SCREEN_H;
		int startX;
		int startY;
		int displayMode;
		bool fullSize;
		bool freeConsole;
	}ScreenProperties;
private:

	std::stack<Scene*> sceneStack;



	SDL_Window * screen;



	void readConfig();
	void initScreen();
	void initContext();

	void initGlew();

public:
	Renderer();
	~Renderer();
	ScreenProperties screenProperties;


	SDL_Window *getScreen();
	
	void initScenes();


	void draw(ResourceManager *resourceManager);
	std::stack<Scene*> &getScenes();
};


