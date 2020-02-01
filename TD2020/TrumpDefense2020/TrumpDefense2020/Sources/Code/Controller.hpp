#pragma once

//include libs
#include <time.h>

class ResourceManager;
class Controller {
	ResourceManager *resourceManager;
public:

	//for render
	float fRotationAngle = 0.0f;
	const float PIover180 = 3.1415f / 180.0f;

	//for update
	clock_t tLastFrame;
	float fFrameInterval;


	// Used for FPS calculation
	int iFPSCount, iCurrentFPS;
	clock_t tLastSecond;

	int getFPS();

	float sof(float fVal);
	void resetTimer();
	void updateTimer();



	Controller(ResourceManager *resourceManager);
	~Controller();


	void init();

	void appBody();

};

