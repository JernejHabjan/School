#pragma once
#include "ObjectStorage.hpp"
#include "Sound.hpp"
#include "Camera.hpp"
#include "Grid.hpp"
#include "Resources.hpp"


namespace InputManager
{ 

	const float MOVE_DISTANCE = 2.5;
	const float ROTATE_PITCH = 5.0;


	void manageEvent(SDL_KeyboardEvent *key, Sound* sound, bool &running, bool &victory, Resources &resources, Grid &grid, int &level, Uint32 &gameStartTime);
	void manageMousePressEvent(SDL_MouseButtonEvent& e, float SCREEN_W, float SCREEN_H, Camera* cam, Matrix &matrix, Grid &grid, Sound* sound);
	void onMouseWheelScroll(SDL_Event &event, Camera* cam);

};

