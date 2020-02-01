#pragma once
#include "InputManager.hpp"
#include "Resources.hpp"
#include <Windows.h>

class Game
{
public:
	SDL_Window* screen;
	int SCREEN_W = 1600;
	int SCREEN_H = 900;



	bool defeated = false;
	bool victory = false;

	std::vector<std::string> enemyArray;


	int defeatedTimes = 0;
	int level = 1;
	bool running = true;
	Game();
	void init();
	void play();
	void nextLevel();
	~Game();
};

