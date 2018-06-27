#pragma once
#include "Scene.hpp"
class GameScene : public Scene
{
public:
	GameScene();
	~GameScene();

	void drawBoundigBox();
	void drawDebugLines();
	void drawVisibleEntities();

};

